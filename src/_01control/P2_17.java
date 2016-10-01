package _01control;

import java.util.Scanner;

/**
 * Created by michaelmeyer on 9/30/16.
 * This program will find the difference between two military times.
 */
public class P2_17 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the first time: ");
        String strTime1 = scanner.next();
        System.out.print("Please enter the second time: ");
        String strTime2 = scanner.next();
        if (strTime1.length() != 4 || strTime2.length() != 4) {
            System.out.println("Time entered should be exactly 4 digits long (e.g., 0900). Please rerun.");
        } else {
            int nHoursDiff = calcHoursDiff(strTime1, strTime2);
            int nMinsDiff = calcMinsDiff(strTime1, strTime2);
            System.out.println(nHoursDiff + " hours " + nMinsDiff + " minutes");
        }
    }

    private static int calcHoursDiff(String strTime1, String strTime2) {
        int nHours1 = Integer.valueOf(strTime1.substring(0, 2));
        int nHours2 = Integer.valueOf(strTime2.substring(0, 2));
        return Math.abs(nHours1 - nHours2);
    }

    private static int calcMinsDiff(String strTime1, String strTime2) {
        int nMins1 = Integer.valueOf(strTime1.substring(2, 4));
        int nMins2 = Integer.valueOf(strTime2.substring(2, 4));
        return Math.abs(nMins1 - nMins2);
    }
}
