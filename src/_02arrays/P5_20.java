package _02arrays;

import java.util.Scanner;

/**
 * Created by michaelmeyer on 10/5/16.
 */
public class P5_20 {

    /**
     * Driver for the testing of a leap year. User asked for year, program informs whether or not it is a leap year
     * @param args not used in this program
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a year: ");
        boolean isLeapYear = isLeapYear(scanner.nextInt());
        if (isLeapYear) {
            System.out.println("Yep, that's a leap year.");
        } else {
            System.out.println("Sorry, not a leap year.");
        }
    }

    /**
     * A super duper fast method to test if an int is a leap year.
     * @param nYear the year to be tested
     * @return true if the year is a leap year, false if not
     */
    private static boolean isLeapYear(int nYear) {
        if (nYear % 400 == 0) {
            return true;
        } else if (nYear % 100 == 0) {
            return false;
        } else if (nYear % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
