package _05dice.P11_22;

import javax.swing.*;

/**
 * Triangle creator that allows user to click three spots to create a connect-the-dots triangle.
 * Created by michaelmeyer on 10/27/16.
 */
public class TriangleViewer {

    private static int SCREEN_WIDTH = 400;
    private static int SCREEN_LENGTH = 400;

    /**
     * The entry point of application. This will be the viewer and driver for our triangle creation program.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        JFrame triangleFrame = new TriangleDrawFrame();
        triangleFrame.setSize(SCREEN_WIDTH, SCREEN_LENGTH);
        triangleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        triangleFrame.setVisible(true);
    }

}
