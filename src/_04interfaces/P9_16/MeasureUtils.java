package _04interfaces.P9_16;

import _04interfaces.P9_14.Measurable;

/**
 * Utility class to be used to compute properties in the Measurable interface.
 * Created by michaelmeyer on 10/19/16.
 */
public class MeasureUtils {

    private MeasureUtils() {

    }

    /**
     * Utility method to compute the maximum Measurable object from an array using surface area for comparison
     * @param objects array of Measurable objects to be compared
     * @return the maximum Measurable
     */
    public static Measurable maximum (Measurable[] objects) {
        Measurable max = objects[0];
        for (Measurable object : objects) {
            if (object.getSurfaceArea() > max.getSurfaceArea()) {
                max = object;
            }
        }
        return max;
    }

}
