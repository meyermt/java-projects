package _06design.P12_8;

/**
 * Exception for when a particular product is sold out.
 * Created by michaelmeyer on 11/4/16.
 */
public class SoldOutException extends Exception {
    /**
     * Instantiates a new Sold out exception.
     */
    public SoldOutException() {
    }

    /**
     * Instantiates a new Sold out exception.
     *
     * @param message the message
     */
    public SoldOutException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Sold out exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public SoldOutException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Sold out exception.
     *
     * @param cause the cause
     */
    public SoldOutException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Sold out exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public SoldOutException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
