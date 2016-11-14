package _07blackjack.blackjack;

/**
 * Created by michaelmeyer on 11/12/16.
 */
public enum CardValues {

    ACE("ace_of_", 1),
    TWO("2_of_", 2),
    THREE("3_of_", 3),
    FOUR("4_of_", 4),
    FIVE("5_of_", 5),
    SIX("6_of_", 6),
    SEVEN("7_of_", 7),
    EIGHT("8_of_", 8),
    NINE("9_of_", 9),
    TEN("10_of_", 10),
    JACK("jack_of_", 10),
    QUEEN("queen_of_", 10),
    KING("king_of_", 10),
    BACK("cardback", 0);

    private final String text;
    private final int value;

    private CardValues(String text, int value) {
        this.text = text;
        this.value = value;
    }

    public int getIntValue() {
        return value;
    }

    @Override
    public String toString() {
        return text;
    }

}
