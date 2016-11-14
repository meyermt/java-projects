package _07blackjack.blackjack;

/**
 * All card values for traditional set of cards.
 * Created by michaelmeyer on 11/12/16.
 */
public enum CardValues {

    /**
     * Ace card values.
     */
    ACE("ace_of_", 1),
    /**
     * Two card values.
     */
    TWO("2_of_", 2),
    /**
     * Three card values.
     */
    THREE("3_of_", 3),
    /**
     * Four card values.
     */
    FOUR("4_of_", 4),
    /**
     * Five card values.
     */
    FIVE("5_of_", 5),
    /**
     * Six card values.
     */
    SIX("6_of_", 6),
    /**
     * Seven card values.
     */
    SEVEN("7_of_", 7),
    /**
     * Eight card values.
     */
    EIGHT("8_of_", 8),
    /**
     * Nine card values.
     */
    NINE("9_of_", 9),
    /**
     * Ten card values.
     */
    TEN("10_of_", 10),
    /**
     * Jack card values.
     */
    JACK("jack_of_", 10),
    /**
     * Queen card values.
     */
    QUEEN("queen_of_", 10),
    /**
     * King card values.
     */
    KING("king_of_", 10),
    /**
     * Back card values.
     */
    BACK("cardback", 0);

    private final String text;
    private final int value;

    private CardValues(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * Gets int value. This can be used when adding scores for each card.
     *
     * @return the int value
     */
    public int getIntValue() {
        return value;
    }

    @Override
    public String toString() {
        return text;
    }

}
