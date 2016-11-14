package _07blackjack.blackjack;

import javax.swing.*;

/**
 * Created by michaelmeyer on 11/12/16.
 */
public class BlackJackViewer {

    /**
     * The constant SCREEN_WIDTH.
     */
    public static final int SCREEN_WIDTH = 800;
    /**
     * The constant SCREEN_HEIGHT.
     */
    public static final int SCREEN_HEIGHT = 800;

    /**
     * The entry point of application. This will drive the vending machine GUI.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        JFrame frame = new BlackJackFrame();
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setTitle("BlackJack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
