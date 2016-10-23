package _04interfaces.P9_16;

import _04interfaces.P9_14.Measurable;

/**
 * Representation of a country. Must have a name and a surface area.
 * Created by michaelmeyer on 10/19/16.
 */
public class Country implements Measurable {

    private final String strName;
    private final double dSurfaceArea;

    /**
     * Constructs Country with name of country and surface area.
     * @param dSurfaceArea
     */
    public Country(String strName, double dSurfaceArea) {
        this.strName = strName;
        this.dSurfaceArea = dSurfaceArea;
    }

    public String getName() {
        return strName;
    }

    @Override
    public double getSurfaceArea() {
        return dSurfaceArea;
    }

    @Override
    public String toString() {
        return "Country{" +
                "strName='" + strName + '\'' +
                ", dSurfaceArea=" + dSurfaceArea +
                '}';
    }
}
