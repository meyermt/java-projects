package _03objects.P8_11;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple implementation of a letter. Constructs with sender and recipient, then provides methods to format and print out
 * the final letter.
 * Created by michaelmeyer on 10/12/16.
 */
public class Letter {

    private final String from;
    private final String to;
    private List<String> strLines = new ArrayList<>();
    private final String strGreeting;
    private static final String SINCERELY = "Sincerely,";

    /**
     * Constructs a letter with sender and recipient.
     * @param from sender of the letter
     * @param to recipient of the letter
     */
    public Letter(String from, String to) {
        this.from = from;
        this.to = to;
        this.strGreeting = "Dear " + to + ":";
    }

    /**
     * Adds a line to the letter.
     * @param strLine line of text to be added to the letter.
     */
    public void addLine (String strLine) {
        strLines.add(strLine);
    }

    /**
     * Creates letter from recipient, sender, and any added lines.
     * @return string representation of completed letter
     */
    public String getText() {
        StringBuilder builder = new StringBuilder();
        builder.append(strGreeting + "\n\n");
        for (String strLine : strLines) {
            builder.append(strLine + "\n");
        }
        builder.append("\n");
        builder.append(SINCERELY);
        builder.append("\n");
        builder.append(from);
        return builder.toString();
    }

    /**
     * Driver method of creating letters.
     * @param args
     */
    public static void main(String[] args) {
        Letter letter = new Letter("John", "Mary");
        letter.addLine("I am sorry we must part.");
        letter.addLine("I wish you all the best.");
        System.out.println(letter.getText());
    }

}
