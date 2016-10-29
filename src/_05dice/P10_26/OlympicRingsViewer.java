package _05dice.P10_26;

import javax.swing.*;

/**
 * Driver class to view olympic rings.
 * Created by michaelmeyer on 10/26/16.
 */
public class OlympicRingsViewer {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent component = new OlympicRingsComponent();
        frame.add(component);
        frame.setVisible(true);
    }

}
