package _04interfaces.P9_14;

/**
 * Class representing the attributes of a cylindrical soda can. Computes surface area and volume.
 * Created by michaelmeyer on 10/11/16.
 */
public class SodaCan implements Measurable {

    private final double dHeight;
    private final double dRadius;

    /**
     * Construst a soda can using height and radius parameters.
     * @param dHeight height of the soda can
     * @param dRadius radius of the soda can
     */
    public SodaCan(double dHeight, double dRadius) {
        this.dHeight = dHeight;
        this.dRadius = dRadius;
    }

    /**
     * Computes the surface area of the cylindrical soda can and returns as a double.
     * @return the surface area of the soda can.
     */
    public double getSurfaceArea() {
        return (2 * Math.PI * Math.pow(dRadius, 2)) + (2 * Math.PI * dRadius * dHeight);
    }

    /**
     * Computes the volume of the cylindrical soda can and returns as a double. Will calculate to 4 decimal places.
     * @return the volume of the soda can.
     */
    public double getVolume() {
        return Math.PI * Math.pow(dRadius, 2) * dHeight;
    }

}
