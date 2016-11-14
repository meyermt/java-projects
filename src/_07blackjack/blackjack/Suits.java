package _07blackjack.blackjack;

/**
 * Created by michaelmeyer on 11/12/16.
 */
public enum Suits {

    DIAMONDS("diamonds"),
    CLUBS("clubs"),
    HEARTS("hearts"),
    SPADES("spades"),
    BACK("");

    private final String text;

    private Suits(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
