package _07blackjack.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The BlackJackDealer handles all actions from the "house", as well as acts as a helper class for adding up optimal scores
 * or informing of busts.
 * Created by michaelmeyer on 11/12/16.
 */
public class BlackJackDealer {

    private static final int BLACKJACK = 21;
    private static final int DECKS = 6;
    private List<Card> shoe = new ArrayList<>();
    private int index;

    /**
     * Instantiates a new Black jack dealer. Each new instance populates the shoe and shuffles the cards.
     */
    public BlackJackDealer() {
        populateShoe();
        Collections.shuffle(shoe);
        index = 0;
    }

    /**
     * Draw card from the shoe.
     *
     * @return the card that has been drawn.
     */
    public Card drawCard() {
        if (index < shoe.size()) {
            Card card = shoe.get(index);
            index++;
            return card;
        } else {
            newShoe();
            Card card = shoe.get(index);
            index++;
            return card;
        }
    }

    /**
     * Is busted hand boolean. This informs of whether a hand that is being played has gone over 21.
     *
     * @param hand the hand
     * @return the boolean. True if busted.
     */
    public boolean isBustedHand(List<Card> hand) {
        int total = 0;
        for (Card card : hand) {
            total = total + card.getValue().getIntValue();
        }
        if (total > BLACKJACK) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Add optimal score. This method will find the highest possible total score of a hand passed in. It is important to
     * note that the method assumes there is at least one permutation of value additions that is less than 21. It will
     * handle figuring out how to use a collection of aces to achieve the highest score less than or equal to 21, though.
     *
     * @param hand the hand
     * @return the int of the highest possible score under 21.
     */
    public int addOptimalScore(List<Card> hand) {
        int total = 0;
        List<Card> aces = new ArrayList<>();
        for (Card card : hand) {
            if (card.getValue().getIntValue() == 1) {
                aces.add(card);
            }
            total = total + card.getValue().getIntValue();
        }
        for (Card ace : aces) {
            if (total + 10 <= BLACKJACK) {
                total = total + 10;
            }
        }
        return total;
    }

    /**
     * Take turn list. This will take a dealer-view turn.
     *
     * @param hand the hand
     * @return the list of cards at the end of the turn
     */
    public List<Card> takeTurn(List<Card> hand) {
        int score = addOptimalScore(hand);
        if (score == 17 && (hand.get(0).getValue() == CardValues.ACE || hand.get(1).getValue() == CardValues.ACE)) {
            hand.add(drawCard());
        }
        while (addOptimalScore(hand) < 17) {
            hand.add(drawCard());
        }
        return hand;
    }

    /*
        convenience method to get a new shoe once the current one has been dealt through.
     */
    private void newShoe() {
        index = 0;
        Collections.shuffle(shoe);
    }

    /*
        initial population of the shoe. Can set to as many decks as desired.
     */
    private void populateShoe() {
        for (int i = 0; i < DECKS; i++) {
            for (CardValues value : CardValues.values()) {
                if (value != CardValues.BACK) {
                    for (Suits suit : Suits.values()) {
                        if (suit != Suits.BACK) {
                            Card card = new Card(value, suit);
                            shoe.add(card);
                        }
                    }
                }
            }
        }
    }

}
