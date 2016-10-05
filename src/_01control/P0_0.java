package _01control;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 Name your source files according to its Programming Exercise identifier, except replace the period with underscore.
 For example, if the Programming Exercise identifier is P1.15, then name your java source file P1_15
 */
public class P0_0 {

    public static void main(String[] args) throws IOException {

        //Example: Find the max value of an indefinite series of integers entered by the user in the command-line

        //pseudocode
        //while quit is not entered
            //ask user for integer value or type quit to exit
            //store integer value in ArrayList
        //assume the first entry in the ArrayList is the max value
        //for each value in ArrayList - starting with second entry
            //if value > max
                // set max to value
        //print the max value to the console


        fileStuff();



    }

    private static void fileStuff() throws IOException {
        Files.createTempFile("blah", "blah");
    }
}
