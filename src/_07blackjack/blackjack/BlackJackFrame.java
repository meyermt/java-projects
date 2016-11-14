package _07blackjack.blackjack;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static _07blackjack.blackjack.BlackJackViewer.SCREEN_HEIGHT;
import static _07blackjack.blackjack.BlackJackViewer.SCREEN_WIDTH;

/**
 *
 * Created by michaelmeyer on 11/12/16.
 */
public class BlackJackFrame extends JFrame {

    private static final int INITIAL_BET = 5;
    private static final int STARTING_MONEY = 1000;

    private BlackJackDealer dealer;
    private List<Card> dealerHand = new ArrayList<>();
    private List<Card> playerHand = new ArrayList<>();
    private CardsComponent dealerCards;
    private CardsComponent playerCards;
    private boolean isNewHandAvailable;
    private boolean canDoubleDown;
    private int betAmount;

    private JLabel amountLabel;
    private JLabel winningsLabel;
    private int winnings;

    public BlackJackFrame() {
        betAmount = INITIAL_BET;
        winnings = STARTING_MONEY;
        dealer = new BlackJackDealer();
        isNewHandAvailable = true;
        canDoubleDown = false;
        initDealerPanel();
        initBettingPanel();
        initGamePlayPanel();
        initPlayerPanel();
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setVisible(true);
    }

    private void initGamePlayPanel() {
        JPanel gamePlayPanel = new JPanel();
        gamePlayPanel.setLayout(new BoxLayout(gamePlayPanel, BoxLayout.Y_AXIS));
        gamePlayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setHitButton(gamePlayPanel);
        setStandButton(gamePlayPanel);
        setDoubleDownButton(gamePlayPanel);
        this.add(gamePlayPanel, BorderLayout.CENTER);
    }

    private void setHitButton(JPanel gamePlayPanel) {
        JButton hitButton = new JButton();
        hitButton.setText("Hit");
        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isNewHandAvailable) {
                    JOptionPane.showMessageDialog(null, "No hand to hit on. Must deal cards and start a new round.");
                } else {
                    canDoubleDown = false;
                    playerHand.add(dealer.drawCard());
                    playerCards.setNewPlayerHand(playerHand);
                    if (dealer.isBustedHand(playerHand)) {
                        JOptionPane.showMessageDialog(null, "Oh no, you busted! Dealer wins!");
                        dealerCards.setNewPlayerHand(dealerHand);
                        winnings = winnings - betAmount;
                        winningsLabel.setText("Current Cash: $" + winnings);
                        isNewHandAvailable = true;
                    }
                }
            }
        });
        gamePlayPanel.add(hitButton);
    }

    private void setStandButton(JPanel gamePlayPanel) {
        JButton standButton = new JButton();
        standButton.setText("Stand");
        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isNewHandAvailable) {
                    JOptionPane.showMessageDialog(null, "You will need to start a new round before standing.");
                } else {
                    int playerScore = dealer.addOptimalScore(playerHand);
                    takeDealerTurn(playerScore);
                }
            }
        });
        gamePlayPanel.add(standButton);
    }

    private void takeDealerTurn(int playerScore) {
        dealerHand = dealer.takeTurn(dealerHand);
        dealerCards.setNewPlayerHand(dealerHand);
        int dealerScore = dealer.addOptimalScore(dealerHand);
        if (dealer.isBustedHand(dealerHand)) {
            JOptionPane.showMessageDialog(null, "Dealer busts with a " + dealerScore + "! You win!");
            winnings = winnings + betAmount;
        } else if (playerScore > dealerScore) {
            JOptionPane.showMessageDialog(null, "You got a " + playerScore + ", dealer got a " + dealerScore + ". You owned the dealer! Good hand!");
            winnings = winnings + betAmount;
        } else if (playerScore < dealerScore) {
            JOptionPane.showMessageDialog(null, "You got a " + playerScore + ", dealer got a " + dealerScore + ". Dealer beat you! Tough Luck!");
            winnings = winnings - betAmount;
        } else {
            JOptionPane.showMessageDialog(null, "You got a " + playerScore + ", dealer got a " + dealerScore + ". It's a push!");
        }
        winningsLabel.setText("Current Cash: $" + winnings);
        isNewHandAvailable = true;
    }

    private void setDoubleDownButton(JPanel gamePlayPanel) {
        JButton ddButton = new JButton();
        ddButton.setText("Double Down");
        ddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (canDoubleDown) {
                    betAmount = betAmount * 2;
                    amountLabel.setText("Current Bet: $" + betAmount);
                    if (hasFunds()) {
                        playerHand.add(dealer.drawCard());
                        playerCards.setNewPlayerHand(playerHand);
                        int playerScore = dealer.addOptimalScore(playerHand);
                        takeDealerTurn(playerScore);
                    } else {
                        JOptionPane.showMessageDialog(null, "Not enough funds to double down.");
                    }
                    betAmount = betAmount / 2;
                    amountLabel.setText("Current Bet: $" + betAmount);
                } else {
                    JOptionPane.showMessageDialog(null, "You are not able to double down at this time.");
                }
            }
        });
        gamePlayPanel.add(ddButton);
    }

    private void initDealerPanel() {
        JPanel dealerPanel = new JPanel();
        dealerPanel.setLayout(new BoxLayout(dealerPanel, BoxLayout.Y_AXIS));
        dealerPanel.setSize(new Dimension(800, 300));
        dealerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        dealerCards = new CardsComponent("Dealer");
        dealerCards.setPreferredSize(new Dimension(800, 300));
        dealerPanel.add(dealerCards);
        this.add(dealerPanel, BorderLayout.NORTH);
    }

    private void initBettingPanel() {
        JPanel bettingPanel = new JPanel();
        bettingPanel.setLayout(new BoxLayout(bettingPanel, BoxLayout.Y_AXIS));
        bettingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bettingPanel.setPreferredSize(new Dimension(200, 100));
        setBetFieldButton(bettingPanel);
        setDealButton(bettingPanel);
        setWinningsLabel(bettingPanel);
        this.add(bettingPanel, BorderLayout.WEST);
    }

    private void setWinningsLabel(JPanel bettingPanel) {
        winningsLabel = new JLabel();
        winningsLabel.setText("Current Cash: $" + winnings);
        bettingPanel.add(winningsLabel);
    }

    private void setBetFieldButton(JPanel bettingPanel) {
        amountLabel = new JLabel();
        amountLabel.setText("Current Bet: $" + INITIAL_BET);
        bettingPanel.add(amountLabel);
        JLabel betLabel = new JLabel();
        betLabel.setText("Bet Amount");
        bettingPanel.add(betLabel);
        JTextField betTextField = new JTextField(10);
        betTextField.setText(String.valueOf(INITIAL_BET));
        betTextField.setMaximumSize(new Dimension(200, 20));
        bettingPanel.add(betTextField);
        JButton betButton = new JButton();
        betButton.setText("Set as Bet Amount");
        betButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInt(betTextField.getText()) && isNewHandAvailable) {
                    betAmount = Integer.valueOf(betTextField.getText());
                    amountLabel.setText("Current Bet: " + betAmount);
                } else {
                    JOptionPane.showMessageDialog(null, "Must enter valid bet amount and be before a hand.");
                    betTextField.setText(String.valueOf(INITIAL_BET));
                }
            }
        });
        bettingPanel.add(betButton);
    }

    private boolean isInt(String strInt) {
        try {
            Integer.valueOf(strInt);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void setDealButton(JPanel bettingPanel) {
        JButton dealButton = new JButton();
        dealButton.setText("Deal Hand");
        dealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hasFunds()) {
                    if (isNewHandAvailable) {
                        dealerHand.clear();
                        dealerHand.add(dealer.drawCard());
                        dealerHand.add(dealer.drawCard());
                        dealerCards.setNewDealerHand(dealerHand);
                        playerHand.clear();
                        playerHand.add(dealer.drawCard());
                        playerHand.add(dealer.drawCard());
                        playerCards.setNewPlayerHand(playerHand);
                        isNewHandAvailable = false;
                        canDoubleDown = true;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You are betting more than the funds you have available. Please update your bet amount.");
                }
            }
        });
        bettingPanel.add(dealButton);
    }

    private boolean hasFunds() {
        if (betAmount > winnings) {
            return false;
        } else {
            return true;
        }
    }

    private void initPlayerPanel() {
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setPreferredSize(new Dimension(800, 300));
        playerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        playerCards = new CardsComponent("Player");
        playerCards.setPreferredSize(new Dimension(800, 300));
        playerPanel.add(playerCards);
        this.add(playerPanel, BorderLayout.SOUTH);
    }
}
