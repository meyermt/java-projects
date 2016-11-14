package _07blackjack.blackjack;

/**
 * The four suits used for traditional cards.
 * Created by michaelmeyer on 11/12/16.
 */
public enum Suits {

    /**
     * Diamonds suits.
     */
    DIAMONDS("diamonds"),
    /**
     * Clubs suits.
     */
    CLUBS("clubs"),
    /**
     * Hearts suits.
     */
    HEARTS("hearts"),
    /**
     * Spades suits.
     */
    SPADES("spades"),
    /**
     * Back suits.
     */
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
