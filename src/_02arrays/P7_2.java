package _02arrays;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by michaelmeyer on 10/5/16.
 * This job reads in a file from the its execution directory and writes the input to an output file in the same directory
 * with preceding line numbers
 */
public class P7_2 {

    /**
     * Main driver and only method for the program, which does simple IO for a file, adding line numbers.
     * @param args
     */
    public static void main(String[] args) {
        try {
            File inputFile = new File("input-file.txt");
            Scanner scanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter("output-file.txt");
            int nLine = 1;
            while (scanner.hasNext()) {
                String strLine = scanner.nextLine();
                writer.printf("/* %d */ %s\n", nLine, strLine);
                nLine++;
            }
            scanner.close();
            writer.close();
        } catch (FileNotFoundException e) {
            //we'd probably log a message here but for now just print stacktrace
            e.printStackTrace();
        }
    }

}
