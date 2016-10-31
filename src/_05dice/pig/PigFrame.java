package _05dice.pig;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Frame for pig game. Controls the view for the game.
 * Created by michaelmeyer on 10/29/16.
 */
public class PigFrame extends JFrame {

    private JPanel infoPanel;
    private JPanel buttonPanel;
    private JPanel currentRoundPanel;
    private JPanel scorePanel;
    private JButton startButton;
    private JButton rollButton;
    private JButton holdButton;
    private JTextArea currentRound;
    private JLabel currentRoundLabel;
    private JLabel playerScoreLabel;
    private JLabel computerScoreLabel;

    private int nPlayerScore;
    private int nComputerScore;
    private int nRunningTotal;

    private Random random;
    private PigAIEngine pigAIEngine;

    private boolean isPlayerTurn;
    private boolean isFirstRoll;

    /**
     * Instantiates a new Pig frame. Constructs all the view elements and sets game to initial state.
     */
    public PigFrame() {
        initInfoPanel();
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initStartButton();
        initRollButton();
        initHoldButton();
        this.add(buttonPanel, BorderLayout.WEST);
        initCurrentRound();
        initScoreLabels();
        pigAIEngine = new PigAIEngine();
        random = new Random();
        isPlayerTurn = true;
        isFirstRoll = true;
    }

    /*
        Creates the info panel which shows instructions for playing the game.
     */
    private void initInfoPanel() {
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        //infoPanel.setMaximumSize(new Dimension(400, 200));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("Welcome to the dice game. The rules are simple. You will take turns going against the computer \n" +
                "rolling the dice to get to 100. A turn consists of rolling the dice one to many times, adding each roll \n" +
                "as points to the player's point total. If at any time a 1 is rolled, the player loses their turn and no \n" +
                "points are added to their total. The first player to 100 wins!");
        infoPanel.add(textArea);
        this.add(infoPanel, BorderLayout.NORTH);
    }

    /*
        Creates the start new game button.
     */
    private void initStartButton() {
        startButton = new JButton();
        startButton.setText("Start New Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });
        buttonPanel.add(startButton);
    }

    /*
        Creates the roll button for human player.
     */
    private void initRollButton() {
        rollButton = new JButton();
        rollButton.setText("Roll Dice");
        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isFirstRoll) {
                    currentRound.setText("");
                    currentRoundLabel.setText("Current Round Total: 0");
                    isFirstRoll = false;
                }
                if (isPlayerTurn) {
                    int nRoll = rollRandomDice();
                    currentRound.append(nRoll + "\n");
                    if (nRoll == 1) {
                        currentRound.append("");
                        nRunningTotal = 0;
                        currentRoundLabel.setText("Current Round Total: 0");
                        isPlayerTurn = false;
                        JOptionPane.showMessageDialog(null, "You rolled a 1. Computer's Turn.");
                        takeComputerTurn();
                    } else {
                        nRunningTotal = nRunningTotal + nRoll;
                        currentRoundLabel.setText("Current Round Total: " + nRunningTotal);
                    }
                }
            }
        });
        buttonPanel.add(rollButton);
    }

    /*
        Creates the hold button for human player.
     */
    private void initHoldButton() {
        holdButton = new JButton();
        holdButton.setText("Hold (End Turn)");
        holdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isPlayerTurn) {
                    nPlayerScore = nPlayerScore + nRunningTotal;
                    playerScoreLabel.setText("Player Score: " + nPlayerScore);
                    if (nPlayerScore < 100) {
                        nRunningTotal = 0;
                        isPlayerTurn = false;
                        takeComputerTurn();
                    } else {
                        JOptionPane.showMessageDialog(null, "Congrats, you won the game!");
                        startNewGame();
                    }
                }
            }
        });
        buttonPanel.add(holdButton);
    }

    /*
        Creates the current round components, including text area and current points label.
     */
    private void initCurrentRound() {
        currentRoundPanel = new JPanel();
        currentRoundPanel.setLayout(new BoxLayout(currentRoundPanel, BoxLayout.Y_AXIS));
        currentRoundPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 50, 10));
        currentRoundLabel = new JLabel();
        JPanel currentRoundLabelPanel = new JPanel();
        currentRoundLabel.setText("Current Round Total: 0");
        currentRoundLabel.setHorizontalAlignment(SwingConstants.LEFT);
        currentRoundLabelPanel.add(currentRoundLabel);
        currentRound = new JTextArea();
        currentRound.setBackground(Color.PINK);
        currentRoundPanel.add(currentRoundLabelPanel);
        currentRoundPanel.add(currentRound);
        add(currentRoundPanel);
    }

    /*
        Creates the labels which show scores for humand and computer players.
     */
    private void initScoreLabels() {
        scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 100));
        playerScoreLabel = new JLabel();
        playerScoreLabel.setText("Player Score: 0");
        scorePanel.add(playerScoreLabel);
        computerScoreLabel = new JLabel();
        computerScoreLabel.setText("Computer Score: 0");
        scorePanel.add(computerScoreLabel);
        add(scorePanel, BorderLayout.EAST);
    }

    /*
        returns random number 1-6
     */
    private int rollRandomDice() {
        return random.nextInt(6) + 1;
    }

    /*
        Logic for computer to take their turn. Uses the AI engine to determine how many turns to take.
     */
    private void takeComputerTurn() {
        int nRoll = rollRandomDice();
        nRunningTotal = nRunningTotal + nRoll;
        currentRound.setText(nRoll + "\n");
        currentRoundLabel.setText("Current Round Total: " + nRunningTotal);
        while (nRoll != 1 && pigAIEngine.willRollAgain()
            && (nComputerScore + nRunningTotal < 100)) {
            nRoll = rollRandomDice();
            nRunningTotal = nRunningTotal + nRoll;
            currentRoundLabel.setText("Current Round Total: " + nRunningTotal);
            currentRound.append(nRoll + "\n");
        }
        if (nRoll == 1) {
            nRunningTotal = 0;
            currentRoundLabel.setText("Current Round Total: 0");
        }
        nComputerScore = nComputerScore + nRunningTotal;
        computerScoreLabel.setText("Computer Score: " + nComputerScore);
        if (nComputerScore >= 100) {
            JOptionPane.showMessageDialog(null, "You let the computer win. You are a disgrace to humanity.");
            startNewGame();
        }
        currentRound.append("Computer gets " + nRunningTotal + ".\n");
        currentRound.append("Your turn.");
        nRunningTotal = 0;
        isPlayerTurn = true;
        isFirstRoll = true;
    }

    /*
        Resets all relevant variables and labels to start a new game.
     */
    private void startNewGame() {
        nRunningTotal = 0;
        nPlayerScore = 0;
        nComputerScore = 0;
        currentRoundLabel.setText("Current Round Total: 0");
        currentRound.setText("");
        playerScoreLabel.setText("Player Score: 0");
        computerScoreLabel.setText("Computer Score: 0");
        isPlayerTurn = true;
        isFirstRoll = true;
    }
}
