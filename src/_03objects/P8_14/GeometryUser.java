package _03objects.P8_14;

import java.util.Scanner;

/**
 * Driver for geometry using program. Prompts for radius and height input, then calculates various geometric measurements.
 * Created by michaelmeyer on 10/13/16.
 */
public class GeometryUser {

    /**
     * Driver method for the program. Prompts for input and writes output to standard out.
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a radius and height separated by a space: ");
        String[] strInputs = scanner.nextLine().split(" ");
        double r = Integer.valueOf(strInputs[0]);
        double h = Integer.valueOf(strInputs[1]);
        System.out.println("Cone surface: " + Geometry.coneSurface(r, h));
        System.out.println("Cone volume: " + Geometry.coneVolume(r, h));
        System.out.println("Cylinder surface: " + Geometry.cylinderSurface(r, h));
        System.out.println("Cylinder volume: " + Geometry.cylinderVolume(r, h));
        System.out.println("Sphere surface: " + Geometry.sphereSurface(r));
        System.out.println("Sphere volume: " + Geometry.sphereVolume(r));
    }


}
