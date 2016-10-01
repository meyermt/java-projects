package _01control;

import java.util.Scanner;

/**
 * Created by michaelmeyer on 9/29/16.
 * This program finds the minimum value from a set of inputs
 */
public class P4_6 {

//    Set a Boolean variable "first" to true.
//    While another value has been read successfully
//      If first is true
//          Set the minimum to the value.
//          Set first to false.
//      Else if the value is less than the minimum
//          Set the minimum to the value.
//    Print the minimum.

    public static void main(String[] args) {

        System.out.println("Please enter integers (separated by space), and lowest will be returned. ");
        System.out.print("Type any non-integer value to quit and find lowest value: ");
        Scanner scanner = new Scanner(System.in);
        boolean first = true;
        int minimum = 0;
        while (scanner.hasNextInt()) {
            int nValue = scanner.nextInt();
            if (first) {
                minimum = nValue;
                first = false;
            } else if (nValue < minimum) {
                minimum = nValue;
            }
        }
        System.out.println(minimum);
    }

}
