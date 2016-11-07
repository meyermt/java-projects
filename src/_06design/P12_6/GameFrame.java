package _06design.P12_6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static _06design.P12_6.GameViewer.SCREEN_WIDTH;
import static _06design.P12_6.GameViewer.SCREEN_HEIGHT;

/**
 * Viewable frame for math game.
 * Created by michaelmeyer on 11/3/16.
 */
public class GameFrame extends JFrame {

    /**
     * The enum Levels.
     */
    public enum Levels {
        /**
         * Level 1 levels.
         */
        LEVEL_1, /**
         * Level 2 levels.
         */
        LEVEL_2, /**
         * Level 3 levels.
         */
        LEVEL_3 };
    private QuestionEngine questionEngine;
    private JPanel infoPanel;
    private JPanel levelPointsPanel;
    private JPanel questionPanel;
    private JPanel answerPanel;
    private JLabel levelLabel;
    private JLabel pointsLabel;
    private JLabel questionLabel;
    private JTextField answerField;
    private JButton submitButton;

    private int totalPoints;
    private Levels level;
    private int wrongAnswers;

    /**
     * Instantiates a new Game frame.
     */
    public GameFrame() {
        questionEngine = new QuestionEngine();
        level = Levels.LEVEL_1;
        initInfoPanel();
        initLevelPointsPanel();
        initQuestionPanel();
        initAnswerPanel();
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setVisible(true);
    }

    private void initInfoPanel() {
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("Welcome to the math learning extravaganza. You will be asked math problems and be \n" +
                "given a point for all correct answers! You will have two attempts to get the problem right. Every 5 points,\n" +
                "you will move up a level. The goal of the game is to get past level 3. Good luck!");
        infoPanel.add(textArea);
        this.add(infoPanel, BorderLayout.NORTH);
    }

    private void initLevelPointsPanel() {
        levelPointsPanel = new JPanel();
        levelPointsPanel.setLayout(new BoxLayout(levelPointsPanel, BoxLayout.Y_AXIS));
        levelPointsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        levelLabel = new JLabel();
        levelLabel.setText("Level: 1");
        //partially used code from here
        //http://stackoverflow.com/questions/2715118/how-to-change-the-size-of-the-font-of-a-jlabel-to-take-the-maximum-size
        levelLabel.setFont(new Font(levelLabel.getFont().getName(), Font.PLAIN, 24));
        levelPointsPanel.add(levelLabel);
        pointsLabel = new JLabel();
        pointsLabel.setText("Points: 0");
        pointsLabel.setFont(new Font(pointsLabel.getFont().getName(), Font.PLAIN, 24));
        levelPointsPanel.add(pointsLabel);
        this.add(levelPointsPanel, BorderLayout.WEST);
    }

    private void initQuestionPanel() {
        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        questionLabel = new JLabel();
        questionLabel.setText(questionEngine.generateNewQuestion(Levels.LEVEL_1));
        questionLabel.setFont(new Font(questionLabel.getFont().getName(), Font.PLAIN, 24));
        questionPanel.add(questionLabel);
        this.add(questionPanel, BorderLayout.CENTER);
    }

    private void initAnswerPanel() {
        answerPanel = new JPanel();
        answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.Y_AXIS));
        answerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 100, 10));
        JLabel answerLabel = new JLabel();
        answerLabel.setText("Answer:");
        answerLabel.setFont(new Font(answerLabel.getFont().getName(), Font.PLAIN, 24));
        answerPanel.add(answerLabel);
        answerField = new JTextField(10);
        answerField.setFont(new Font(answerField.getFont().getName(), Font.PLAIN, 24));
        answerPanel.add(answerField);
        submitButton = new JButton();
        submitButton.setText("Submit Answer");
        submitButton.setFont(new Font(answerField.getFont().getName(), Font.PLAIN, 24));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValidAnswer(answerField.getText())) {
                    int nAnswer = Integer.valueOf(answerField.getText());
                    if (nAnswer == questionEngine.getAnswer()) {
                        totalPoints++;
                        wrongAnswers = 0;
                        pointsLabel.setText("Points: " + totalPoints);
                        JOptionPane.showMessageDialog(null, "Correct answer! Good job!");
                        if (totalPoints == 5) {
                            level = Levels.LEVEL_2;
                            levelLabel.setText("Level: 2");
                        } else if (totalPoints == 10) {
                            level = Levels.LEVEL_3;
                            levelLabel.setText("Level: 3");
                        } else if (totalPoints == 15) {
                            JOptionPane.showMessageDialog(null, "Also, you won the game! Play again!");
                            totalPoints = 0;
                            level = Levels.LEVEL_1;
                            pointsLabel.setText("Points: 0");
                            levelLabel.setText("Level: 1");
                        }
                        questionLabel.setText(questionEngine.generateNewQuestion(level));
                    } else {
                        wrongAnswers++;
                        if (wrongAnswers == 2) {
                            JOptionPane.showMessageDialog(null, "Wrong answer, try a new question");
                            wrongAnswers = 0;
                            questionLabel.setText(questionEngine.generateNewQuestion(level));
                        } else {
                            JOptionPane.showMessageDialog(null, "Wrong answer, try again!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You must enter a valid answer (must be a number)");
                }
                answerField.setText("");
            }
        });
        answerPanel.add(submitButton);
        this.add(answerPanel, BorderLayout.EAST);
    }

    private boolean isValidAnswer(String answer) {
        try {
            Integer.valueOf(answer);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
