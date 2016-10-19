package _04interfaces.P9_10;

import java.awt.*;

/**
 * A superior Rectangle to the one provided by Java (*scoffs at Oracle*). This one will calculate area and perimeter for
 * you!
 * Created by michaelmeyer on 10/18/16.
 */
public class BetterRectangle  extends Rectangle {

    /**
     * Constructs a Rectangle at a specified location with a specified height and width.
     * @param x x coordinate for location
     * @param y y coordinate for location
     * @param width width of the rectangle
     * @param height height of the rectangle
     */
    //I'm not really sure what the point of calling setLocation and setSize was rather than
    //just using the super constructor that sets all of them?
    public BetterRectangle(int x, int y, int width, int height) {
        super.setLocation(x, y);
        super.setSize(width, height);
    }

    /**
     * Returns perimeter measurement using height and width of the rectangle.
     * @return perimeter
     */
    public int getPerimeter() {
        return (2 * width) + (2 * height);
    }

    /**
     * Returns the area measurement using height and width of the rectangle.
     * @return area
     */
    public int getArea() {
        return height * width;
    }
}
