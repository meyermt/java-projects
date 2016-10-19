package _04interfaces.P9_8;

/**
 * Created by michaelmeyer on 10/18/16.
 */
public class Student extends Person {

    private String strMajor;

    public Student(String strName, int nYearOfBirth, String strMajor) {
        super(strName, nYearOfBirth);
        this.strMajor = strMajor;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + this.getStrName() + '\'' +
                ", year of birth='" + this.getnYearOfBirth() + '\'' +
                ", major='" + strMajor + '\'' +
                '}';
    }
}
