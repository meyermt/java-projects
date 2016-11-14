package _07blackjack.blackjack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaelmeyer on 11/12/16.
 */
public class CardsComponent extends JComponent {

    private List<Image> images = new ArrayList<>();
    private String name;

    public CardsComponent(String name) {
        this.name = name;
    }

    public void setNewDealerHand(List<Card> hand) {
        images.clear();
        for (int i = 0; i < hand.size(); i++) {
            if (i == 0) {
                Image img = sizeCard(readCard(new Card(CardValues.BACK, Suits.BACK)));
                images.add(img);
            } else {
                Image img = sizeCard(readCard(hand.get(i)));
                images.add(img);
            }
        }
        setVisible(true);
        repaint();
    }

    public void setNewPlayerHand(List<Card> hand) {
        images.clear();
        for (int i = 0; i < hand.size(); i++) {
            Image img = sizeCard(readCard(hand.get(i)));
            images.add(img);
        }
        setVisible(true);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 10;
        int y = 10;
        for (Image image : images) {
            g.drawImage(image, x, y, null);
            x = x + 85;
        }
        //new experimental
        g.drawChars(name.toCharArray(), 0, 6, 10, 275);
    }

    private Image readCard(Card card) {
        try {
            return ImageIO.read(new File("playingcards/" + card.getValue().toString() + card.getSuit().toString() + ".png"));
        } catch (IOException e) {
            throw new RuntimeException("Can't read the cards");
        }
    }

    private Image sizeCard(Image image) {
        return image.getScaledInstance(75, 100, 0);
    }

}
