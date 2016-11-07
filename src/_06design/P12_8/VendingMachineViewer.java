package _06design.P12_8;

import _06design.P12_6.GameFrame;

import javax.swing.*;

/**
 * Vending machine viewer class. Main driver for program.
 * Created by michaelmeyer on 11/3/16.
 */
public class VendingMachineViewer {

    /**
     * The constant SCREEN_WIDTH.
     */
    public static final int SCREEN_WIDTH = 800;
    /**
     * The constant SCREEN_HEIGHT.
     */
    public static final int SCREEN_HEIGHT = 600;

    /**
     * The entry point of application. This will drive the vending machine GUI.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        JFrame frame = new VendingMachineFrame();
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setTitle("Vending Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
