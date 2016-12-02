package _08final.mvc.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by michaelmeyer on 11/30/16.
 */
public class MaxThrowingFloater extends Sprite {

    private final static int FLOATER_DIAMETER = 18;
    private final static int FLOATER_RADIUS = FLOATER_DIAMETER / 2;
    private Random random = new Random();
    private SpriteSheet floaterSheet = new SpriteSheet("throw-floater.png", 9, 8);

    /**
     * Instantiates a new Max throwing floater.
     */
    public MaxThrowingFloater() {
        setTeam(Team.FLOATER);
        int ranX = random.nextInt(545) + 1;
        int ranY = random.nextInt(645) + 1;
        setCenter(new Point(ranX, ranY));
    }

    @Override
    public void move() {}

    @Override
    public void draw(Graphics g) {
        Point coord = getCenter();
        //this only has one sheet member
        BufferedImage throwingFloater = floaterSheet.getSprite(0);
        g.drawImage(throwingFloater.getScaledInstance(FLOATER_DIAMETER, FLOATER_DIAMETER, 0), (int) coord.getX() - FLOATER_RADIUS, (int) coord.getY() - FLOATER_RADIUS, null);
    }
}
