package _08final.mvc.model;

import _08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A little man/beast set to win dodgeball glory for his hometown of Hellcago. This is his model.
 * Created by michaelmeyer on 11/1/16.
 */
public class Diablo extends Sprite {

    private static final int WALKING_SPEED = 7;
    private static final int DODGE_COUNT = 3;
    private static final int DODGE_SPEED = 20;
    public static final int MAX_THROWING_SPEED = 40;
    public static final int DIABLO_DIAMETER = 60;
    public static final int DIABLO_RADIUS = DIABLO_DIAMETER / 2;

    //positions in this array: 0 - walk right, 1 - walk right, 2 - walk left, 3 - walk left
    private SpriteSheet dWalking = new SpriteSheet("diablo-walking.png", 31, 28);
    //positions in this array: 0 - catching, 1 - caught ball
    private SpriteSheet dCatching = new SpriteSheet("diablo-catching.png", 31, 28);
    //positions in this array: 0 - holding and standing, 1 - holding and walking, 2 - throwing
    private SpriteSheet dBall = new SpriteSheet("diablo-ball.png", 31, 28);
    private BufferedImage[] diabloPhases;

    private int walkingSpeed;
    private boolean bProtected; //for fade in and out
    public boolean isThrowing;
    public boolean isReleasingThrow;
    public boolean isJumping;
    public boolean hasBall;
    public boolean isCatching;
    private boolean isFacingLeft;
    private boolean isFacingRight;
    public boolean walkingLeft;
    public boolean walkingRight;
    public boolean walkingUp;
    public boolean walkingDown;

    private boolean leftStepSwitch;
    private boolean rightStepSwitch;
    private boolean rightStepThrowingSwitch;
    private int staticSheetPos;
    private double radians;
    private int dodgeCounter = 0;

    public Diablo() {
        isJumping = false;
        hasBall = false;
        isThrowing = false;
        isFacingRight = true;
        isReleasingThrow = false;
        setThrowingSpeed(WALKING_SPEED);
        setTeam(Team.FRIEND);
        setCenter(new Point(Game.ARENA_WIDTH / 4, Game.ARENA_HEIGHT / 2));
        setRadius(DIABLO_RADIUS);
        this.staticSheetPos = 0;
        BufferedImage[] dWalkingPhases = dWalking.getAllSprites();
        BufferedImage[] dCatchingPhases = dCatching.getAllSprites();
        BufferedImage[] dThrowingPhases = dBall.getAllSprites();
        diabloPhases = new BufferedImage[dWalkingPhases.length + dCatching.getAllSprites().length + dBall.getAllSprites().length];
        System.arraycopy(dWalkingPhases, 0, diabloPhases, 0, dWalkingPhases.length);
        System.arraycopy(dCatchingPhases, 0, diabloPhases, dWalkingPhases.length, dCatchingPhases.length);
        System.arraycopy(dThrowingPhases, 0, diabloPhases, dWalkingPhases.length + dCatchingPhases.length, dThrowingPhases.length);
        isCatching = false;
        setProtected(true);
        walkingSpeed = WALKING_SPEED;
    }

    @Override
    public void move() {
        setDeltaX(0);
        setDeltaY(0);
        if (CommandCenter.getInstance().isMaxWalkingSpeedFloater()) {
            walkingSpeed = WALKING_SPEED * 2;
        } else {
            walkingSpeed = WALKING_SPEED;
        }
        if (isJumping) {
            dodgeCounter++;
            if (dodgeCounter > DODGE_COUNT) {
                isJumping = false;
                dodgeCounter = 0;
            }
            setDeltaX( Math.cos(radians) * DODGE_SPEED );
            setDeltaY( Math.sin(radians) * DODGE_SPEED );
        } else if (isCatching) {
            //you can't move when catching
        } else if (isReleasingThrow) {
            //reset throwing speed
            setThrowingSpeed(0);
        } else if (isThrowing) {
            updateThrowingSpeed();
        } else {
            if (walkingLeft && (getCenter().getX() - DIABLO_RADIUS > 0)) {
                isFacingRight = false;
                isFacingLeft = true;
                setDeltaX(-walkingSpeed);
            }
            if (walkingRight && (getCenter().getX() + DIABLO_RADIUS < Game.ARENA_WIDTH / 2)) {
                isFacingRight = true;
                isFacingLeft = false;
                setDeltaX(walkingSpeed);
            }
            if (walkingUp && (getCenter().getY() - DIABLO_RADIUS > 0)) {
                setDeltaY(-walkingSpeed);
            }
            if (walkingDown && (getCenter().getY() + DIABLO_DIAMETER < Game.ARENA_HEIGHT)) {
                setDeltaY(walkingSpeed);
            }
        }

        Point pnt = getCenter();
        double dX = pnt.x + getDeltaX();
        double dY = pnt.y + getDeltaY();
        setCenter(new Point((int) dX, (int) dY));

        //implementing the fadeInOut functionality - added by Dmitriy
        if (getProtected()) {
            setFadeValue(getFadeValue() + 1);
        }
        if (getFadeValue() == 50) {
            setProtected(false);
        }
    }

    public void dodge(int x, int y) {
        radians = Math.atan2((y - getCenter().getY()), (x - getCenter().getX()));
        isJumping = true;
    }

    @Override
    public void draw(Graphics g) {
        Point coord = getCenter();
        if (isFacingLeft && !isCatching && !hasBall && (walkingLeft || walkingUp || walkingDown)) {
            if (leftStepSwitch) {
                staticSheetPos = 2;
                g.drawImage(diabloPhases[2].getScaledInstance(DIABLO_DIAMETER, DIABLO_DIAMETER, 0), (int) coord.getX() - DIABLO_RADIUS, (int) coord.getY() - DIABLO_RADIUS, null);
                leftStepSwitch = false;
            } else {
                staticSheetPos = 3;
                g.drawImage(diabloPhases[3].getScaledInstance(DIABLO_DIAMETER, DIABLO_DIAMETER, 0), (int) coord.getX() - DIABLO_RADIUS, (int) coord.getY() - DIABLO_RADIUS, null);
                leftStepSwitch = true;
            }
        } else if (isFacingRight && !isCatching && !hasBall && (walkingRight || walkingUp || walkingDown)) {
            if (rightStepSwitch) {
                staticSheetPos = 0;
                g.drawImage(diabloPhases[0].getScaledInstance(DIABLO_DIAMETER, DIABLO_DIAMETER, 0), (int) coord.getX() - DIABLO_RADIUS, (int) coord.getY() - DIABLO_RADIUS, null);
                rightStepSwitch = false;
            } else {
                staticSheetPos = 1;
                g.drawImage(diabloPhases[1].getScaledInstance(DIABLO_DIAMETER, DIABLO_DIAMETER, 0), (int) coord.getX() - DIABLO_RADIUS, (int) coord.getY() - DIABLO_RADIUS, null);
                rightStepSwitch = true;
            }
        } else if (isReleasingThrow) {
            staticSheetPos = 2;
            g.drawImage(diabloPhases[8].getScaledInstance(DIABLO_DIAMETER, DIABLO_DIAMETER, 0), (int) coord.getX() - DIABLO_RADIUS, (int) coord.getY() - DIABLO_RADIUS, null);
            isReleasingThrow = false;
        } else if (hasBall && (walkingRight || walkingLeft || walkingUp || walkingDown)) {
            if (rightStepThrowingSwitch) {
                staticSheetPos = 6;
                g.drawImage(diabloPhases[6].getScaledInstance(DIABLO_DIAMETER, DIABLO_DIAMETER, 0), (int) coord.getX() - DIABLO_RADIUS, (int) coord.getY() - DIABLO_RADIUS, null);
                rightStepThrowingSwitch = false;
            } else {
                staticSheetPos = 7;
                g.drawImage(diabloPhases[7].getScaledInstance(DIABLO_DIAMETER, DIABLO_DIAMETER, 0), (int) coord.getX() - DIABLO_RADIUS, (int) coord.getY() - DIABLO_RADIUS, null);
                rightStepThrowingSwitch = true;
            }
        } else if (isCatching && !hasBall) {
            g.drawImage(diabloPhases[4].getScaledInstance(DIABLO_DIAMETER, DIABLO_DIAMETER, 0), (int) coord.getX() - DIABLO_RADIUS, (int) coord.getY() - DIABLO_RADIUS, null);
        } else {
            g.drawImage(diabloPhases[staticSheetPos].getScaledInstance(DIABLO_DIAMETER, DIABLO_DIAMETER, 0), (int) coord.getX() - DIABLO_RADIUS, (int) coord.getY() - DIABLO_RADIUS, null);
        }
    }

    public BufferedImage getDiabloPic() {
        return diabloPhases[2];
    }

    public void setProtected(boolean bParam) {
        if (bParam) {
            setFadeValue(0);
        }
        bProtected = bParam;
    }

    public boolean getProtected() {return bProtected;}

    //longer it is held, more updates made
    private void updateThrowingSpeed() {
        if (CommandCenter.getInstance().isMaxThrowingSpeedFloater()) {
            setThrowingSpeed(MAX_THROWING_SPEED);
        }else if (getThrowingSpeed() < MAX_THROWING_SPEED) {
            setThrowingSpeed(getThrowingSpeed() + 1);
        }
    }

}
