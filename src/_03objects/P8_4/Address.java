package _03objects.P8_4;

import com.sun.istack.internal.Nullable;

/**
 * Holds address information. Can compare two addresses to tell which is before the other (by postal code) and can
 * print the address to standard out.
 * Created by michaelmeyer on 10/11/16.
 */
public class Address {

    private final int nHouseNumber;
    private final String strStreet;
    //making this non final because it may be null
    private int nAptNumber;
    private final String strCity;
    private final String strState;
    //making this package private so other Address objects can access for comparison
    final int nPostalCode;
    private final boolean hasAptNumber;

    /**
     * Returns address without an apartment number
     * @param nHouseNumber house number of address
     * @param strStreet street name of address
     * @param strCity city for address
     * @param strState state for address
     * @param nPostalCode postal zip code for address
     */
    public Address(int nHouseNumber, String strStreet, String strCity, String strState, int nPostalCode) {
        this.nHouseNumber = nHouseNumber;
        this.strStreet = strStreet;
        this.strCity = strCity;
        this.strState = strState;
        this.nPostalCode = nPostalCode;
        hasAptNumber = false;
    }

    /**
     * Returns address with an apartment number
     * @param nHouseNumber house number of address
     * @param strStreet street name of address
     * @param nAptNumber apartment number for address
     * @param strCity city for address
     * @param strState state for address
     * @param nPostalCode postal zip code for address
     */
    public Address(int nHouseNumber, String strStreet, int nAptNumber, String strCity, String strState, int nPostalCode) {
        this.nHouseNumber = nHouseNumber;
        this.strStreet = strStreet;
        this.nAptNumber = nAptNumber;
        this.strCity = strCity;
        this.strState = strState;
        this.nPostalCode = nPostalCode;
        hasAptNumber = true;
    }

    /**
     * Prints address to standard out. Address is on the first line. City, state and zip code on the second line.
     */
    public void print() {
        if (hasAptNumber) {
            System.out.println(nHouseNumber + " " + strStreet + " APT " + nAptNumber);
        } else {
            System.out.println(nHouseNumber + " " + strStreet);
        }
        System.out.println(strCity + ", " + strState + "  " + nPostalCode);
    }

    /**
     * Compares zip codes of addresses. Returns true if address invoking this method has zip code that comes before other,
     * false if the other comes before.
     * @param other the address which to compare zip codes
     * @return boolean indicating whether or not address comes before other
     */
    public boolean comesBefore(Address other) {
        if (nPostalCode < other.nPostalCode) {
            return true;
        } else {
            return false;
        }
    }

}
