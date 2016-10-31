package _05dice.pig;

import _05dice.P11_22.TriangleDrawFrame;

import javax.swing.*;

/**
 * Driver for the pig game.
 * Created by michaelmeyer on 10/29/16.
 */
public class PigViewer {

    private static int SCREEN_WIDTH = 660;
    private static int SCREEN_LENGTH = 600;

    /**
     * The entry point of application. Consructs pig frame.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        JFrame pigFrame = new PigFrame();
        pigFrame.setSize(SCREEN_WIDTH, SCREEN_LENGTH);
        pigFrame.setTitle("Game of Pig");
        pigFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pigFrame.setVisible(true);
    }

}
