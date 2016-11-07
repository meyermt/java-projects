package _06design.P12_8;

/**
 * An exception thrown from the vending machine when insufficient funds are used to purchase a product.
 * Created by michaelmeyer on 11/4/16.
 */
public class InsufficientFundsException extends Exception {
    /**
     * Instantiates a new Insufficient funds exception.
     */
    public InsufficientFundsException() {
    }

    /**
     * Instantiates a new Insufficient funds exception.
     *
     * @param message the message
     */
    public InsufficientFundsException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Insufficient funds exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Insufficient funds exception.
     *
     * @param cause the cause
     */
    public InsufficientFundsException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Insufficient funds exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public InsufficientFundsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
