package _03objects.P8_5;

import java.text.DecimalFormat;

/**
 * Class representing the attributes of a cylindrical soda can. Computes surface area and volume.
 * Created by michaelmeyer on 10/11/16.
 */
public class SodaCan {

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
     * Computes the surface area of the cylindrical soda can and returns as a double. Will calculate to 4 decimal places.
     * @return the surface area of the soda can.
     */
    public String getSurfaceArea() {
        double dSurfaceArea = (2 * Math.PI * Math.pow(dRadius, 2)) + (2 * Math.PI * dRadius * dHeight);
        DecimalFormat decimalFormat = new DecimalFormat("#.0000");
        return decimalFormat.format(dSurfaceArea);
    }

    /**
     * Computes the volume of the cylindrical soda can and returns as a double. Will calculate to 4 decimal places.
     * @return the volume of the soda can.
     */
    public String getVolume() {
        double dVolume = Math.PI * Math.pow(dRadius, 2) * dHeight;
        DecimalFormat decimalFormat = new DecimalFormat("#.0000");
        return decimalFormat.format(dVolume);
    }

}
