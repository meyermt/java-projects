package _04interfaces.P9_8;

/**
 * Created by michaelmeyer on 10/18/16.
 */
public class Person {

    private String strName;
    private int nYearOfBirth;

    public Person(String strName, int nYearOfBirth) {
        this.strName = strName;
        this.nYearOfBirth = nYearOfBirth;
    }

    public String getStrName() {
        return strName;
    }

    public int getnYearOfBirth() {
        return nYearOfBirth;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + strName + '\'' +
                ", date of birth=" + nYearOfBirth +
                '}';
    }
}
