package _06design.P12_6;

import java.util.Random;

/**
 * Created by michaelmeyer on 11/3/16.
 */
public class QuestionEngine {

    private String question;
    private int answer;

    /**
     * Instantiates a new Question engine.
     */
    public QuestionEngine() {
    }

    /**
     * Gets question.
     *
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Gets answer.
     *
     * @return the answer
     */
    public int getAnswer() {
        return answer;
    }

    /**
     * Generate new question string.
     *
     * @param level the level
     * @return the string
     */
    public String generateNewQuestion(GameFrame.Levels level) {
        if (level == GameFrame.Levels.LEVEL_1) {
            return generateLevelOneQuestion();
        } else if (level == GameFrame.Levels.LEVEL_2) {
            return generateLevelTwoQuestion();
        } else if (level == GameFrame.Levels.LEVEL_3) {
            return generateLevelThreeQuestion();
        } else {
            throw new AssertionError("There is no level that is this high. Impossible.");
        }
    }

    private String generateLevelOneQuestion() {
        Random random = new Random();
        //Going to assume the directions didn't want negatives...
        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        while (num1 + num2 >= 10) {
            num1 = random.nextInt(10);
            num2 = random.nextInt(10);
        }
        question = "What is " + num1 + " + " + num2 + "?";
        answer = num1 + num2;
        return question;
    }

    private String generateLevelTwoQuestion() {
        Random random = new Random();
        //Going to assume the directions didn't want negatives...
        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        question = "What is " + num1 + " + " + num2 + "?";
        answer = num1 + num2;
        return question;
    }

    private String generateLevelThreeQuestion() {
        Random random = new Random();
        //Going to assume the directions didn't want negatives...
        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        while (num1 - num2 < 0) {
            num1 = random.nextInt(10);
            num2 = random.nextInt(10);
        }
        question = "What is " + num1 + " - " + num2 + "?";
        answer = num1 - num2;
        return question;
    }

}
