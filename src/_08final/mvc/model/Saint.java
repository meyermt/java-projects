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
    private static final int AM_I_LOST_TIMER = 300;
    private static final int SAINT_DIAMETER = 60;
    private static final int SAINT_RADIUS = SAINT_DIAMETER / 2;
    private static final int MAX_THROWING_CADENCE = 30;
    private static final int MIN_THROWING_CADENCE = 1;
    private static final int MIN_THROWING_SPEED = 10;

    /**
     * The Top speed.
     */
    public int topSpeed = 30;
    private int lostCounter = 0;
    private BufferedImage[] saintPhases = new SpriteSheet("saint-phases.png", 32, 32).getAllSprites();

    /**
     * The Is throwing.
     */
    public boolean isThrowing;
    /**
     * The Is releasing throw.
     */
    public boolean isReleasingThrow;
    private boolean isJumping;
    private boolean isCommandingRetrieval;
    /**
     * The Has ball.
     */
    public boolean hasBall;
    /**
     * The Is catching.
     */
    public boolean isCatching;
    private boolean isFacingLeft;
    private boolean isFacingRight;
    /**
     * The Is retrieving.
     */
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

    /**
     * Instantiates a new Saint.
     */
    public Saint() {
    }

    /**
     * Instantiates a new Saint.
     *
     * @param startingPoint the starting point
     */
    public Saint(Point startingPoint) {
        isFacingLeft = true;
        isFacingRight = false;
        setTeam(Team.FOE);
        setRadius(SAINT_RADIUS);
        setCenter(startingPoint);
        setThrowingSpeed(random.nextInt(topSpeed) + MIN_THROWING_SPEED);
        radians = (random.nextDouble() * Math.PI);
        throwingCadence = random.nextInt(MAX_THROWING_CADENCE) + MIN_THROWING_CADENCE;
        setDeltaX(Math.cos(radians) * WALKING_SPEED);
        setDeltaY(Math.sin(radians) * WALKING_SPEED);
        setWalkingByRadians();
        isRetrieving = false;
        isCommandingRetrieval = false;
        ball = null;
        lostCounter = 0;
    }

    @Override
    public void move() {
        lostCounter++;
        Point pnt = getCenter();

        if (isCommandingRetrieval) {
            commandRetrieval();
            isCommandingRetrieval = false;
        }

        bounceOffEdges(pnt);
            if (isRetrieving) {
                lostCounter++;
                if (lostCounter > AM_I_LOST_TIMER) {
                    isRetrieving = false;
                    setBall(null);
                    lostCounter = 0;
                    if (ball != null) {
                        ball.setSaintRetriever(null);
                    }
                }
            }
        setDeltaX(Math.cos(radians) * WALKING_SPEED);
        setDeltaY(Math.sin(radians) * WALKING_SPEED);
        double dX = pnt.x + getDeltaX();
        double dY = pnt.y + getDeltaY();
        setCenter(new Point((int) dX, (int) dY));
    }

    /**
     * Bounce off edges.
     *
     * @param pnt the pnt
     */
    public void bounceOffEdges(Point pnt) {
        if (pnt.x + SAINT_RADIUS > Game.ARENA_WIDTH && isWalkingRight) {
            radians = Math.PI - radians;
            isWalkingRight = false;
            isFacingLeft = true;
            isFacingRight = false;
        } else if (pnt.x - SAINT_RADIUS < Game.SAINT_X_TERRITORY && !isWalkingRight) {
            radians = Math.PI - radians;
            isWalkingRight = true;
            isFacingLeft = false;
            isFacingRight = true;
        } else if (pnt.y + SAINT_DIAMETER> Game.ARENA_HEIGHT && !isWalkingUp) {
            radians = (2 * Math.PI) - radians;
            isWalkingUp = true;
        } else if (pnt.y - SAINT_RADIUS < 0 && isWalkingUp) {
            radians = (2 * Math.PI) - radians;
            isWalkingUp = false;
        } else {
        }
        setDeltaX(Math.cos(radians) * WALKING_SPEED);
        setDeltaY(Math.sin(radians) * WALKING_SPEED);
    }

    @Override
    public void draw(Graphics g) {
        Point coord = getCenter();
        if (isFacingLeft && !isCatching && !hasBall) {
            if (leftStepSwitch) {
                g.drawImage(saintPhases[2].getScaledInstance(SAINT_DIAMETER, SAINT_DIAMETER, 0), (int) coord.getX() - SAINT_RADIUS, (int) coord.getY() - SAINT_RADIUS, null);
                leftStepSwitch = false;
            } else {
                g.drawImage(saintPhases[3].getScaledInstance(SAINT_DIAMETER, SAINT_DIAMETER, 0), (int) coord.getX() - SAINT_RADIUS, (int) coord.getY() - SAINT_RADIUS, null);
                leftStepSwitch = true;
            }
        } else if (isFacingRight && !isCatching && !hasBall) {
            if (rightStepSwitch) {
                g.drawImage(saintPhases[0].getScaledInstance(SAINT_DIAMETER, SAINT_DIAMETER, 0), (int) coord.getX() - SAINT_RADIUS, (int) coord.getY() - SAINT_RADIUS, null);
                rightStepSwitch = false;
            } else {
                g.drawImage(saintPhases[1].getScaledInstance(SAINT_DIAMETER, SAINT_DIAMETER, 0), (int) coord.getX() - SAINT_RADIUS, (int) coord.getY() - SAINT_RADIUS, null);
                rightStepSwitch = true;
            }
        } else if (isReleasingThrow) {
            g.drawImage(saintPhases[6].getScaledInstance(SAINT_DIAMETER, SAINT_DIAMETER, 0), (int) coord.getX() - SAINT_RADIUS, (int) coord.getY() - SAINT_RADIUS, null);
            isReleasingThrow = false;
        } else if (hasBall) {
            if (rightStepThrowingSwitch) {
                g.drawImage(saintPhases[4].getScaledInstance(SAINT_DIAMETER, SAINT_DIAMETER, 0), (int) coord.getX() - SAINT_RADIUS, (int) coord.getY() - SAINT_RADIUS, null);
                rightStepThrowingSwitch = false;
            } else {
                g.drawImage(saintPhases[5].getScaledInstance(SAINT_DIAMETER, SAINT_DIAMETER, 0), (int) coord.getX() - SAINT_RADIUS, (int) coord.getY() - SAINT_RADIUS, null);
                rightStepThrowingSwitch = true;
            }
        } else if (isCatching && !hasBall) {
            g.drawImage(saintPhases[7].getScaledInstance(SAINT_DIAMETER, SAINT_DIAMETER, 0), (int) coord.getX() - SAINT_RADIUS, (int) coord.getY() - SAINT_RADIUS, null);
        }
    }

    /**
     * Retrieve ball.
     *
     * @param ball the ball
     */
    public void retrieveBall(Ball ball) {
        isRetrieving = true;
        isCommandingRetrieval = true;
        this.ball = ball;
    }

    private void commandRetrieval() {
        if (ball != null) {
            ballXCoords = ball.getCenter().getX();
            ballYCoords = ball.getCenter().getY();
            radians = Math.atan2((ballYCoords - getCenter().getY()), (ballXCoords - getCenter().getX()));
            setWalkingByRadians();
        }
    }

    /**
     * Pick up ball.
     *
     * @param ball the ball
     */
    public void pickUpBall(Ball ball) {
        //just in case we picked up a ball on the way to a ball
        ball.setSaintRetriever(this);
        this.ball = ball;
        isRetrieving = false;
        hasBall = true;
    }

    /**
     * Gets ball.
     *
     * @return the ball
     */
    public Ball getBall() {
        return ball;
    }

    /**
     * Sets ball.
     *
     * @param ball the ball
     */
    public void setBall(Ball ball) {
        this.ball = ball;
    }

    /**
     * Gets throwing cadence.
     *
     * @return the throwing cadence
     */
    public int getThrowingCadence() {
        return throwingCadence;
    }

    private void setWalkingByRadians() {
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

}
