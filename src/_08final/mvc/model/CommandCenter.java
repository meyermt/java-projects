package _08final.mvc.model;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Command center.
 */
public class CommandCenter {

	private int nNumDiablo;
	private int nLevel;
	private long lScore;
	private Diablo diablo;
	private Ball killingBall;
	private boolean bPlaying;
	private boolean bPaused;
	private boolean isNewLevel;
	private boolean isMaxWalkingSpeedFloater;
	private boolean isMaxThrowingSpeedFloater;
	private int spawnedBalls;
	
	// These ArrayLists with capacities set
	private List<Movable> movDebris = new ArrayList<>(300);
	private List<Movable> movFriends = new ArrayList<>(100);
	private List<Movable> movFoes = new ArrayList<>(200);
	private List<Movable> movFloaters = new ArrayList<>(50);
	private List<Movable> movNeutrals = new ArrayList<>(50);

	private GameOpsList opsList = new GameOpsList();


	private static CommandCenter instance = null;

	// Constructor made private - static Utility class only
	private CommandCenter() {}


	/**
	 * Get instance command center.
	 *
	 * @return the command center
	 */
	public static CommandCenter getInstance(){
		if (instance == null){
			instance = new CommandCenter();
		}
		return instance;
	}


	/**
	 * Init game.
	 */
	public  void initGame(){
		setSpawnedBallCount(0);
		setScore(0);
		setNumDiablos(3);
		spawnDiablo(true);
		setNewLevel(false);
	}

	/**
	 * Spawn diablo.
	 *
	 * @param bFirst the b first
	 */
// The parameter is true if this is for the beginning of the game, otherwise false
	// When you spawn a new diablo, you need to decrement its number
	public void spawnDiablo(boolean bFirst) {
		if (getNumDiablos() != 0) {
			diablo = new Diablo();

			opsList.enqueue(diablo, CollisionOp.Operation.ADD);
			if (!bFirst)
			    setNumDiablos(getNumDiablos() - 1);
		}
		
		//Sound.playSound("shipspawn.wav");

	}

	/**
	 * Spawn balls.
	 *
	 * @param numBalls the num balls
	 */
	public void spawnBalls(int numBalls) {
		for (int i = 1; i <= numBalls; i++) {
			Ball ball = new Ball(i, numBalls);
			opsList.enqueue(ball, CollisionOp.Operation.ADD);
		}
		this.spawnedBalls = spawnedBalls + numBalls;
	}

	/**
	 * Gets spawned ball count.
	 *
	 * @return the spawned ball count
	 */
	public int getSpawnedBallCount() {
		return spawnedBalls;
	}

	/**
	 * Sets spawned ball count.
	 *
	 * @param numBalls the num balls
	 */
	public void setSpawnedBallCount(int numBalls) {
		this.spawnedBalls = numBalls;
	}

	/**
	 * Gets ops list.
	 *
	 * @return the ops list
	 */
	public GameOpsList getOpsList() {
		return opsList;
	}

	/**
	 * Sets ops list.
	 *
	 * @param opsList the ops list
	 */
	public void setOpsList(GameOpsList opsList) {
		this.opsList = opsList;
	}

	/**
	 * Clear all.
	 */
	public  void clearAll(){
		movDebris.clear();
		movFriends.clear();
		movFoes.clear();
		movFloaters.clear();
		movNeutrals.clear();
	}

	/**
	 * Is playing boolean.
	 *
	 * @return the boolean
	 */
	public  boolean isPlaying() {
		return bPlaying;
	}

	/**
	 * Sets playing.
	 *
	 * @param bPlaying the b playing
	 */
	public  void setPlaying(boolean bPlaying) {
		this.bPlaying = bPlaying;
	}

	/**
	 * Is paused boolean.
	 *
	 * @return the boolean
	 */
	public  boolean isPaused() {
		return bPaused;
	}

	/**
	 * Sets paused.
	 *
	 * @param bPaused the b paused
	 */
	public  void setPaused(boolean bPaused) {
		this.bPaused = bPaused;
	}

	/**
	 * Is new level boolean.
	 *
	 * @return the boolean
	 */
	public  boolean isNewLevel() {
		return isNewLevel;
	}

	/**
	 * Sets new level.
	 *
	 * @param isNewLevel the is new level
	 */
	public  void setNewLevel(boolean isNewLevel) {
		this.isNewLevel = isNewLevel;
	}

	/**
	 * Is game over boolean.
	 *
	 * @return the boolean
	 */
	public  boolean isGameOver() {		//if the number of falcons is zero, then game over
		if (getNumDiablos() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Gets level.
	 *
	 * @return the level
	 */
	public  int getLevel() {
		return nLevel;
	}

	/**
	 * Gets score.
	 *
	 * @return the score
	 */
	public   long getScore() {
		return lScore;
	}

	/**
	 * Sets score.
	 *
	 * @param lParam the l param
	 */
	public  void setScore(long lParam) {
		lScore = lParam;
	}

	/**
	 * Sets level.
	 *
	 * @param n the n
	 */
	public  void setLevel(int n) {
		nLevel = n;
	}

	/**
	 * Gets num diablos.
	 *
	 * @return the num diablos
	 */
	public  int getNumDiablos() {
		return nNumDiablo;
	}

	/**
	 * Sets num diablos.
	 *
	 * @param nParam the n param
	 */
	public  void setNumDiablos(int nParam) {
		nNumDiablo = nParam;
	}

	/**
	 * Gets diablo.
	 *
	 * @return the diablo
	 */
	public Diablo getDiablo() { return diablo; }

	/**
	 * Set diablo.
	 *
	 * @param diablo the diablo
	 */
	public  void setDiablo(Diablo diablo){
		this.diablo = diablo;
	}

	/**
	 * Gets killing ball.
	 *
	 * @return the killing ball
	 */
	public Ball getKillingBall() { return killingBall; }

	/**
	 * Set killing ball.
	 *
	 * @param killingBall the killing ball
	 */
	public void setKillingBall(Ball killingBall){
		this.killingBall = killingBall;
	}

	/**
	 * Is max walking speed floater boolean.
	 *
	 * @return the boolean
	 */
	public boolean isMaxWalkingSpeedFloater() { return isMaxWalkingSpeedFloater; }

	/**
	 * Sets max walking speed floater.
	 *
	 * @param isMaxWalkingSpeedFloater the is max walking speed floater
	 */
	public void setMaxWalkingSpeedFloater(boolean isMaxWalkingSpeedFloater) { this.isMaxWalkingSpeedFloater = isMaxWalkingSpeedFloater; }

	/**
	 * Is max throwing speed floater boolean.
	 *
	 * @return the boolean
	 */
	public boolean isMaxThrowingSpeedFloater() { return isMaxThrowingSpeedFloater; }

	/**
	 * Sets max throwing speed floater.
	 *
	 * @param isMaxThrowingSpeedFloater the is max throwing speed floater
	 */
	public void setMaxThrowingSpeedFloater(boolean isMaxThrowingSpeedFloater) { this.isMaxThrowingSpeedFloater = isMaxThrowingSpeedFloater; }

	/**
	 * Gets mov debris.
	 *
	 * @return the mov debris
	 */
	public List<Movable> getMovDebris() {
		return movDebris;
	}

	/**
	 * Gets mov friends.
	 *
	 * @return the mov friends
	 */
	public List<Movable> getMovFriends() {
		return movFriends;
	}

	/**
	 * Gets mov foes.
	 *
	 * @return the mov foes
	 */
	public List<Movable> getMovFoes() {
		return movFoes;
	}

	/**
	 * Gets mov floaters.
	 *
	 * @return the mov floaters
	 */
	public List<Movable> getMovFloaters() {
		return movFloaters;
	}

	/**
	 * Gets mov neutrals.
	 *
	 * @return the mov neutrals
	 */
	public List<Movable> getMovNeutrals() { return movNeutrals; }


}
