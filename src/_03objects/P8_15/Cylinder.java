package _03objects.P8_15;

/**
 * Simple implementation of cylinder. Calculates volume and surface from radius and height.
 * Created by michaelmeyer on 10/13/16.
 */
public class Cylinder {

    private final double r;
    private final double h;

    /**
     * Constructs cylinder from radius and height
     * @param r radius
     * @param h height
     */
    public Cylinder(double r, double h) {
        this.r = r;
        this.h = h;
    }

    /**
     * Calculates cylinder volume and returns as a double.
     * @return cylinder volume result
     */
    public double cylinderVolume() {
        return Math.PI * Math.pow(r, 2) * h;
    }

    /**
     * Calculates cylinder surface and returns as a double.
     * @return cylinder surface result
     */
    public double cylinderSurface() {
        return (2 * Math.PI * Math.pow(r, 2)) + (2 * Math.PI * r * h);
    }
}
