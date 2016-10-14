package _03objects.P8_15;

import java.util.Scanner;

/**
 * Driver for geometry using program. Prompts for radius and height input, then calculates various geometric measurements.
 * Created by michaelmeyer on 10/13/16.
 */
public class GeometryUser {

    /*
    Answer to book's question: In this particular example, this is a much more object-oriented approach.
     */

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
        Cone cone = new Cone(r, h);
        System.out.println("Cone surface: " + cone.coneSurface());
        System.out.println("Cone volume: " + cone.coneVolume());
        Cylinder cylinder = new Cylinder(r, h);
        System.out.println("Cylinder surface: " + cylinder.cylinderSurface());
        System.out.println("Cylinder volume: " + cylinder.cylinderVolume());
        Sphere sphere = new Sphere(r);
        System.out.println("Sphere surface: " + sphere.sphereSurface());
        System.out.println("Sphere volume: " + sphere.sphereVolume());
    }


}

