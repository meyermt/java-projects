package _01control;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by michaelmeyer on 9/26/16.
 * This program will prompt for a number, then print out list of prime numbers up to that number and including that number,
 * if it is also prime
 */
public class P4_18 {

    public static void main(String[] args) {
        System.out.print("Please enter an integer to get primes leading up to it: ");
        Scanner scanner = new Scanner(System.in);
        int nInput = scanner.nextInt();
        //vet the input first to see if input has primes
        if (nInput < 2) {
            System.out.println("No primes below 2!");
        } else {
            processInput(nInput);
        }
    }

    private static void processInput(int nInput) {
        Set<Integer> nPreviousPrimes = new HashSet<>();
        nPreviousPrimes.add(2);
        System.out.println("2");
        for (int i = 3; i <= nInput; i++) {
            if (isPrime(i, nPreviousPrimes)) {
                System.out.println(i);
                nPreviousPrimes.add(i);
            }
        }
    }

    private static boolean isPrime(int nPrimeCandidate, Set<Integer> nPreviousPrimes) {
        boolean isPrime = true;
        for (Integer nPrime : nPreviousPrimes) {
            //if one of our primes divides i, i can't be a prime
            if (nPrimeCandidate % nPrime == 0) {
                isPrime =  false;
            }
        }
        return isPrime;
    }
}
