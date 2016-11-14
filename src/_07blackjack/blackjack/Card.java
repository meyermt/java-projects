package _07blackjack.blackjack;

/**
 * Card bean class. Each card is made up of a CardValues and Suits enum, which together identify the card.
 * Created by michaelmeyer on 11/12/16.
 */
public final class Card {

    private final CardValues value;
    private final Suits suit;

    /**
     * Instantiates a new, immutable Card.
     *
     * @param value the value
     * @param suit  the suit
     */
    public Card(CardValues value, Suits suit) {
        this.value = value;
        this.suit = suit;
    }

    /**
     * Gets value of the card.
     *
     * @return the value
     */
    public CardValues getValue() {
        return value;
    }

    /**
     * Gets suit.
     *
     * @return the suit
     */
    public Suits getSuit() {
        return suit;
    }
}
