package _03objects.P8_9;

/**
 * Simple implementation of a combination lock. Lock consists of three numbers between 0 and 39, and methods by which to
 * turn the dial in order to unlock the lock. Unlike many locks, this one does not require a full rotation on the second
 * number. The first turn of the lock always is assumed to start from zero. As an example, a lock of 35-5-20 would be unlocked
 * by a turnRight invocation of 5, turnLeft of 10, and a final turnRight of 25.
 * Created by michaelmeyer on 10/12/16.
 */
public class ComboLock {

    private final int nFirst;
    private final int nSecond;
    private final int nThird;
    private boolean isUnlocked;
    private boolean isFirstTrue;
    private boolean isSecondTrue;

    /**
     * Constructs lock with given three integer combo. Must be between 0 and 39.
     * @param nFirst first number in combo
     * @param nSecond second number in combo
     * @param nThird third number in combo
     * @throws RuntimeException if a number not between 0 and 39 is chosen
     */
    public ComboLock(int nFirst, int nSecond, int nThird) {
        if (nFirst < 0 || nFirst > 39 || nSecond < 0 || nSecond > 39 ||
                nThird < 0 || nThird > 39) {
            throw new RuntimeException("Illegal combination of " + nFirst + " "  +
                    nSecond + " " + nThird + " chosen for lock. All numbers must be between 0 and 39");
        }
        this.nFirst = nFirst;
        this.nSecond = nSecond;
        this.nThird = nThird;
        this.isUnlocked = false;
        this.isFirstTrue = false;
        this.isSecondTrue = false;
    }

    /**
     * Attempts to open the lock. If successful, returns true
     * @return true if the lock can be unlocked, false if it cannot.
     */
    public boolean open() {
        if (isUnlocked) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Resets the lock so that it can be attempted to be unlocked from the beginning. If the lock is unlocked, this locks
     * it again.
     */
    public void reset() {
        this.isFirstTrue = false;
        this.isSecondTrue = false;
        this.isUnlocked = false;
    }

    /**
     * Calculates the ticks used on the first and third turn of the combo lock. If correct amount of ticks and on correct
     * step, unlocks first phase or the whole lock.
     * @param nTicks amount of number ticks to turn the combo lock
     */
    public void turnRight(int nTicks) {
        //this is what must be true to have a valid first turn
        if (!isFirstTrue && !isSecondTrue && !isUnlocked && nTicks == 40 - nFirst) {
            isFirstTrue = true;
        }
        //this is what must be true to have a valid last turn
        if (isFirstTrue && isSecondTrue && !isUnlocked && nTicks == 40 - nThird + nSecond) {
            isUnlocked = true;
        }
    }

    /**
     * Calculates the ticks used on the second turn of the combo lock. If correct amount of ticks, unlocks second phase.
     * @param nTicks amount of number ticks to turn the combo lock
     */
    public void turnLeft(int nTicks) {
        //this is what must be true to have a valid second turn
        if (isFirstTrue && !isSecondTrue && !isUnlocked && (nTicks == 40 - nFirst + nSecond)) {
            isSecondTrue = true;
        }
    }

}
