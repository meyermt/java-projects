package _06design.P12_6;

import javax.swing.*;

/**
 * Main driver/viewer for the math game.
 * Created by michaelmeyer on 11/3/16.
 */
public class GameViewer {

    /**
     * The constant SCREEN_WIDTH.
     */
    public static final int SCREEN_WIDTH = 800;
    /**
     * The constant SCREEN_HEIGHT.
     */
    public static final int SCREEN_HEIGHT = 400;

    /**
     * The entry point of application. This will drive the game GUI.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        JFrame frame = new GameFrame();
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setTitle("Math Made Fun");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
