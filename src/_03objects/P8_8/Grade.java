package _03objects.P8_8;

/**
 * Representation of a grade for a student.
 * Created by michaelmeyer on 10/12/16.
 */
public class Grade {

    private final String strGrade;
    private final double dGpa;

    /**
     * Constructs a grade object based on letter grade passed in.
     * @param strGrade letter grade
     */
    public Grade (String strGrade) {
        this.strGrade = strGrade;
        this.dGpa = gradeToGpa(strGrade);
    }

    /**
     * Retrieves numeric GPA for a certain grade.
     * @return numeric GPA for letter grade
     */
    public double getGpa() {
        return this.dGpa;
    }

    private double gradeToGpa(String strGrade) {
        double dGpa;
        switch (strGrade) {
            case "A+":
                dGpa = 4.0;
                break;
            case "A":
                dGpa = 4.0;
                break;
            case "A-":
                dGpa = 3.7;
                break;
            case "B+":
                dGpa = 3.3;
                break;
            case "B":
                dGpa = 3.0;
                break;
            case "B-":
                dGpa = 2.7;
                break;
            case "C+":
                dGpa = 2.3;
                break;
            case "C":
                dGpa = 2.0;
                break;
            case "C-":
                dGpa = 1.7;
                break;
            case "D+":
                dGpa = 1.3;
                break;
            case "D":
                dGpa = 1.0;
                break;
            case "D-":
                dGpa = 0.7;
                break;
            case "F":
                dGpa = 0.0;
                break;
            default:
                throw new RuntimeException("Incorrect letter grade supplied: " + strGrade);
        }
        return dGpa;
    }
}
