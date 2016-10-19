package _04interfaces.P9_8;

/**
 * Simple representation of a student, which extends from Person. Must include name, year of birth, and major.
 * Created by michaelmeyer on 10/18/16.
 */
public class Student extends Person {

    private String strMajor;

    /**
     * Constructs student with name, year of birth, and major.
     * @param strName student's name
     * @param nYearOfBirth student's year of birth
     * @param strMajor student's major
     */
    public Student(String strName, int nYearOfBirth, String strMajor) {
        super(strName, nYearOfBirth);
        this.strMajor = strMajor;
    }

    /**
     * String representation of a student
     * @return string values of student object
     */
    @Override
    public String toString() {
        return "Student{" +
                "name='" + this.getStrName() + '\'' +
                ", year of birth='" + this.getnYearOfBirth() + '\'' +
                ", major='" + strMajor + '\'' +
                '}';
    }
}
