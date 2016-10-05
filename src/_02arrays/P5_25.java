package _02arrays;

import java.util.Scanner;

/**
 * Created by michaelmeyer on 10/5/16.
 */
public class P5_25 {

    private static final String ZERO_CODE = "||:::";
    private static final String FRAME_BAR = "|";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a zip code to be processed: ");
        String strZip = scanner.nextLine();
        String[] strZipInts = strZip.split("");
        StringBuilder builder = new StringBuilder();
        //Put up a frame before
        builder.append(FRAME_BAR);
        int nCount = 0;
        for (String strZipInt : strZipInts) {
            int nZipInt = Integer.valueOf(strZipInt);
            System.out.println(strZipInt);
            builder.append(intToBar(nZipInt));
            System.out.println(intToBar(Integer.valueOf(strZipInt)));
            nCount = nCount + nZipInt;
        }
        int nEnding = 10 - (nCount % 10);
        builder.append(intToBar(nEnding));
        //frame bar at the end of our barcode
        builder.append(FRAME_BAR);
        System.out.println(builder.toString());
    }

    private static String intToBar(int nInt) {
        if (nInt == 0) {
            return processZero();
        } else {
            return processNonZero(nInt);
        }
    }

    private static String processZero() {
        return ZERO_CODE;
    }

    private static String processNonZero(int nInt) {
        StringBuilder builder = new StringBuilder();
        //tracking the initial value to see if the 0th spot gets a bar
        int nZero = nInt;
        if (nInt - 7 >= 0) {
            nInt = nInt - 7;
            builder.append("|");
        } else {
            builder.append(":");
        }
        if (nInt - 4 >= 0) {
            nInt = nInt - 4;
            builder.append("|");
        } else {
            builder.append(":");
        }
        if (nInt - 2 >= 0) {
            nInt = nInt - 2;
            builder.append("|");
        } else {
            builder.append(":");
        }
        if (nInt - 1 >= 0) {
            nInt = nInt - 1;
            builder.append("|");
        } else {
            builder.append(":");
        }
        if (nZero == 7 || nZero == 4 || nZero == 2 || nZero == 1) {
            builder.append("|");
        } else {
            builder.append(":");
        }
        return builder.toString();
    }
}
