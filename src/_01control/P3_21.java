package _01control;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Created by michaelmeyer on 9/29/16.
 * This program computes old style income tax
 */
public class P3_21 {

    public static void main(String[] args) {
        System.out.print("Enter your salary: ");
        Scanner scanner = new Scanner(System.in);
        double dSalary = scanner.nextDouble();
        double dIntRate = 0;
        if (dSalary <= 50000) {
            dIntRate = 0.01;
        } else if (dSalary > 50000 && dSalary <= 75000) {
            dIntRate = 0.02;
        } else if (dSalary > 75000 && dSalary <= 100000) {
            dIntRate = 0.03;
        } else if (dSalary > 100000 && dSalary <= 250000) {
            dIntRate = 0.04;
        } else if (dSalary > 250000 && dSalary <= 500000) {
            dIntRate = 0.05;
        } else if (dSalary > 500000) {
            dIntRate = 0.06;
        } else {
            //potentially also exit here
            System.out.println("Entered incorrect input");
        }
        BigDecimal tax = new BigDecimal(dSalary * dIntRate).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println("Your income tax is: " + tax);
    }

}
