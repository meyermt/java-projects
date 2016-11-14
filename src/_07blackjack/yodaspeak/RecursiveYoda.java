package _07blackjack.yodaspeak;

/**
 * A recursive way to reverse the words in a sentence (aka "yoda speak").
 * Created by michaelmeyer on 11/12/16.
 */
public class RecursiveYoda {

    /**
     * Instantiates a new Recursive yoda.
     */
    public RecursiveYoda() {}

    /**
     * Yoterpret takes a string sentence and reverses the words, then returns that as a String sentence. Called yoterpreting.
     *
     * @param sentence input sentence
     * @return reversed words sentence
     */
    public String yoterpret(String sentence) {
        String[] words = sentence.split(" ");
        int index = 0;
        String[] yodaWords = new String[words.length];
        return yoterpret(words, yodaWords, index);
    }

    /*
        This is the recursive helper method. It will terminate at the midpoint, switching array members on each end as it
        gets closer to that point. yodaWords and the string array are not class members, and thus must be passed in with
        each iteration.
     */
    private String yoterpret(String[] words, String[] yodaWords, int index) {
        //this marks when we have passed the midpoint
        if (words.length - index <= index) {
            return String.join(" ", yodaWords);
        } else {
            yodaWords[index] = words[words.length - index - 1];
            yodaWords[words.length - index - 1] = words[index];
            return yoterpret(words, yodaWords, index + 1);
        }
    }
}
