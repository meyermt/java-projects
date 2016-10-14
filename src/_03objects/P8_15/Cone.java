package _03objects.P8_15;

/**
 * Simple implementation of a cone that calculates volume and surface from radius and height.
 * Created by michaelmeyer on 10/13/16.
 */
public class Cone {

    private final double r;
    private final double h;

    /**
     * Constructs cone from radius and height.
     * @param r radius
     * @param h height
     */
    public Cone(double r, double h) {
        this.r = r;
        this.h = h;
    }

    /**
     * Calculates cone volume and returns as a double.
     * @return cone volume result
     */
    public double coneVolume() {
        return (Math.PI * Math.pow(r, 2) * h) / 3;
    }

    /**
     * Calculates cone surface and returns as a double.
     * @return cone surface result
     */
    public double coneSurface() {
        double dInner = Math.pow(h, 2) + Math.pow(r, 2);
        return Math.PI * r * (r + (Math.sqrt(dInner)));
    }
}
