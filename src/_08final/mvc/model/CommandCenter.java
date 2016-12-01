package _08final.mvc.model;

import java.util.ArrayList;
import java.util.List;


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


	public static CommandCenter getInstance(){
		if (instance == null){
			instance = new CommandCenter();
		}
		return instance;
	}


	public  void initGame(){
		setSpawnedBallCount(0);
		setLevel(1);
		setScore(0);
		setNumDiablos(3);
		spawnDiablo(true);
		spawnBalls(2);
		setNewLevel(false);
	}
	
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

	public void spawnBalls(int numBalls) {
		for (int i = 1; i <= numBalls; i++) {
			Ball ball = new Ball(i, numBalls);
			opsList.enqueue(ball, CollisionOp.Operation.ADD);
		}
		this.spawnedBalls = spawnedBalls + numBalls;
	}

	public int getSpawnedBallCount() {
		return spawnedBalls;
	}

	public void setSpawnedBallCount(int numBalls) {
		this.spawnedBalls = numBalls;
	}

	public GameOpsList getOpsList() {
		return opsList;
	}

	public void setOpsList(GameOpsList opsList) {
		this.opsList = opsList;
	}

	public  void clearAll(){
		movDebris.clear();
		movFriends.clear();
		movFoes.clear();
		movFloaters.clear();
		movNeutrals.clear();
	}

	public  boolean isPlaying() {
		return bPlaying;
	}

	public  void setPlaying(boolean bPlaying) {
		this.bPlaying = bPlaying;
	}

	public  boolean isPaused() {
		return bPaused;
	}

	public  void setPaused(boolean bPaused) {
		this.bPaused = bPaused;
	}

	public  boolean isNewLevel() {
		return isNewLevel;
	}

	public  void setNewLevel(boolean isNewLevel) {
		this.isNewLevel = isNewLevel;
	}
	
	public  boolean isGameOver() {		//if the number of falcons is zero, then game over
		if (getNumDiablos() == 0) {
			return true;
		}
		return false;
	}

	public  int getLevel() {
		return nLevel;
	}

	public   long getScore() {
		return lScore;
	}

	public  void setScore(long lParam) {
		lScore = lParam;
	}

	public  void setLevel(int n) {
		nLevel = n;
	}

	public  int getNumDiablos() {
		return nNumDiablo;
	}

	public  void setNumDiablos(int nParam) {
		nNumDiablo = nParam;
	}

	public Diablo getDiablo() { return diablo; }

	public  void setDiablo(Diablo diablo){
		this.diablo = diablo;
	}

	public Ball getKillingBall() { return killingBall; }

	public void setKillingBall(Ball killingBall){
		this.killingBall = killingBall;
	}

	public boolean isMaxWalkingSpeedFloater() { return isMaxWalkingSpeedFloater; }

	public void setMaxWalkingSpeedFloater(boolean isMaxWalkingSpeedFloater) { this.isMaxWalkingSpeedFloater = isMaxWalkingSpeedFloater; }

	public boolean isMaxThrowingSpeedFloater() { return isMaxThrowingSpeedFloater; }

	public void setMaxThrowingSpeedFloater(boolean isMaxThrowingSpeedFloater) { this.isMaxThrowingSpeedFloater = isMaxThrowingSpeedFloater; }

	public List<Movable> getMovDebris() {
		return movDebris;
	}

	public List<Movable> getMovFriends() {
		return movFriends;
	}

	public List<Movable> getMovFoes() {
		return movFoes;
	}

	public List<Movable> getMovFloaters() {
		return movFloaters;
	}

	public List<Movable> getMovNeutrals() { return movNeutrals; }


}
