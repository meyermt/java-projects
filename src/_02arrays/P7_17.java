package _02arrays;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by michaelmeyer on 10/6/16.
 * This program reads some sales data in an input file and produces separate total output files based on category
 */
public class P7_17 {

    /**
     * Main driver for the program. Reads sales input file line by line and parses into string arrays, does some light
     * data validation, and passes
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            File inputFile = new File("sales-input.txt");
            Scanner scanner = new Scanner(inputFile);
            List<String[]> strParsedInputs = new ArrayList<>();
            while (scanner.hasNext()) {
                String[] strParsed = scanner.nextLine().split(";");
                //validate input here
                if (strParsed.length != 4) {
                    System.out.println("Must have 4 inputs per line. please reprocess: " + Arrays.toString(strParsed));
                } else {
                    strParsedInputs.add(strParsed);
                }
            }
            scanner.close();
            Set<String> strCategories = getCategories(strParsedInputs);
            Map<String, List<String>> categoryAndOutput = formatReports(strCategories, strParsedInputs);
            for (Map.Entry<String, List<String>> strCategoryAndEntries : categoryAndOutput.entrySet()) {
                writeToFile(strCategoryAndEntries);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The sales input file could not be found");
            e.printStackTrace();
        }
    }

    /**
     * Gets unique categories from the second array spot in a list of arrays.
     * @param strParsedInputs List of parsed arrays to be searched for categories
     * @return unique set of string categories
     */
    private static Set<String> getCategories(List<String[]> strParsedInputs) {
        Set<String> strDistinctCats = new HashSet<>();
        for (String[] strParsedInput : strParsedInputs) {
                strDistinctCats.add(strParsedInput[1]);
            if (strParsedInput.length != 4) {
                System.out.println("The following input is not in the correct format and will not be processed: " +
                        Arrays.toString(strParsedInput));
            }
        }
        return strDistinctCats;
    }

    /**
     * Formats categories and parsed array of input into logical report format of categories to input lines
     * @param strCategories categories to serve as keys in formatted report
     * @param strParsedInputs string arrayed input. The second array member is the category, which should match one of the
     * categories provided in parameters. Each other array member is a field in the report.
     * @return map representation of formatted report
     */
    private static Map<String, List<String>> formatReports(Set<String> strCategories, List<String[]> strParsedInputs) {
        Map<String, List<String>> strCategoryAndEntries = new HashMap<>();
        for (String strCategory : strCategories) {
            double dTotal = 0.00;
            List<String> strEntries = new ArrayList<>();
            for (String[] strParsedInput : strParsedInputs) {
                if (strCategory.equals(strParsedInput[1])) {
                    System.out.println("Category is: " + strCategory);
                    System.out.println("Match is: " + strParsedInput[1]);
                    dTotal = dTotal + Double.valueOf(strParsedInput[2]);
                    strEntries.add(strParsedInput[0] + ";" +
                        strParsedInput[2] + ";" + strParsedInput[3]);
                }
            }
            BigDecimal bTotal = new BigDecimal(dTotal).setScale(2, BigDecimal.ROUND_HALF_UP);
            strEntries.add("Total: " + bTotal.toString());
            strCategoryAndEntries.put(strCategory, strEntries);
        }
        return strCategoryAndEntries;
    }

    /**
     * Writes category and list of entries to file named same as category in execution base directory.
     * @param strCategoryAndEntries map entry of category to list of print line entries
     */
    private static void writeToFile(Map.Entry<String, List<String>> strCategoryAndEntries) {
        try (PrintWriter writer = new PrintWriter(strCategoryAndEntries.getKey() + ".txt")) {
            for (String outputLine : strCategoryAndEntries.getValue()) {
                writer.println(outputLine);
            }
        } catch (FileNotFoundException e) {
            System.out.println("The output file for this sales program could not be found.");
            e.printStackTrace();
        }
    }


}
