package _05dice.pig;

import java.util.Random;

/**
 * Very simple AI engine for pig game.
 * Created by michaelmeyer on 10/29/16.
 */
public class PigAIEngine {

    /**
     * Instantiates a new Pig ai engine.
     */
    public PigAIEngine() {
    }

    /**
     * Decides if the computer will roll the dice again. This is very simple, but could be enhanced to be more intelligent
     * at some point.
     * @return true if computer will roll again. false if not.
     */
    public boolean willRollAgain() {
        Random random = new Random();
        return random.nextBoolean();
    }

}
