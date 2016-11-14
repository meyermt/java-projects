package _07blackjack.yodaspeak;

import java.util.Scanner;

/**
 * Driver for recursive yoda. Takes in string sentence input and asks RecursiveYoda to yoterpret it.
 * Created by michaelmeyer on 11/12/16.
 */
public class YodaSpeakDriver {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.print("Please enter a sentence: ");
        Scanner scanner = new Scanner(System.in);
        String sentence = scanner.nextLine();
        RecursiveYoda yoda = new RecursiveYoda();
        System.out.println(yoda.yoterpret(sentence));
    }
}
