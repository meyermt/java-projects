package _04interfaces.P9_13;

import java.awt.*;

/**
 * Point with x,y coordinates and a label.
 * Created by michaelmeyer on 10/18/16.
 */
public class LabeledPoint {

    private final String label;
    private final Point point;

    /**
     * Constructs LabeledPoint with String label. Stores point coordinates in Java Point class.
     * @param x x coordinate
     * @param y y coordinate
     * @param label label for point
     */
    public LabeledPoint(int x, int y, String label) {
        this.point = new Point(x, y);
        this.label = label;
    }

    /**
     * Returns string representation of labeled point
     * @return string representation of labeled point object
     */
    @Override
    public String toString() {
        return "LabeledPoint{" +
                "label='" + label + '\'' +
                ", point=" + point.toString() +
                '}';
    }
}
