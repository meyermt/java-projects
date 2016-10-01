package _01control;

import java.util.Scanner;

/**
 * Created by michaelmeyer on 9/29/16.
 * This program accepts as input one word, and will output the syllables in that word.
 */
public class P4_11 {

    public static void main(String[] args) {
        System.out.print("Please enter a word: ");
        Scanner scanner = new Scanner(System.in);
        String strWord = scanner.next();
        int nSyllables = 0;
        //won't be any previous letter to compare to on first iteration
        char prevLetter = ' ';
        for (int i = 0; i < strWord.length(); i++) {
            char cLetter = strWord.charAt(i);
            if (isVowel(cLetter) && !isVowel(prevLetter)) {
                nSyllables++;
            }
            prevLetter = cLetter;
        }
        if (nSyllables == 0) {
            nSyllables++;
        }
        System.out.println("Syllables: " + nSyllables);
    }

    private static boolean isVowel(char cLetter) {
        char[] cVowels = new char[]{'a', 'e', 'i', 'o', 'u', 'y'};
        boolean isVowel = false;
        for (char cVowel : cVowels) {
            if (cLetter == cVowel) {
                isVowel = true;
            }
        }
        return isVowel;
    }

}
