package _05dice.P11_22;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Frame and event listener for the triangle creation process.
 * Created by michaelmeyer on 10/27/16.
 */
public class TriangleDrawFrame extends JFrame {

    private TriangleComponent triangleComponent;

    /**
     * The type Triangle mouse listener. This listener draws the next phase of the triangle process at the point where
     * the mouse was pressed.
     */
    class TriangleMouseListener implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
            triangleComponent.drawTrianglePartAt(e.getX(), e.getY());
        }

        @Override
        public void mouseClicked(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    }

    /**
     * Instantiates a new Triangle draw frame.
     */
    public TriangleDrawFrame() {
        initDotComponents();
        MouseListener listener = new TriangleMouseListener();
        this.addMouseListener(listener);
    }

    private void initDotComponents() {
        triangleComponent = new TriangleComponent();
        this.add(triangleComponent);
    }

}
