package _03objects.P8_6;

/**
 * Representation of a car. Stores gas quantity and car fuel efficiency. Can simulate driving a given distance and
 * print amount of gas left.
 * Created by michaelmeyer on 10/12/16.
 */
public class Car {

    private final int nFuelEfficiency;
    private int nGasQuantity;

    /**
     * Constructs Car object with given fuel efficiency and gas level of 0.
     * @param nFuelEfficiency miles per gallon the car gets
     */
    public Car(int nFuelEfficiency) {
        this.nFuelEfficiency = nFuelEfficiency;
        nGasQuantity = 0;
    }

    /**
     * Simulates driving by calculating gas used and subtracting it from the car's gas quantity (fuel tank).
     * Uses integer rounding.
     * @param nDistance distance to travel in the car
     */
    public void drive(int nDistance) {
        int nGasUsed = nDistance / nFuelEfficiency;
        nGasQuantity = nGasQuantity - nGasUsed;
    }

    /**
     * Accessor for getting the amount of gas left in the car.
     * @return the amount of gas left in the car
     */
    public int getGasLevel() {
        return nGasQuantity;
    }

    /**
     * Adds some amount of gas to the car's gas quantity (fuel tank).
     * @param nGasAmt the amount of gas to add to the tank
     */
    public void addGas(int nGasAmt) {
        nGasQuantity = nGasQuantity + nGasAmt;
    }

}
