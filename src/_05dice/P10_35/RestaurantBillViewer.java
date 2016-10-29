package _05dice.P10_35;

import javax.swing.*;

/**
 * Viewer for restaurant bill GUI.
 * Created by michaelmeyer on 10/26/16.
 */
public class RestaurantBillViewer {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 1000;

    /**
     * The entry point of application. This will drive the restaurant bill GUI.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        JFrame frame = new RestaurantBillFrame();
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
