package _04interfaces.P9_8;

/**
 * Simple representation of a person. Must have a name and a year of birth.
 * Created by michaelmeyer on 10/18/16.
 */
public class Person {

    private String strName;
    private int nYearOfBirth;

    /**
     * The only constructor for person. Must provide a name and a year of birth.
     * @param strName person's name
     * @param nYearOfBirth person's year of birth as an integer
     */
    public Person(String strName, int nYearOfBirth) {
        this.strName = strName;
        this.nYearOfBirth = nYearOfBirth;
    }

    /**
     * Retrieves the name
     * @return person's name
     */
    public String getStrName() {
        return strName;
    }

    /**
     * Retrieves the year of birth
     * @return person's year of birth
     */
    public int getnYearOfBirth() {
        return nYearOfBirth;
    }

    /**
     * Returns string representation of person, including name and year of birth.
     * @return string values of person object properties
     */
    @Override
    public String toString() {
        return "Person{" +
                "name='" + strName + '\'' +
                ", date of birth=" + nYearOfBirth +
                '}';
    }
}
