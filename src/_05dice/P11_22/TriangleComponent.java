package _05dice.P11_22;

import javax.swing.*;
import java.awt.*;

/**
 * Triangle drawing component for the triangle creator. Figures out where it is in the triangle drawing process and draws
 * accordingly.
 * Created by michaelmeyer on 10/28/16.
 */
public class TriangleComponent extends JComponent {

    private int nDotCount;
    private int nXPos1;
    private int nYPos1;
    private int nXPos2;
    private int nYPos2;
    private int nXPos3;
    private int nYPos3;
    private static int DOT_SIZE = 5;
    private static int Y_ALIGN = -20;

    /**
     * Instantiates a new Triangle component. Makes a new triangle and sets visible to false.
     */
    public TriangleComponent() {
        setVisible(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        nDotCount++;
        Graphics2D g2d = (Graphics2D)g;
        //we'll always be drawing the first point
        g2d.fillOval(nXPos1, nYPos1, DOT_SIZE, DOT_SIZE);
        if (nDotCount == 2) {
            g2d.fillOval(nXPos2, nYPos2, DOT_SIZE, DOT_SIZE);
            g2d.drawLine(nXPos1, nYPos1, nXPos2, nYPos2);
        } else if (nDotCount == 3) {
            g2d.fillOval(nXPos2, nYPos2, DOT_SIZE, DOT_SIZE);
            g2d.fillOval(nXPos3, nYPos3, DOT_SIZE, DOT_SIZE);
            g2d.drawLine(nXPos1, nYPos1, nXPos2, nYPos2);
            g2d.drawLine(nXPos2, nYPos2, nXPos3, nYPos3);
            g2d.drawLine(nXPos1, nYPos1, nXPos3, nYPos3);
        } else if (nDotCount > 3) {
            nDotCount = 0;
            drawTrianglePartAt(nXPos1, nYPos1);
        }
    }

    /**
     * Draw triangle part at the x and y coordinates that are given.
     *
     * @param x the x position
     * @param y the y position
     */
    public void drawTrianglePartAt(int x, int y) {
        int newXPos = x;
        int newYPos = y + Y_ALIGN;
        if (nDotCount == 0 || nDotCount > 2) {
            nXPos1 = newXPos;
            nYPos1 = newYPos;
        } else if (nDotCount == 1) {
            nXPos2 = newXPos;
            nYPos2 = newYPos;
        } else if (nDotCount == 2) {
            nXPos3 = newXPos;
            nYPos3 = newYPos;
        }
        setVisible(true);
        repaint();
    }

}
