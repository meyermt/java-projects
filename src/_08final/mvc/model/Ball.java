package _08final.mvc.model;

import _08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by michaelmeyer on 11/1/16.
 */
public class Ball extends Sprite {

    private SpriteSheet ballSheet = new SpriteSheet("ball.png", 9, 8);
    private final static int BALL_DIAMETER = 18;
    private final static int BALL_RADIUS = BALL_DIAMETER / 2;
    private final static double AIR_FRICTION = .3;
    private final int uid;
    private double radians;
    private double ballSpeed;
    public boolean isMoving;
    public boolean flyingRight;
    public boolean flyingUp;
    public boolean hasFoeRetriever;
    private Sprite thrower;
    private Random random = new Random();

    /**
     * Three ways balls are constructed. One is initial center line. Uses this constructor. this requires knowledge of ball position
     * and total number of balls.
     * @param ballPos
     * @param numBalls
     */
    public Ball(int ballPos, int numBalls) {
        uid = ballPos;
        setTeam(Team.NEUTRAL);
        int ballYPos = (Game.ARENA_HEIGHT / (numBalls + 1)) * ballPos;
        setCenter(new Point((Game.ARENA_WIDTH / 2), ballYPos));
        setRadius(BALL_RADIUS);
        isMoving = false;
        hasFoeRetriever = false;
    }

    public Ball(int uid, Point center) {
        this.uid = uid;
        setTeam(Team.NEUTRAL);
        setCenter(center);
        setRadius(BALL_RADIUS);
        isMoving = false;
        hasFoeRetriever = false;
    }

    /**
     * The other construction, which assumes this ball is being thrown by a sprite of some sort
     * @param sprite
     */
    public Ball(int uid, Sprite sprite, int mouseX, int mouseY) {
        this.uid = uid;
        setThrower(sprite);
        setTeam(sprite.getTeam());
        setRadius(BALL_RADIUS);
        setCenter(new Point((int) sprite.getCenter().getX(), (int) sprite.getCenter().getY()));
        ballSpeed = sprite.getThrowingSpeed();
        radians = Math.atan2((mouseY - getCenter().getY()), (mouseX - getCenter().getX()));
        setDeltaX( Math.cos(radians) * ballSpeed );
        setDeltaY( Math.sin(radians) * ballSpeed );
        setDirection();
        hasFoeRetriever = false;
    }

    public void setThrower(Sprite thrower) {
        this.thrower = thrower;
    }

    public Sprite getThrower() {
        return thrower;
    }

    @Override
    public void move() {
        if (ballSpeed > 1) {
            //some random slowing equation
            ballSpeed = ballSpeed - (AIR_FRICTION);
            Point pnt = getCenter();
            //this just keeps the sprite inside the bounds of the frame
            if (pnt.x + BALL_DIAMETER > Game.ARENA_WIDTH && flyingRight) {
                radians = Math.PI - radians;
                flyingRight = false;
            } else if (pnt.x - BALL_DIAMETER < 0 && !flyingRight) {
                radians = Math.PI - radians;
                flyingRight = true;
            } else if (pnt.y + BALL_DIAMETER + 18 > Game.ARENA_HEIGHT && !flyingUp) {
                radians = (2 * Math.PI) - radians;
                flyingUp = true;
            } else if (pnt.y - BALL_DIAMETER < 0 && flyingUp) {
                radians = (2 * Math.PI) - radians;
                flyingUp = false;
            }
            setDeltaX(Math.cos(radians) * ballSpeed);
            setDeltaY(Math.sin(radians) * ballSpeed);
            double dX = pnt.x + getDeltaX();
            double dY = pnt.y + getDeltaY();
            setCenter(new Point((int) dX, (int) dY));
        } else {
            ballSpeed = 0;
            isMoving = false;
        }
    }

    public void randomFlight() {
        Point pnt = getCenter();
        radians = random.nextDouble() * 2;
        setDeltaX( Math.cos(radians) * ballSpeed );
        setDeltaY( Math.sin(radians) * ballSpeed );
        setDirection();
    }

    @Override
    public void draw(Graphics g) {
        Point coord = getCenter();
        //ball only has one sheet member
        BufferedImage ball = ballSheet.getSprite(0);
        g.drawImage(ball.getScaledInstance(BALL_DIAMETER, BALL_DIAMETER, 0), (int) coord.getX() - BALL_RADIUS, (int) coord.getY() - BALL_RADIUS, null);
    }

    private void setDirection() {
        if (Math.PI / 2 > Math.abs(radians)) {
            flyingRight = true;
        } else {
            flyingRight = false;
        }

        if (radians < 0) {
            flyingUp = true;
        } else {
            flyingUp = false;
        }
        isMoving = true;
    }

    public int getUID() {
        return uid;
    }
}
