package _05dice.pig;

import java.util.Random;

/**
 * Created by michaelmeyer on 10/29/16.
 */
public class PigAIEngine {

    public PigAIEngine() {
    }

    public boolean willRollAgain(int nRoll, int nScore) {
        Random random = new Random();
        return random.nextBoolean();
    }

}
