package _07blackjack.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by michaelmeyer on 11/12/16.
 */
public class BlackJackDealer {

    private static final int BLACKJACK = 21;
    private static final int DECKS = 6;
    private List<Card> shoe = new ArrayList<>();
    private int index;

    public BlackJackDealer() {
        populateShoe();
        Collections.shuffle(shoe);
        index = 0;
    }

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

    private void newShoe() {
        index = 0;
        Collections.shuffle(shoe);
    }

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
