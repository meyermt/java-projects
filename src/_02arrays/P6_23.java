package _02arrays;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by michaelmeyer on 10/5/16.
 * This program takes an input of captions followed by values and will output a bar graph that shows bars in asterisks
 * with relative values to each other. Max length of bar is 40.
 */
public class P6_23 {

    private static final double MAX_BAR_LENGTH = 40.00;
    private static final String BAR_GRAPHIC = "*";

    /**
     * Main driver for the program. Takes input and passes control to the main bar graph construction method.
     * @param args unused for this program.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter captions followed by values for bar chart separated by spaces " +
                "(e.g., vikings 100 lions 30 packers 3): ");
        String[] strFields = scanner.nextLine().split(" ");
        List<String> strCaptions = new ArrayList<>();
        List<Double> nValues = new ArrayList<>();
        for (int i = 0; i < strFields.length; i++) {
            if (i % 2 == 0) {
                strCaptions.add(strFields[i]);
            } else {
                nValues.add(Double.valueOf(strFields[i]));
            }
        }
        constructBarChart(strCaptions, nValues);
    }

    /**
     * Main construction driver for the bar graph.
     * @param strCaptions captions that will be written to the left of each bar
     * @param nValues value associated with each caption
     */
    private static void constructBarChart(List<String> strCaptions, List<Double> nValues) {
        double nMaxFactor = MAX_BAR_LENGTH / getMax(nValues);
        for (int i = 0; i < strCaptions.size(); i++) {
            //System.out.print(strCaptions.get(i) + " ");
            int nWholeStars = Double.valueOf(nValues.get(i) * nMaxFactor).intValue();
            System.out.printf("%20s ", strCaptions.get(i));
            drawBars(nWholeStars);
            System.out.println("");
        }
    }

    /**
     * Draws the asterisk bars. If a bar is only of factor 0, then it will still get one asterisk.
     * @param nLength number of asterisks to write to bar
     */
    private static void drawBars(int nLength) {
        if (nLength == 0) {
            nLength = 1;
        }
        for (int i = 0; i < nLength; i++) {
            System.out.print(BAR_GRAPHIC);
        }
    }


    /**
     * Retrieves the max value of all the values that will be graphed
     * @param nValues list of values to be evaluated
     * @return max value
     */
    private static double getMax(List<Double> nValues) {
        double nMax = 0;
        for (Double nValue : nValues) {
            if (nValue.doubleValue() > nMax) {
                nMax = nValue.doubleValue();
            }
        }
        return nMax;
    }
}
