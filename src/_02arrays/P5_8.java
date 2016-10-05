package _02arrays;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by michaelmeyer on 10/5/16.
 * This program will read words and randomly scramble two of the characters, so long as they are not first or last
 */
public class P5_8 {

    private static final int WORD_ENDS = 2;
    private static final int SHORT_WORD = 2;
    private static final int PAST_FIRST_LETTER = 1;

    /**
     * Driver for the program. Takes one line of space delimited input and sends to be scrambled.
     * @param args not used in this program
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter words to scramble: ");
        String strWordString = scanner.nextLine();
        String[] strWordS = strWordString.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String strWord : strWordS) {
            builder.append(scramble(strWord)).append(" ");
        }
        System.out.println(builder.toString().trim());
    }

    /**
     * Scrambles two inner characters of a string that is greater than two characters long. Characters are chosen at random.
     * @param string the string to be scrambled
     * @return the scrambled string
     */
    private static String scramble(String string) {
        String strNew = string;
        if (string.length() > SHORT_WORD) {
            StringBuilder builder = new StringBuilder();
            Random random = new Random();
            int nScrambleRange = string.length() - WORD_ENDS;
            int nChar1 = random.nextInt(nScrambleRange) + PAST_FIRST_LETTER;
            int nChar2 = random.nextInt(nScrambleRange) + PAST_FIRST_LETTER;
            while (nChar1 == nChar2) {
                nChar2 = random.nextInt(nScrambleRange) + PAST_FIRST_LETTER;
            }
            for (int i = 0; i < string.length(); i++) {
                if (i == nChar1) {
                    builder.append(string.charAt(nChar2));
                } else if (i == nChar2) {
                    builder.append(string.charAt(nChar1));
                } else  {
                    builder.append(string.charAt(i));
                }
            }
            strNew = builder.toString();
        }
        return strNew;
    }

}
