package _07blackjack.yodaspeak;

import java.util.Scanner;

/**
 * Driver for yoda speak. Uses the non-recursive yoda, hence drive labeled as such (NR).
 * Created by michaelmeyer on 11/12/16.
 */
public class YodaSpeakNRDriver {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.print("Please enter a sentence: ");
        Scanner scanner = new Scanner(System.in);
        String sentence = scanner.nextLine();
        IterativeYoda yoda = new IterativeYoda();
        System.out.println(yoda.yoterpret(sentence));
    }

}
