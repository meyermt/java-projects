package _06design.P12_8;

/**
 * Product representation. From lab code with some minor tweaks.
 */
public class Product {
    private String mDesc;
    private double mPrice;


    /**
     * Instantiates a new Product.
     *
     * @param desc  the desc
     * @param price the price
     */
    public Product(String desc, double price) {
        mDesc = desc;
        mPrice = price;
    }

    /**
     * Gets desc.
     *
     * @return the desc
     */
    public String getDesc() {
        return mDesc;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return mPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (Double.compare(product.mPrice, mPrice) != 0) return false;
        return mDesc != null ? mDesc.equals(product.mDesc) : product.mDesc == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = mDesc != null ? mDesc.hashCode() : 0;
        temp = Double.doubleToLongBits(mPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}