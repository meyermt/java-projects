package _06design.P12_8;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Vending machine representation. This class handles all the core activities of a vending machine, and stores the state
 * of the products and money in the machine at any particular time.
 * Created by michaelmeyer on 11/3/16.
 */
public class VendingMachine {

    /**
     * The constant sDecimalFormat.
     */
    public static DecimalFormat sDecimalFormat = new DecimalFormat("$0.00");
    private static final String PASSWORD = "iamadmin";

    private Map<Product, Integer> productAndQuantity;

    private Product selectedProduct;
    private Map<Coin, Integer> vendingMachineRegister;
    private Map<Coin, Integer> insertedMoney;
    private boolean isUnlocked;

    /**
     * Instantiates a new Vending machine.
     *
     * @param productAndQuantity     the product and quantity
     * @param vendingMachineRegister the vending machine register
     */
    public VendingMachine(Map<Product, Integer> productAndQuantity, Map<Coin, Integer> vendingMachineRegister) {
        this.productAndQuantity = productAndQuantity;
        this.vendingMachineRegister = vendingMachineRegister;
        insertedMoney = new HashMap<>();
        isUnlocked = false;
    }

    /**
     * Insert coin.
     *
     * @param coin the coin
     */
    public void insertCoin(Coin coin) {
        if (insertedMoney.containsKey(coin)) {
            System.out.println("got here");
            int quantity = insertedMoney.get(coin);
            quantity++;
            insertedMoney.put(coin, quantity);
        } else {
            System.out.println("got here instead");
            insertedMoney.put(coin, 1);
        }
    }

    /**
     * Gets inserted money.
     *
     * @return the inserted money
     */
    public Map<Coin, Integer> getInsertedMoney() {
        return insertedMoney;
    }

    /**
     * Gets selected product.
     *
     * @return the selected product
     */
    public Product getSelectedProduct() {
        return selectedProduct;
    }

    /**
     * Gets inserted total.
     *
     * @return the inserted total
     */
    public double getInsertedTotal() {
        return addCoins(insertedMoney);
    }

    /**
     * Gets product list.
     *
     * @return the product list
     */
    public List<Product> getProductList() {
        List<Product> products = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : productAndQuantity.entrySet()) {
            products.add(entry.getKey());
        }
        return products;
    }

    /**
     * Select product.
     *
     * @param product the product
     */
    public void selectProduct(Product product) {
        selectedProduct = product;
    }

    /**
     * Attempts purchase of product with inserted coins. If there are sufficient funds and the product is not sold out,
     * the product is returned. If it is sold out, a SoldOutException is returned. If insufficient funds, an
     * InsufficientFundsException is returned.
     *
     * @return the purchased product
     * @throws InsufficientFundsException when there are not enough funds to purchase product
     * @throws SoldOutException           when product is sold out
     */
    public Product tryPurchaseProduct() throws InsufficientFundsException, SoldOutException {
        if (isSufficientFunds()) {
            if (!isProductSoldOut()) {
                for (Map.Entry<Coin, Integer> entry : insertedMoney.entrySet()) {
                    int quantity = vendingMachineRegister.get(entry.getKey());
                    quantity = quantity + entry.getValue();
                    vendingMachineRegister.put(entry.getKey(), quantity);
                }
                int productCount = productAndQuantity.get(selectedProduct);
                productCount--;
                productAndQuantity.put(selectedProduct, productCount);
                insertedMoney.clear();
                return selectedProduct;
            }else {
                insertedMoney.clear();
                throw new SoldOutException("Product " + selectedProduct.getDesc() + " is sold out.");
            }
        } else {
            insertedMoney.clear();
            throw new InsufficientFundsException("User only entered " + sDecimalFormat.format(addCoins(insertedMoney)) +
                    " and product costs" + sDecimalFormat.format(selectedProduct.getPrice()));
        }
    }

    /**
     * Is product sold out boolean.
     *
     * @return the boolean
     */
    public boolean isProductSoldOut() {
        if (productAndQuantity.get(selectedProduct).intValue() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Is sufficient funds boolean.
     *
     * @return the boolean
     */
    public boolean isSufficientFunds() {
        if (addCoins(insertedMoney) >= selectedProduct.getPrice()) {
            return true;
        } else {
            return false;
        }
    }

    private double addCoins(Map<Coin, Integer> coins) {
        double dMoney = 0;
        for (Map.Entry<Coin, Integer> entry : coins.entrySet()) {
            dMoney = dMoney + entry.getKey().getValue() * entry.getValue();
        }
        return dMoney;
    }

    /**
     * Restock.
     *
     * @param product the product
     */
    public void restock(Product product) {
        if (isUnlocked) {
            if (productAndQuantity.get(product) != null) {
                int newQuantity = productAndQuantity.get(product) + 1;
                productAndQuantity.put(product, newQuantity);
            } else {
                productAndQuantity.put(product, 1);
            }
            productAndQuantity.get(product);
        }
    }

    /**
     * Remove money coin.
     *
     * @param coin the coin
     * @return the coin
     */
    public Coin removeMoney(Coin coin) {
        if (isUnlocked) {
            if (vendingMachineRegister.get(coin) != null) {
                int newQuantity = vendingMachineRegister.get(coin) - 1;
                vendingMachineRegister.put(coin, newQuantity);
                return coin;
            }
        }
        return null;
    }

    /**
     * Unlock operator functions.
     *
     * @param password the password
     */
    public void unlockOperatorFunctions(String password) {
        if (password.equals(PASSWORD)) {
            isUnlocked = true;
        }
    }

    /**
     * Lock operator functions.
     */
    public void lockOperatorFunctions() {
        isUnlocked = false;
    }

    /**
     * Is operator function unlocked boolean.
     *
     * @return the boolean
     */
    public boolean isOperatorFunctionUnlocked() {
        return isUnlocked;
    }

}
