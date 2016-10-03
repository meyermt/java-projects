package _01control;

import java.util.Map;
import java.util.Scanner;

/**
 * Created by michaelmeyer on 9/29/16.
 * This program converts number input into roman numberals
 */
public class P3_26 {

    public static void main(String[] args) {
        System.out.print("Enter your number to be converted to Roman numeral: ");
        Scanner scanner = new Scanner(System.in);
        int nInput = scanner.nextInt();
        if (nInput > 3999 || nInput < 0) {
            System.out.println("No Roman Numeral for this number");
        } else {
            System.out.println(convertWholeToNumeral(nInput));
        }
    }

    private static String convertWholeToNumeral(int nInput) {
        String strRomanFinal = "";
        int nThousand = nInput / 1000;
        if (nThousand > 0) {
           for (int i = 1; i <= nThousand; i++) {
               strRomanFinal = strRomanFinal.concat("M");
           }
        }
        int nHundreds = (nInput % 1000) / 100;
        if (nHundreds > 0) {
            String strRomanHunds = convertSingleToNumerals(nHundreds, "C", "D", "M");
            strRomanFinal = strRomanFinal.concat(strRomanHunds);
        }
        int nTens = (nInput % 100) / 10;
        if (nTens > 0) {
            String strRomanTens = convertSingleToNumerals(nTens, "X", "L", "C");
            strRomanFinal = strRomanFinal.concat(strRomanTens);
        }
        int nOnes = nInput % 10;
        String strRomanOnes = convertSingleToNumerals(nOnes, "I", "V", "X");
        return strRomanFinal.concat(strRomanOnes);
    }

    private static String convertSingleToNumerals(int nNumber, String strOne, String strFive, String strTen) {
        String strRoman;
        switch (nNumber) {
            case 0:
                strRoman = "";
                break;
            case 1:
                strRoman = strOne;
                break;
            case 2:
                strRoman = strOne.concat(strOne);
                break;
            case 3:
                strRoman = strOne.concat(strOne).concat(strOne);
                break;
            case 4:
                strRoman = strOne.concat(strFive);
                break;
            case 5:
                strRoman = strFive;
                break;
            case 6:
                strRoman = strFive.concat(strOne);
                break;
            case 7:
                strRoman = strFive.concat(strOne).concat(strOne);
                break;
            case 8:
                strRoman = strFive.concat(strOne).concat(strOne).concat(strOne);
                break;
            case 9:
                strRoman = strOne.concat(strTen);
                break;
            default:
                strRoman = "Error";
        }
        return strRoman;
    }
}
