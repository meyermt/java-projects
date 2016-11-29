package _08final.mvc.model;

import _08final.mvc.controller.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by michaelmeyer on 11/1/16.
 */
public class Ball extends Sprite {

    private SpriteSheet ballSheet = new SpriteSheet("ball.png", 9, 8);
    private final static int BALL_DIAMETER = 18;
    private final static int BALL_RADIUS = BALL_DIAMETER / 2;
    private double radians;
    private double ballSpeed;
    public boolean isMoving;
    public boolean flyingRight;
    public boolean flyingUp;
    public boolean hasFoeRetriever = false;

    /**
     * Three ways balls are constructed. One is initial center line. Uses this constructor. this requires knowledge of ball position
     * and total number of balls.
     * @param ballPos
     * @param numBalls
     */
    public Ball(int ballPos, int numBalls) {
        setTeam(Team.NEUTRAL);
        int ballYPos = (Game.DIM.height / (numBalls + 1)) * ballPos;
        setCenter(new Point((Game.DIM.width / 2), ballYPos));
        setRadius(BALL_RADIUS);
        isMoving = false;
    }

    public Ball(Point center) {
        setTeam(Team.NEUTRAL);
        setCenter(center);
        setRadius(BALL_RADIUS);
        isMoving = false;
    }

    /**
     * The other construction, which assumes this ball is being thrown by a sprite of some sort
     * @param sprite
     */
    public Ball(Sprite sprite, int mouseX, int mouseY) {
        setTeam(sprite.getTeam());
        setRadius(BALL_RADIUS);
        setCenter(new Point((int) sprite.getCenter().getX(), (int) sprite.getCenter().getY()));
        ballSpeed = sprite.getThrowingSpeed();
        radians = Math.atan2((mouseY - getCenter().getY()), (mouseX - getCenter().getX()));
        setDeltaX( Math.cos(radians) * ballSpeed );
        setDeltaY( Math.sin(radians) * ballSpeed );
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

    @Override
    public void move() {
        if (ballSpeed > 1) {
            //some random slowing equation
            ballSpeed = ballSpeed - (.1);
            Point pnt = getCenter();
            //this just keeps the sprite inside the bounds of the frame
            if (pnt.x + BALL_DIAMETER > Game.DIM.getWidth() && flyingRight) {
                radians = Math.PI - radians;
                flyingRight = false;
            } else if (pnt.x - BALL_DIAMETER < 0 && !flyingRight) {
                radians = Math.PI - radians;
                flyingRight = true;
            } else if (pnt.y + BALL_DIAMETER + 18 > Game.DIM.getHeight() && !flyingUp) {
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

    @Override
    public void draw(Graphics g) {
        Point coord = getCenter();
        //ball only has one sheet member
        BufferedImage ball = ballSheet.getSprite(0);
        g.drawImage(ball.getScaledInstance(BALL_DIAMETER, BALL_DIAMETER, 0), (int) coord.getX() - BALL_RADIUS, (int) coord.getY() - BALL_RADIUS, null);
    }
}
