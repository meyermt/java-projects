package _05dice.P10_26;

import javax.swing.*;
import java.awt.*;

/**
 * Olympic rings component. Paints the olympic rings.
 * Created by michaelmeyer on 10/26/16.
 */
public class OlympicRingsComponent extends JComponent {

    /**
     * Instantiates a new Olympic rings component.
     */
    public OlympicRingsComponent() {}

    @Override
    public void paintComponent(Graphics g) {
        drawRing(40, 40, Color.BLUE, g);
        drawRing(200, 40, Color.BLACK, g);
        drawRing(360, 40, Color.RED, g);
        drawRing(120, 120, Color.YELLOW, g);
        drawRing(280, 120, Color.GREEN, g);
    }

    /*
        Draws a ring for the olympics rings drawing.
     */
    //solution for drawing ring thickness:
    //http://stackoverflow.com/questions/16995308/can-you-increase-line-thickness-when-using-java-graphics-for-an-applet-i-dont
    private void drawRing(int x, int y, Color color, Graphics g) {
        g.setColor(color);
        ((Graphics2D) g).setStroke(new BasicStroke(10));
        g.drawOval(x, y, 150, 150);
    }

}
