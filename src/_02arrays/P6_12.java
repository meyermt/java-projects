package _02arrays;

import java.util.Random;

/**
 * Created by michaelmeyer on 10/5/16.
 * This program generates a sequence of 20 random die tosses, prints the values, and marks runs with parentheses
 */
public class P6_12 {

    /**
     * Driver for the random die run marker program. Randomly generates die rolls and puts parens around runs of two or more.
     * Output is written to standard out.
     * @param args unused for this program.
     */
    public static void main(String[] args) {
        int[] nRandomDieRolls = new int[20];
        Random random = new Random();
        for (int i = 0; i < nRandomDieRolls.length; i++) {
            nRandomDieRolls[i] = random.nextInt(6) + 1;
        }
        boolean inRun = false;
        for (int i = 0; i < nRandomDieRolls.length; i++) {
            if (inRun && i != 0) {
                if (nRandomDieRolls[i] != nRandomDieRolls[i - 1]) {
                    System.out.print(")");
                    inRun = false;
                }
            }
            if (!inRun && i != nRandomDieRolls.length - 1) {
                if (nRandomDieRolls[i] == nRandomDieRolls[i + 1]) {
                    System.out.print("(");
                    inRun = true;
                }
            }
            System.out.print(nRandomDieRolls[i]);
        }
        if (inRun) {
            System.out.print(")");
        }
    }

}
