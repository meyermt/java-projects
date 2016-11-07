package _06design.P12_8;

/**
 * Coin representation. From lab code, with a few minor tweaks.
 */
public class Coin {
    private double mValue;
    private Type type;

    /**
     * The enum Type.
     */
    public enum Type {
        /**
         * Dollar type.
         */
        DOLLAR, /**
         * Quarter type.
         */
        QUARTER, /**
         * Dime type.
         */
        DIME, /**
         * Nickel type.
         */
        NICKEL
    }


    /**
     * Instantiates a new Coin.
     *
     * @param value the value
     */
    public Coin(double value) {
        mValue = value;
    }

    /**
     * Instantiates a new Coin.
     *
     * @param type the type
     */
    public Coin(Type type){

        double dVal = 0.0;

        switch (type){
            case DOLLAR:
                dVal = 1.00;
                break;
            case QUARTER:
                dVal = 0.25;
                break;
            case DIME:
                dVal = 0.10;
                break;
            case NICKEL:
                dVal = 0.05;
                break;
            default:
                dVal = 0.00;
                break;

        }

        this.mValue = dVal;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public double getValue() {
        return mValue;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType() { return type; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coin coin = (Coin) o;

        if (Double.compare(coin.mValue, mValue) != 0) return false;
        return type == coin.type;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(mValue);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}

