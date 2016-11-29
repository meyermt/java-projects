package _08final.mvc.model;

import _08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by michaelmeyer on 11/1/16.
 */
public class Saint extends Sprite {

    private static final int WALKING_SPEED = 7;
    private static final int BUMPER_WAIT_FRAMES = 10;
    private static final int SAINT_DIAMETER = 60;
    private static final int SAINT_RADIUS = SAINT_DIAMETER / 2;
    private static final int TOP_SPEED = 60;
    private BufferedImage[] saintPhases = new SpriteSheet("saint-phases.png", 32, 32).getAllSprites();

    public boolean isThrowing;
    public boolean isReleasingThrow;
    private boolean isJumping;
    public boolean hasBall;
    public boolean isCatching;
    private boolean isFacingLeft;
    private boolean isFacingRight;
    public boolean isRetrieving;
    private boolean leftStepSwitch;
    private boolean rightStepSwitch;
    private boolean rightStepThrowingSwitch;
    private int throwingCadence;
    private static final Random random = new Random();
    private Ball ball;

    private double ballXCoords;
    private double ballYCoords;
    private double radians;
    private boolean isWalkingRight;
    private boolean isWalkingUp;
    private int xBumperCounter;
    private int yBumperCounter;

    public Saint() {
    }

    //TODO: should add an intelligence in this constructor at some point
    public Saint(Point startingPoint) {
        xBumperCounter = 0;
        yBumperCounter = 0;
        isFacingLeft = true;
        isFacingRight = false;
        setTeam(Team.FOE);
        setRadius(SAINT_RADIUS);
        setCenter(startingPoint);
        setThrowingSpeed(random.nextDouble() * TOP_SPEED);
        radians = random.nextDouble() * 2;
        throwingCadence = random.nextInt(10) + 1;
        setDeltaX(Math.cos(radians) * WALKING_SPEED);
        setDeltaY(Math.sin(radians) * WALKING_SPEED);
        setWalkingByRadians();
        isRetrieving = false;
        ball = null;
    }

    @Override
    public void move() {
            Point pnt = getCenter();
            //non max values implies there is a ball to get
            if (isRetrieving && !hasBall) {
                radians = Math.atan2((ballYCoords - getCenter().getY()), (ballXCoords - getCenter().getX()));
                setDeltaX(Math.cos(radians) * WALKING_SPEED);
                setDeltaY(Math.sin(radians) * WALKING_SPEED);
                setWalkingByRadians();
            }

            if (!isRetrieving) {
                //need to wait for a bit after bouncing so we don't spiral off the screen by changing too soon
                int bumpXWait = xBumperCounter % BUMPER_WAIT_FRAMES;
                int bumpYWait = yBumperCounter % BUMPER_WAIT_FRAMES;
                //this just keeps the sprite inside the bounds of their side
                if (pnt.x + SAINT_RADIUS > Game.DIM.getWidth() && bumpXWait == 0) {
                    radians = Math.PI - radians;
                    xBumperCounter++;
                }
                if (pnt.x - SAINT_RADIUS < Game.SAINT_X_TERRITORY && bumpXWait == 0) {
                    radians = Math.PI - radians;
                    xBumperCounter++;
                    //isWalkingRight = true;
                }
                if (pnt.y + SAINT_RADIUS > Game.DIM.getHeight() && bumpYWait == 0) {
                    radians = (2 * Math.PI) - radians;
                    yBumperCounter++;
                    //isWalkingUp = true;
                }
                if (pnt.y - SAINT_RADIUS < 0 && bumpYWait == 0) {
                    radians = (2 * Math.PI) - radians;
                    yBumperCounter++;
                    //isWalkingUp = false;
                } else {
                    xBumperCounter = 0;
                    yBumperCounter = 0;
                }
                setDeltaX(Math.cos(radians) * WALKING_SPEED);
                setDeltaY(Math.sin(radians) * WALKING_SPEED);
            } else {
                setDeltaX(Math.cos(radians) * WALKING_SPEED);
                setDeltaY(Math.sin(radians) * WALKING_SPEED);
                if (pnt.x + SAINT_RADIUS > Game.DIM.getWidth()) {
                    setDeltaX(0);
                }
                if (pnt.x - SAINT_RADIUS < Game.SAINT_X_TERRITORY) {
                    setDeltaX(0);
                }
                if (pnt.y + SAINT_RADIUS > Game.DIM.getHeight()) {
                    setDeltaY(0);
                }
                if (pnt.y - SAINT_RADIUS < 0) {
                    setDeltaY(0);
                }
            }
            setWalkingByRadians();
            double dX = pnt.x + getDeltaX();
            double dY = pnt.y + getDeltaY();
            setCenter(new Point((int) dX, (int) dY));
            //setGraphicDirections(radians);
    }

    @Override
    public void draw(Graphics g) {
        Point coord = getCenter();
        if (isFacingLeft && !isCatching && !hasBall) {
            if (leftStepSwitch) {
                g.drawImage(saintPhases[0].getScaledInstance(SAINT_DIAMETER, SAINT_DIAMETER, 0), (int) coord.getX() - SAINT_RADIUS, (int) coord.getY() - SAINT_RADIUS, null);
                leftStepSwitch = false;
            } else {
                g.drawImage(saintPhases[1].getScaledInstance(SAINT_DIAMETER, SAINT_DIAMETER, 0), (int) coord.getX() - SAINT_RADIUS, (int) coord.getY() - SAINT_RADIUS, null);
                leftStepSwitch = true;
            }
        } else if (isFacingRight && !isCatching && !hasBall) {
            if (rightStepSwitch) {
                g.drawImage(saintPhases[2].getScaledInstance(SAINT_DIAMETER, SAINT_DIAMETER, 0), (int) coord.getX() - SAINT_RADIUS, (int) coord.getY() - SAINT_RADIUS, null);
                rightStepSwitch = false;
            } else {
                g.drawImage(saintPhases[3].getScaledInstance(SAINT_DIAMETER, SAINT_DIAMETER, 0), (int) coord.getX() - SAINT_RADIUS, (int) coord.getY() - SAINT_RADIUS, null);
                rightStepSwitch = true;
            }
        } else if (isReleasingThrow) {
            g.drawImage(saintPhases[8].getScaledInstance(SAINT_DIAMETER, SAINT_DIAMETER, 0), (int) coord.getX() - SAINT_RADIUS, (int) coord.getY() - SAINT_RADIUS, null);
            isReleasingThrow = false;
        } else if (hasBall) {
            if (rightStepThrowingSwitch) {
                g.drawImage(saintPhases[6].getScaledInstance(SAINT_DIAMETER, SAINT_DIAMETER, 0), (int) coord.getX() - SAINT_RADIUS, (int) coord.getY() - SAINT_RADIUS, null);
                rightStepThrowingSwitch = false;
            } else {
                g.drawImage(saintPhases[7].getScaledInstance(SAINT_DIAMETER, SAINT_DIAMETER, 0), (int) coord.getX() - SAINT_RADIUS, (int) coord.getY() - SAINT_RADIUS, null);
                rightStepThrowingSwitch = true;
            }
        } else if (isCatching && !hasBall) {
            g.drawImage(saintPhases[4].getScaledInstance(SAINT_DIAMETER, SAINT_DIAMETER, 0), (int) coord.getX() - SAINT_RADIUS, (int) coord.getY() - SAINT_RADIUS, null);
        }
    }

    public void retrieveBall(Ball ball) {
        if (!ball.hasFoeRetriever) {
            ballXCoords = ball.getCenter().getX();
            ballYCoords = ball.getCenter().getY();
            isRetrieving = true;
        }
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public int getThrowingCadence() {
        return throwingCadence;
    }

    private void setWalkingByRadians() {
//        System.out.println("radians is: " + radians);
//        System.out.println(" pre adjust walking right? " + isWalkingRight);
//        System.out.println(" pre adjust walking up? " + isWalkingUp);
        System.out.println(" is retrieving? " + isRetrieving);
        if (Math.PI / 2 > Math.abs(radians)) {
            isWalkingRight = true;
            isFacingRight = true;
            isFacingLeft = false;
        } else {
            isWalkingRight = false;
            isFacingRight = false;
            isFacingLeft = true;
        }

        if (radians < 0) {
            isWalkingUp = true;
        } else {
            isWalkingUp = false;
        }
    }

    private void setGraphicDirections(double radians) {
        double angle = Math.toDegrees(radians);
        if (angle < 180) {
            isFacingLeft = false;
            isFacingRight = true;
        } else {
            isFacingLeft = true;
            isFacingRight = false;
        }
    }

}
