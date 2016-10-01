package _01control;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by michaelmeyer on 9/29/16.
 * This program takes three input strings and sorts them lexicographically
 */
public class P3_16 {

    public static void main(String[] args) {
        System.out.print("Enter three strings: ");
        Scanner scanner = new Scanner(System.in);
        String strFirst = scanner.next();
        String strSecond = scanner.next();
        String strThird = scanner.next();
        boolean isFirstBeforeSecond = false;
        boolean isFirstBeforeThird = false;
        boolean isSecondBeforeThird = false;
        if (strFirst.compareTo(strSecond) < 0) {
            isFirstBeforeSecond = true;
        }
        if (strFirst.compareTo(strThird) < 0) {
            isFirstBeforeThird = true;
        }

        if (!isFirstBeforeSecond && isFirstBeforeThird) {
            System.out.println(strSecond + " " + strFirst + " " + strThird);
        } else if (isFirstBeforeSecond && !isFirstBeforeThird) {
            System.out.println(strThird + " " + strFirst + " " + strSecond);
        } else {
            //need to do one more comparison to figure out last ordering
            if (strSecond.compareTo(strThird) < 0) {
                isSecondBeforeThird = true;
            }
            if (isFirstBeforeSecond && isFirstBeforeThird && isSecondBeforeThird) {
                System.out.println(strFirst + " " + strSecond + " " + strThird);
            } else if (isFirstBeforeSecond && isFirstBeforeThird && !isSecondBeforeThird) {
                System.out.println(strFirst + " " + strThird + " " + strSecond);
            } else if (!isFirstBeforeSecond && !isFirstBeforeThird && isSecondBeforeThird) {
                System.out.println(strSecond + " " + strThird + " " + strFirst);
            } else {
                System.out.println(strThird + " " + strSecond + " " + strFirst);
            }
        }
    }
}
