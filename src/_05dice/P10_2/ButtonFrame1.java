package _05dice.P10_2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Simple button that shows number of times pressed.
 */
public class ButtonFrame1 extends JFrame {

    private static final int FRAME_WIDTH = 200;
    private static final int FRAME_HEIGHT = 60;
    //being explicit with initial zero setting
    private int n = 0;
    private JButton button;

    /**
     * Instantiates a new Button frame 1.
     */
    public ButtonFrame1() {
        createComponents();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    /**
     * The type Click listener. This is the event listener for mouse clicks on the button.
     */
    class ClickListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("I was clicked.");
            n++;
            button.setText("I was clicked " + n + " times!");
        }
    }

    /**
     * A set of init actions for the button frame.
     */
    private void createComponents() {
        button = new JButton("I was clicked " + n + " times!");
        JPanel panel = new JPanel();
        panel.add(button);
        add(panel);

        ActionListener listener = new ClickListener();
        button.addActionListener(listener);
    }

}
