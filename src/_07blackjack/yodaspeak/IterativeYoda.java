package _07blackjack.yodaspeak;

/**
 * An iterative way to reverse the words in a sentence (aka "yoda speak").
 * Created by michaelmeyer on 11/12/16.
 */
public class IterativeYoda {

    /**
     * Instantiates a new Iterative yoda.
     */
    public IterativeYoda() {
    }

    /**
     * Yoterpret takes a string sentence and reverses the words, then returns that as a String sentence. Called yoterpreting.
     *
     * @param sentence input sentence
     * @return reversed words sentence
     */
    public String yoterpret(String sentence) {
        String[] words = sentence.split(" ");
        String[] yodizedWords = new String[words.length];
        int counter = 0;
        for (int i = words.length - 1; i >= 0; i--) {
            yodizedWords[counter] = words[i];
            counter++;
        }
        return String.join(" ", yodizedWords);
    }

}
