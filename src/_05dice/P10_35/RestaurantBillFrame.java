package _05dice.P10_35;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Restaurant bill that has some pre-selected items as well as the ability to add custom items. User can calculate the bill
 * or reset the bill mid-bill. After calculation, the next item entered will start a new bill.
 * Created by michaelmeyer on 10/26/16.
 */
public class RestaurantBillFrame extends JFrame {

    private final static String[] buttonNames = {"Burger", "Beer", "Shake", "Mutton", "Pie", "Fries", "Soda", "Pasta", "Steak", "Soup"};
    private final static double[] orderedPrices = {4.55, 5.00, 3.45, 10.99, 3.23, 1.89, 1.50, 12.99, 18.99, 4.54};
    private final DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private List<JButton> buttons = new ArrayList<>();
    private JButton resetButton;
    private JButton calculateButton;
    private JButton customButton;
    private double totalBill;
    private JPanel mainPanel;
    private JTextArea totalArea;
    private JTextField customEntryTypeField;
    private JTextField customEntryCostField;

    /**
     * Instantiates a new Restaurant bill frame.
     */
    public RestaurantBillFrame() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.setTitle("Restaurant Bill");
        createButtonComponents();
        initCustomEntry();
        initCalculateAndResetBill();
        initBillLabels();
        this.add(mainPanel);
    }

    private void createButtonComponents() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 2));
        for (int i = 0; i < buttonNames.length; i++) {
            buttons.add(addButton(buttonNames[i], orderedPrices[i], buttonPanel));
        }
        mainPanel.add(buttonPanel);
    }

    private void initBillLabels() {
        totalArea = new JTextArea(100, 10);
        totalArea.setEditable(false);
        mainPanel.add(totalArea);
    }

    private void initCustomEntry() {
        JPanel customEntryPanel = new JPanel();
        customEntryPanel.setLayout(new BoxLayout(customEntryPanel, BoxLayout.X_AXIS));
        JLabel customEntryTypeLabel = new JLabel("Custom Entry Type:");
        customEntryTypeField = new JTextField(50);
        JLabel customEntryCostLabel = new JLabel("Custom Entry Cost:");
        customEntryCostField = new JTextField(50);
        initCustomButton();
        customEntryPanel.add(customEntryTypeLabel);
        customEntryPanel.add(customEntryTypeField);
        customEntryPanel.add(customEntryCostLabel);
        customEntryPanel.add(customEntryCostField);
        customEntryPanel.add(customButton);
        mainPanel.add(customEntryPanel);
    }

    private void initCustomButton() {
        customButton = new JButton();
        customButton.setText("Add to Bill");
        customButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isValidPriceInput(customEntryCostField.getText())) {
                    double dPrice = Double.valueOf(customEntryCostField.getText());
                    printItemPrice(customEntryTypeField.getText(), dPrice);
                } else {
                    JOptionPane.showMessageDialog(null, "You must enter a valid price");
                }
                customEntryCostField.setText("");
                customEntryTypeField.setText("");
            }
        });
    }

    private boolean isValidPriceInput(String strPrice) {
        try {
            Double.valueOf(strPrice);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void initCalculateAndResetBill() {
        JPanel calculateBillpanel = new JPanel();
        initCalculateButton();
        calculateBillpanel.add(calculateButton);
        initResetButton();
        calculateBillpanel.add(resetButton);
        mainPanel.add(calculateBillpanel);
    }

    private void initCalculateButton() {
        calculateButton = new JButton();
        calculateButton.setText("Calculate Total Bill");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalArea.append("Total Bill: " + decimalFormat.format(totalBill) + "\n");
                double dTip = totalBill * .15;
                totalArea.append("Suggested Tip: " + decimalFormat.format(dTip));
            }
        });
    }

    private void initResetButton() {
        resetButton = new JButton();
        resetButton.setText("Reset Bill");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalArea.setText("");
                totalBill = 0;
            }
        });
    }

    private JButton addButton(String strButtonName, double dPrice, JPanel panel) {
        JButton button = new JButton(strButtonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printItemPrice(strButtonName, dPrice);
            }
        });
        panel.add(button);
        return button;
    }

    private void printItemPrice(String strItem, double dPrice) {
        //if we already totalled, the implication is that we are starting a new bill
        if (totalArea.getText().contains("Total Bill")) {
            totalArea.setText("");
            totalBill = 0;
        }
        totalArea.append(strItem + " : $" + decimalFormat.format(dPrice) + "\n");
        totalBill = totalBill + dPrice;
    }

}


