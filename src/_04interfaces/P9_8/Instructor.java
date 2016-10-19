package _04interfaces.P9_8;

/**
 * Created by michaelmeyer on 10/18/16.
 */
public class Instructor extends Person {

    private double dSalary;

    public Instructor(String strName, int nYearOfBirth, double dSalary) {
        super(strName, nYearOfBirth);
        this.dSalary = dSalary;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "name='" + this.getStrName() + '\'' +
                ", year of birth='" + this.getnYearOfBirth() + '\'' +
                ", salary=" + dSalary +
                '}';
    }
}
