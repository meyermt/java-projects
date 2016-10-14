package _03objects.P8_15;

/**
 * Simple implementation of a sphere that will calculate volume and surface.
 * Created by michaelmeyer on 10/13/16.
 */
public class Sphere {

    private final double r;

    /**
     * Constructs sphere from radius.
     * @param r radius
     */
    public Sphere(double r) {
        this.r = r;
    }

    /**
     * Calculates sphere volume and returns as a double.
     * @return sphere volume result
     */
    public double sphereVolume() {
        return (4 * Math.PI * Math.pow(r, 3)) / 3;
    }

    /**
     * Calculates sphere surface and returns as a double.
     * @return sphere surface result
     */
    public double sphereSurface() {
        return 4 * Math.PI * Math.pow(r, 2);
    }

}
