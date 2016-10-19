package _04interfaces.P9_8;

/**
 * Simple representation of an instructor, which extends from Person. Must have a name, year of birth, and salary.
 * Created by michaelmeyer on 10/18/16.
 */
public class Instructor extends Person {

    private final double dSalary;

    /**
     * Constructs instructor. Must supply name, year of birth, and salary.
     * @param strName
     * @param nYearOfBirth
     * @param dSalary
     */
    public Instructor(String strName, int nYearOfBirth, double dSalary) {
        super(strName, nYearOfBirth);
        this.dSalary = dSalary;
    }

    /**
     * String representation of instructor
     * @return string representing instructor object properties
     */
    @Override
    public String toString() {
        return "Instructor{" +
                "name='" + this.getStrName() + '\'' +
                ", year of birth='" + this.getnYearOfBirth() + '\'' +
                ", salary=" + dSalary +
                '}';
    }
}
