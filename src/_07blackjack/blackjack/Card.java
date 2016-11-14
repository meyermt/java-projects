package _07blackjack.blackjack;

/**
 * Created by michaelmeyer on 11/12/16.
 */
public class Card {

    private final CardValues value;
    private final Suits suit;

    public Card(CardValues value, Suits suit) {
        this.value = value;
        this.suit = suit;
    }

    public CardValues getValue() {
        return value;
    }

    public Suits getSuit() {
        return suit;
    }
}
