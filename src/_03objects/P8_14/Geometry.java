package _03objects.P8_14;

/**
 * Calculates general geometry measurements for given parameters and returns as a double.
 * Created by michaelmeyer on 10/13/16.
 */
public class Geometry {

    /**
     * Private constructor to stop from static utility class construction
     */
    private Geometry() {
    }

    /**
     * Calculates sphere volume and returns as a double.
     * @param r radius
     * @return sphere volume result
     */
    public static double sphereVolume(double r) {
        return (4 * Math.PI * Math.pow(r, 3)) / 3;
    }

    /**
     * Calculates sphere surface and returns as a double.
     * @param r radius
     * @return sphere surface result
     */
    public static double sphereSurface(double r) {
        return 4 * Math.PI * Math.pow(r, 2);
    }

    /**
     * Calculates cylinder volume and returns as a double.
     * @param r radius
     * @param h height
     * @return cylinder volume result
     */
    public static double cylinderVolume(double r, double h) {
        return Math.PI * Math.pow(r, 2) * h;
    }

    /**
     * Calculates cylinder surface and returns as a double.
     * @param r radius
     * @param h height
     * @return cylinder surface result
     */
    public static double cylinderSurface(double r, double h) {
        return (2 * Math.PI * Math.pow(r, 2)) + (2 * Math.PI * r * h);
    }

    /**
     * Calculates cone volume and returns as a double.
     * @param r radius
     * @param h height
     * @return cone volume result
     */
    public static double coneVolume(double r, double h) {
        return (Math.PI * Math.pow(r, 2) * h) / 3;
    }

    /**
     * Calculates cone surface and returns as a double.
     * @param r radius
     * @param h height
     * @return cone surface result
     */
    public static double coneSurface(double r, double h) {
        double dInner = Math.pow(h, 2) + Math.pow(r, 2);
        return Math.PI * r * (r + (Math.sqrt(dInner)));
    }
}
