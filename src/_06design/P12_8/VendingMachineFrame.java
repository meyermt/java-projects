package _06design.P12_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.*;

import static _06design.P12_8.VendingMachineViewer.SCREEN_WIDTH;
import static _06design.P12_8.VendingMachineViewer.SCREEN_HEIGHT;

/**
 * Vending machine viewer, This class handles most of the visual representation of the vending machine, and delegates any
 * of the logic of an actual vending machine to the VendingMachine class.
 * Created by michaelmeyer on 11/3/16.
 */
public class VendingMachineFrame extends JFrame {

    private VendingMachine vendingMachine;
    private static final String MANDMS = "M&Ms";
    private static final String SNICKERS = "Snickers";
    private static final String SKITTLES = "Skittles";
    private static final String COKE = "Coke";
    private static final String PEPSI = "Pepsi";
    private static final String DORITOS = "Doritos";
    private static final String RITZ = "Ritz";
    private static final String FRITOS = "Fritos";
    private static final String APPLE = "Apple";
    private static final double MANDMPRICE = .85;
    private static final double SNICKERSPRICE = .75;
    private static final double SKITTLESPRICE = .95;
    private static final double COKEPRICE = 1.65;
    private static final double PEPSIPRICE = 1.45;
    private static final double DORITOSPRICE = 1.55;
    private static final double RITZPRICE = .90;
    private static final double FRITOSPRICE = 1.85;
    private static final double APPLEPRICE = .35;

    private java.util.List<JButton> productButtons = new ArrayList<>();
    private JLabel insertedMoneyLabel;
    private JLabel selectedProductLabel;


    /**
     * Instantiates a new Vending machine frame.
     */
    public VendingMachineFrame() {
        initStockAndRegister();
        initProductButtonComponents();
        initCoinButtonComponents();
        initProductButtons();
        initOperatorButton();
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setVisible(true);
    }

    private void initOperatorButton() {
        JPanel operatorPanel = new JPanel();
        operatorPanel.setLayout(new BoxLayout(operatorPanel, BoxLayout.Y_AXIS));
        operatorPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 380, 10));
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("Enter Operator password:");
        operatorPanel.add(passwordLabel);
        JTextField passwordField = new JTextField(10);
        operatorPanel.add(passwordField);
        JButton operatorButton = new JButton();
        operatorButton.setText("Operator Unlock");
        operatorPanel.add(operatorButton);
        operatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vendingMachine.isOperatorFunctionUnlocked()) {
                    vendingMachine.lockOperatorFunctions();
                    JOptionPane.showMessageDialog(null, "Machine locked and ready for use.");
                    operatorButton.setText("Operator Unlock");
                } else {
                    vendingMachine.unlockOperatorFunctions(passwordField.getText());
                    if (vendingMachine.isOperatorFunctionUnlocked()) {
                        JOptionPane.showMessageDialog(null, "Operator mode entered. Select product button to add products to " +
                                "machine and coin buttons to remove those coins from the machine.");
                        passwordField.setText("");
                        operatorButton.setText("Lock machine.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect Password. Machine not unlocked.");
                        passwordField.setText("");
                    }
                }
            }
        });
        this.add(operatorPanel, BorderLayout.EAST);
    }

    private void initCoinButtonComponents() {
        JPanel coinButtonPanel = new JPanel();
        coinButtonPanel.setLayout(new BoxLayout(coinButtonPanel, BoxLayout.Y_AXIS));
        coinButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for (Coin.Type coin : Coin.Type.values()) {
            addCoinButton(coin, coinButtonPanel);
        }
        insertedMoneyLabel = new JLabel();
        insertedMoneyLabel.setText("Inserted Money: $0.00");
        coinButtonPanel.add(insertedMoneyLabel);
        this.add(coinButtonPanel, BorderLayout.WEST);
    }

    private void initProductButtons() {
        JPanel productButtonPanel = new JPanel();
        productButtonPanel.setLayout(new BoxLayout(productButtonPanel, BoxLayout.Y_AXIS));
        productButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        selectedProductLabel = new JLabel();
        selectedProductLabel.setText("Selected Product: None");
        productButtonPanel.add(selectedProductLabel);
        JButton purchaseButton = new JButton();
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vendingMachine.isOperatorFunctionUnlocked()) {
                    JOptionPane.showMessageDialog(null, "Cannot vend product while in operator mode.");
                } else {
                    if (vendingMachine.getSelectedProduct() != null) {
                        try {
                            Product product = vendingMachine.tryPurchaseProduct();
                            JOptionPane.showMessageDialog(null, "Your product, " + product.getDesc() + ", has been vended." +
                                    "Thanks for your purchase.");
                        } catch (InsufficientFundsException e1) {
                            JOptionPane.showMessageDialog(null, "Insufficient Funds. Your money has been returned.");
                        } catch (SoldOutException e2) {
                            JOptionPane.showMessageDialog(null, "That product is sold out. Your money has been returned.");
                        }
                        insertedMoneyLabel.setText("Inserted Money: $0.00");
                        selectedProductLabel.setText("Selected Product: None");
                    } else {
                        JOptionPane.showMessageDialog(null, "Please choose a product first");
                    }
                }
            }
        });
        purchaseButton.setText("Purchase Product");
        productButtonPanel.add(purchaseButton);
        this.add(productButtonPanel, BorderLayout.CENTER);
    }

    private JButton addCoinButton(Coin.Type coinType, JPanel panel) {
        JButton button = new JButton(coinType.name());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vendingMachine.isOperatorFunctionUnlocked()) {
                    Coin coin = vendingMachine.removeMoney(new Coin(coinType));
                    if (coin == null) {
                        JOptionPane.showMessageDialog(null, "No more of that coin type left in register.");
                    }
                } else {
                    vendingMachine.insertCoin(new Coin(coinType));
                    double insertedTotal = vendingMachine.getInsertedTotal();
                    insertedMoneyLabel.setText("Inserted Money: " + VendingMachine.sDecimalFormat.format(insertedTotal));
                }
            }
        });
        panel.add(button);
        return button;
    }

    private void initProductButtonComponents() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 3));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for (Product product : vendingMachine.getProductList()) {
            productButtons.add(addProductButton(product, buttonPanel));
        }
        this.add(buttonPanel, BorderLayout.NORTH);
    }

    private JButton addProductButton(Product product, JPanel panel) {
        JButton button = new JButton(product.getDesc() + " : " + VendingMachine.sDecimalFormat.format(product.getPrice()));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (vendingMachine.isOperatorFunctionUnlocked()) {
                    vendingMachine.restock(product);
                } else {
                    vendingMachine.selectProduct(product);
                    selectedProductLabel.setText(("Selected Product: " + product.getDesc()));
                }
            }
        });
        panel.add(button);
        return button;
    }

    private void initStockAndRegister() {
        Map<Product, Integer> productAndQuantity = new HashMap<>();
        Product product1 = new Product(MANDMS, MANDMPRICE);
        Product product2 = new Product(SNICKERS, SNICKERSPRICE);
        Product product3 = new Product(SKITTLES, SKITTLESPRICE);
        Product product4 = new Product(COKE, COKEPRICE);
        Product product5 = new Product(PEPSI, PEPSIPRICE);
        Product product6 = new Product(DORITOS, DORITOSPRICE);
        Product product7 = new Product(RITZ, RITZPRICE);
        Product product8 = new Product(FRITOS, FRITOSPRICE);
        Product product9 = new Product(APPLE, APPLEPRICE);
        productAndQuantity.put(product1, 5);
        productAndQuantity.put(product2, 5);
        productAndQuantity.put(product3, 5);
        productAndQuantity.put(product4, 5);
        productAndQuantity.put(product5, 5);
        productAndQuantity.put(product6, 5);
        productAndQuantity.put(product7, 5);
        productAndQuantity.put(product8, 5);
        productAndQuantity.put(product9, 5);
        Map<Coin, Integer> register = new HashMap<>();
        register.put(new Coin(Coin.Type.DOLLAR), 10);
        register.put(new Coin(Coin.Type.QUARTER), 10);
        register.put(new Coin(Coin.Type.DIME), 10);
        register.put(new Coin(Coin.Type.NICKEL), 10);
        vendingMachine = new VendingMachine(productAndQuantity, register);
    }
}
