package _03objects.P8_8;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a student. Stores name and can do some calculation to get total quiz score, average quiz
 * score, and cumulative GPA.
 * Created by michaelmeyer on 10/12/16.
 */
public class Student {

    private final String strName;
    private int nTotalQuizScore;
    private int nNumQuizzes;
    private List<Double> dGpas = new ArrayList<>();

    /**
     * Constructs new Student. Each student must have a name and an initial quiz score.
     * @param strName name of student
     * @param nQuizScore score of quiz
     */
    public Student(String strName, int nQuizScore) {
        this.strName = strName;
        this.nTotalQuizScore = nQuizScore;
        this.nNumQuizzes = 1;
    }

    /**
     * Gets name of student
     * @return student's name
     */
    public String getName() {
        return this.strName;
    }

    /**
     * Adds a quiz to student. Updates total quiz score and number of quizzes.
     * @param nQuizScore
     */
    public void addQuiz(int nQuizScore) {
        this.nTotalQuizScore = this.nTotalQuizScore + nQuizScore;
        this.nNumQuizzes++;
    }

    /**
     * Gets total score of all quizzes added to student object
     * @return total quiz score
     */
    public int getTotalScore() {
        return this.nTotalQuizScore;
    }

    /**
     * Calculates and returns average quiz score
     * @return average quiz score
     */
    public int getAverageScore() {
        return this.getTotalScore() / nNumQuizzes;
    }

    /**
     * Adds a letter grade to a student and uses it to calculate total GPA.
     * @param strGrade letter grade for the student
     */
    public void addGrade(String strGrade) {
        Grade grade = new Grade(strGrade);
        dGpas.add(grade.getGpa());
    }

    /**
     * Calculates and returns total GPA for a student. This is based on any letter grades added for student.
     * @return string representation of student's total GPA.
     */
    public String getTotalGpa() {
        double dRunningTotal = 0.0;
        int nGpas = 0;
        for (Double dGpa : dGpas) {
            dRunningTotal = dRunningTotal + dGpa.doubleValue();
            nGpas++;
        }
        double dGpa = dRunningTotal / nGpas;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(dGpa);
    }

}
