package _01control;

import java.util.Scanner;

/**
 * Created by michaelmeyer on 9/30/16.
 * This program translates card shorthand to the full name
 */
public class P3_14 {

    public static void main(String[] args) {
        System.out.print("Enter the card notation: ");
        Scanner scanner = new Scanner(System.in);
        String strInput = scanner.next();
        String strCardSymbol;
        String strSuitSymbol;
        if (strInput.length() > 2) {
            strCardSymbol = strInput.substring(0, 2);
            strSuitSymbol = strInput.substring(2);
        } else {
            strCardSymbol = strInput.substring(0, 1);
            strSuitSymbol = strInput.substring(1);
        }
        String strCardWord = getCardName(strCardSymbol);
        String strSuitWord = getCardSuit(strSuitSymbol);

        System.out.println(strCardWord + " of " + strSuitWord);
    }

    private static String getCardName(String strCardSymbol) {
        String strCardWord;
        switch(strCardSymbol) {
            case "1": strCardWord = "One";
                break;
            case "2": strCardWord = "Two";
                break;
            case "3": strCardWord = "Three";
                break;
            case "4": strCardWord = "Four";
                break;
            case "5": strCardWord = "Five";
                break;
            case "6": strCardWord = "Six";
                break;
            case "7": strCardWord = "Seven";
                break;
            case "8": strCardWord = "Eight";
                break;
            case "9": strCardWord = "Nine";
                break;
            case "10": strCardWord = "Ten";
                break;
            case "J": strCardWord = "Jack";
                break;
            case "Q": strCardWord = "Queen";
                break;
            case "K": strCardWord = "King";
                break;
            case "A": strCardWord = "Ace";
                break;
            default: strCardWord = "No Match";
                break;
        }
        return strCardWord;
    }

    private static String getCardSuit(String strSuitSymbol) {
        String strSuitWord;
        switch(strSuitSymbol) {
            case "S": strSuitWord = "Spades";
                break;
            case "C": strSuitWord = "Clubs";
                break;
            case "H": strSuitWord = "Hearts";
                break;
            case "D": strSuitWord = "Diamonds";
                break;
            default: strSuitWord = "No Match";
                break;
        }
        return strSuitWord;
    }

}
