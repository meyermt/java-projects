package _08final.mvc.controller;

import _08final.mvc.model.*;
import _08final.mvc.view.GamePanel;
import _08final.sounds.Sound;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

// ===============================================
// == This Game class is the CONTROLLER
// ===============================================

/**
 * Main controller for dodgeball game
 * The type Game.
 */
public class Game implements Runnable, KeyListener, MouseMotionListener, MouseListener {

	// ===============================================
	// FIELDS
	// ===============================================

	private final Set<Integer> pressedKeys = new HashSet<Integer>();
	/**
	 * The constant ARENA_HEIGHT.
	 */
	public static final int ARENA_HEIGHT = 660;
	/**
	 * The constant ARENA_WIDTH.
	 */
	public static final int ARENA_WIDTH = 1120;
	/**
	 * The constant BOTTOM_AND_SCORE.
	 */
	public static final int BOTTOM_AND_SCORE = 100;
	/**
	 * The constant DIM.
	 */
	public static final Dimension DIM = new Dimension(ARENA_WIDTH, ARENA_HEIGHT + BOTTOM_AND_SCORE); //the dimension of the game.
	private GamePanel gmpPanel;
	/**
	 * The constant R.
	 */
	public static Random R = new Random();
	/**
	 * The constant SAINT_X_TERRITORY.
	 */
	public static final double SAINT_X_TERRITORY = ARENA_WIDTH - (ARENA_WIDTH / 2);
	/**
	 * The constant SAINTS_PER_COLUMN.
	 */
	public final static int SAINTS_PER_COLUMN = 5;
	/**
	 * The constant ANI_DELAY.
	 */
	public final static int ANI_DELAY = 45; // milliseconds between screen
	// updates (animation)
	private Thread thrAnim;
	private int nLevel = 1;
	private int nTick = 0;

	private boolean bMuted = true;


	private final int PAUSE = 80, // p key
			START = KeyEvent.VK_ENTER, // s key
            MUTE = 77, // m-key mute
            QUIT = 81; // q key					// fire special weapon;  F key

	private Clip clpMusicBackground;

	private static final int SPAWN_NEW_THROWING_FLOATER = 1200;
    private static final int SPAWN_NEW_WALKING_FLOATER = 600;
    private int throwingFloaterTimer = 0;
    private int walkingFloaterTimer = 0;
    private Random random = new Random();


	// ===============================================
	// ==CONSTRUCTOR
	// ===============================================

	/**
	 * Instantiates a new Game.
	 */
	public Game() {

		gmpPanel = new GamePanel(DIM);
		gmpPanel.addKeyListener(this);
		gmpPanel.addMouseListener(this);
		gmpPanel.addMouseMotionListener(this);
		clpMusicBackground = Sound.clipForLoopFactory("dball-background.wav");
	}

	// ===============================================
	// ==METHODS
	// ===============================================

	/**
	 * Main.
	 *
	 * @param args the args
	 */
	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() { // uses the Event dispatch thread from Java 5 (refactored)
			public void run() {
				try {
					Game game = new Game(); // construct itself
					game.fireUpAnimThread();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void fireUpAnimThread() { // called initially
		if (thrAnim == null) {
			thrAnim = new Thread(this); // pass the thread a runnable object (this)
			thrAnim.start();
		}
	}

	// implements runnable - must have run method
	public void run() {

		// lower this thread's priority; let the "main" aka 'Event Dispatch'
		// thread do what it needs to do first
		thrAnim.setPriority(Thread.MIN_PRIORITY);

		// and get the current time
		long lStartTime = System.currentTimeMillis();

		// this thread animates the scene
		while (Thread.currentThread() == thrAnim) {
			tick();
			gmpPanel.update(gmpPanel.getGraphics()); // update takes the graphics context we must
			// surround the sleep() in a try/catch block
			// this simply controls delay time between
			// the frames of the animation

			//this might be a good place to check for collisions
			directSaintsAndGetSaintBalls();
			throwSaintBalls();
			checkCollisionsAndJumps();
            processDeadBalls();
            processFrameTransactions();
            spawnAndStopFloaters();
			//this might be a god place to check if the level is clear (no more foes)
			//if the level is clear then spawn some big asteroids -- the number of asteroids 
			//should increase with the level. 
			checkNewLevel();

			try {
				// The total amount of time is guaranteed to be at least ANI_DELAY long.  If processing (update) 
				// between frames takes longer than ANI_DELAY, then the difference between lStartTime - 
				// System.currentTimeMillis() will be negative, then zero will be the sleep time
				lStartTime += ANI_DELAY;
				Thread.sleep(Math.max(0,
						lStartTime - System.currentTimeMillis()));
			} catch (InterruptedException e) {
				// just skip this frame -- no big deal
				continue;
			}
		}
	}

	/*
		Here we will guide Saints to go get a ball
	 */
	private void directSaintsAndGetSaintBalls() {
		for (Movable movNeutral : CommandCenter.getInstance().getMovNeutrals()) {
			double neutralX = movNeutral.getCenter().getX();
			//is the ball retrievable for one of our saints
			if (neutralX >= SAINT_X_TERRITORY) {
				//find a saint that should get it and direct them
				//making a saint off the map, because nearest will always be there, won't matter
				Saint saint = null;
				double nearest = Double.MAX_VALUE;
				for (Movable movFoe : CommandCenter.getInstance().getMovFoes()) {
					if (movFoe instanceof Saint) {
						double xDiff = movFoe.getCenter().getX() - movNeutral.getCenter().getX();
						double yDiff = movFoe.getCenter().getY() - movNeutral.getCenter().getY();
                        //if he's the closest and not already retrieving
						if (Math.hypot(xDiff, yDiff) < nearest && !((Saint) movFoe).isRetrieving &&
                                ((Ball) movNeutral).getSaintRetriever() == null) {
							saint = (Saint) movFoe;
                        }
					}
				}
				if (saint != null) {
                    Ball ball = (Ball) movNeutral;
                    ball.setSaintRetriever(saint);
                    saint.retrieveBall(ball);
                }
			}
		}
	}

	/*
	    When a saint is ready to throw his (her?) ball, this invokes it. Depends on saint's throwing cadence and if
	    they have a ball.
	 */
	private void throwSaintBalls() {
		for (Movable movFoe : CommandCenter.getInstance().getMovFoes()) {
			if (movFoe instanceof Saint) {
				Saint saint = (Saint) movFoe;
				if (saint.hasBall && nTick % saint.getThrowingCadence() == 0) {
					Diablo diablo = CommandCenter.getInstance().getDiablo();
					saint.hasBall = false;
					saint.isThrowing = false;
					saint.isReleasingThrow = true;
                    Sound.playSound("saint-grunt.wav");
					saint.setBall(null);
                    int uid = CommandCenter.getInstance().getSpawnedBallCount() + 1;
                    CommandCenter.getInstance().setSpawnedBallCount(uid);
                    int randomVar = random.nextInt(20) - random.nextInt(20);
                    int diabloX = (int) diablo.getCenter().getX() - randomVar;
                    int diabloY = (int) diablo.getCenter().getY() - randomVar;
					CommandCenter.getInstance().getOpsList().enqueue(new Ball(uid, saint, diabloX, diabloY), CollisionOp.Operation.ADD);
				}
			}
		}
	}

	private void checkCollisionsAndJumps() {
        Point pntFriendCenter, pntFoeCenter, pntNeutralCenter;
        int nFriendRadiux, nFoeRadiux, nNeutralRadiux, nFriendCatchRadiux;

        /*
            FRIEND w FOE COLLISIONS
         */
        for (Movable movFriend : CommandCenter.getInstance().getMovFriends()) {
            for (Movable movFoe : CommandCenter.getInstance().getMovFoes()) {

                pntFriendCenter = movFriend.getCenter();
                pntFoeCenter = movFoe.getCenter();
                nFriendRadiux = movFriend.getRadius();
                nFoeRadiux = movFoe.getRadius();
                nFriendCatchRadiux = movFriend.getRadius() / 2;

                if (pntFriendCenter.distance(pntFoeCenter) < (nFriendRadiux + nFoeRadiux) && !CommandCenter.getInstance().getDiablo().getProtected()) {
                    if (movFriend instanceof  Diablo && movFoe instanceof Ball) {
                        Diablo diablo = (Diablo) movFriend;
                        Ball ball = (Ball) movFoe;

                        if (pntFriendCenter.distance(pntFoeCenter) < (nFriendCatchRadiux + nFoeRadiux) && ((Diablo) movFriend).isCatching) {
                            CommandCenter.getInstance().getOpsList().enqueue(ball.getThrower(), CollisionOp.Operation.REMOVE);
                            CommandCenter.getInstance().setScore((CommandCenter.getInstance().getScore() + ball.getPoints()));
                            CommandCenter.getInstance().getOpsList().enqueue(ball, CollisionOp.Operation.REMOVE);
                            CommandCenter.getInstance().setKillingBall(null);
                            diablo.hasBall = true;
                            diablo.isCatching = false;
                        } else {
                            CommandCenter.getInstance().setKillingBall(ball);
                        }
                    } else if (movFoe instanceof Saint && movFriend instanceof Ball) {
                        Ball ball = (Ball) movFriend;
						//random bounce off saint
                        ball.randomFlight();
                        Saint saint = (Saint) movFoe;
                        if (saint.hasBall) {
                            int uid = CommandCenter.getInstance().getSpawnedBallCount() + 1;
                            CommandCenter.getInstance().setSpawnedBallCount(uid);
                            CommandCenter.getInstance().getOpsList().enqueue(new Ball(uid, saint.getCenter()), CollisionOp.Operation.ADD);
                        }
                        killFoe(movFoe);
                        Sound.playSound("ball-hit.wav");
                        CommandCenter.getInstance().setScore((CommandCenter.getInstance().getScore() + ball.getPoints()));
                    }
                } else if (movFriend instanceof Diablo && movFoe instanceof Ball && CommandCenter.getInstance().getKillingBall() != null) {
                    if (CommandCenter.getInstance().getKillingBall().getUID() == ((Ball) movFoe).getUID()) {
                        Diablo diablo = (Diablo) movFriend;
                        Ball ball = (Ball) movFoe;
                        if (diablo.hasBall) {
                                int uid = CommandCenter.getInstance().getSpawnedBallCount() + 1;
                                CommandCenter.getInstance().setSpawnedBallCount(uid);
                                CommandCenter.getInstance().getOpsList().enqueue(new Ball(uid, diablo.getCenter()), CollisionOp.Operation.ADD);
                        }
                        ball.randomFlight();
                        killDiablo(movFriend);
                        Sound.playSound("ball-hit.wav");
                        CommandCenter.getInstance().setKillingBall(null);
                    }
                }
            }
        }

        /*
            NEUTRAL BALLS w FRIEND|FOE COLLISIONS
            all non-held balls are moved back to neutrals. check if they are being picked up
         */
        for (Movable neutral : CommandCenter.getInstance().getMovNeutrals()) {
            for (Movable movFriend : CommandCenter.getInstance().getMovFriends()) {
                pntFriendCenter = movFriend.getCenter();
                pntNeutralCenter = neutral.getCenter();
                nFriendRadiux = movFriend.getRadius();
                nNeutralRadiux = neutral.getRadius();
                if (pntFriendCenter.distance(pntNeutralCenter) < (nFriendRadiux + nNeutralRadiux)) {
                    if (movFriend instanceof Diablo && neutral instanceof Ball) {
                        Diablo diablo = (Diablo) movFriend;
                        if (diablo.isCatching && !diablo.hasBall) {
                            diablo.hasBall = true;
                            CommandCenter.getInstance().getOpsList().enqueue(neutral, CollisionOp.Operation.REMOVE);
                        }
                    }
                }
            }

            for (Movable movFoe : CommandCenter.getInstance().getMovFoes()) {
                pntFoeCenter = movFoe.getCenter();
                pntNeutralCenter = neutral.getCenter();
                nFoeRadiux = movFoe.getRadius();
                nNeutralRadiux = neutral.getRadius();
                if (pntFoeCenter.distance(pntNeutralCenter) < (nFoeRadiux + nNeutralRadiux)) {
                    if (movFoe instanceof Saint && neutral instanceof Ball) {
                        Saint saint = (Saint) movFoe;
                        if (!saint.hasBall) {
                            saint.pickUpBall((Ball) neutral);
                            CommandCenter.getInstance().getOpsList().enqueue(neutral, CollisionOp.Operation.REMOVE);
                        }
                    }
                }
            }
        }

        if (CommandCenter.getInstance().getDiablo() != null){
            Point pntDiabloCenter = CommandCenter.getInstance().getDiablo().getCenter();
            int nDiabloRadiux = CommandCenter.getInstance().getDiablo().getRadius();
            Point pntFloaterCenter;
            int nFloaterRadiux;

            for (Movable movFloater : CommandCenter.getInstance().getMovFloaters()) {
                pntFloaterCenter = movFloater.getCenter();
                nFloaterRadiux = movFloater.getRadius();

                //detect collision
                if (pntDiabloCenter.distance(pntFloaterCenter) < (nDiabloRadiux + nFloaterRadiux)) {

                    if (movFloater instanceof MaxThrowingFloater) {
                        CommandCenter.getInstance().setMaxThrowingSpeedFloater(true);
                        throwingFloaterTimer = nTick + 100;
                    } else if (movFloater instanceof MaxWalkingFloater) {
                        CommandCenter.getInstance().setMaxWalkingSpeedFloater(true);
                        walkingFloaterTimer = nTick + 100;
                    }

                    CommandCenter.getInstance().getOpsList().enqueue(movFloater, CollisionOp.Operation.REMOVE);

                }//end if
            }//end inner for
        }//end if not null
    }

    /*
        STOPPED BALLS
    */
    private void processDeadBalls() {
        for (Movable mov : CommandCenter.getInstance().getMovFoes()) {
            reNeutralizeDeadBall(mov);
        }
        for (Movable mov : CommandCenter.getInstance().getMovFriends()) {
            reNeutralizeDeadBall(mov);
        }
    }

    private void reNeutralizeDeadBall(Movable mov) {
        if (mov instanceof Ball) {
            Ball ball = (Ball) mov;
            if (!ball.isMoving) {
                CommandCenter.getInstance().getOpsList().enqueue(mov, CollisionOp.Operation.REMOVE);
                int uid = CommandCenter.getInstance().getSpawnedBallCount() + 1;
                CommandCenter.getInstance().setSpawnedBallCount(uid);
                CommandCenter.getInstance().getOpsList().enqueue(new Ball(uid, mov.getCenter()), CollisionOp.Operation.ADD);
            }
        }
    }

    private void processFrameTransactions() {
		//we are dequeuing the opsList and performing operations in serial to avoid mutating the movable arraylists while iterating them above
		while(!CommandCenter.getInstance().getOpsList().isEmpty()){
			CollisionOp cop =  CommandCenter.getInstance().getOpsList().dequeue();
			Movable mov = cop.getMovable();
			CollisionOp.Operation operation = cop.getOperation();

			switch (mov.getTeam()){
				case FOE:
					if (operation == CollisionOp.Operation.ADD){
						CommandCenter.getInstance().getMovFoes().add(mov);
					} else {
						CommandCenter.getInstance().getMovFoes().remove(mov);
					}

					break;
				case FRIEND:
					if (operation == CollisionOp.Operation.ADD){
						CommandCenter.getInstance().getMovFriends().add(mov);
					} else {
						CommandCenter.getInstance().getMovFriends().remove(mov);
					}
					break;

				case FLOATER:
					if (operation == CollisionOp.Operation.ADD){
						CommandCenter.getInstance().getMovFloaters().add(mov);
					} else {
						CommandCenter.getInstance().getMovFloaters().remove(mov);
					}
					break;

				case DEBRIS:
					if (operation == CollisionOp.Operation.ADD){
						CommandCenter.getInstance().getMovDebris().add(mov);
					} else {
						CommandCenter.getInstance().getMovDebris().remove(mov);
					}
					break;

				case NEUTRAL:
					if (operation == CollisionOp.Operation.ADD){
						CommandCenter.getInstance().getMovNeutrals().add(mov);
					} else {
						CommandCenter.getInstance().getMovNeutrals().remove(mov);
					}
					break;


			}

		}
		//a request to the JVM is made every frame to garbage collect, however, the JVM will choose when and how to do this
		System.gc();
	}

	private void killFoe(Movable movFoe) {
		//remove the original Foe
		CommandCenter.getInstance().getOpsList().enqueue(movFoe, CollisionOp.Operation.REMOVE);
	}

	private void killDiablo(Movable movFriend) {
        CommandCenter.getInstance().getOpsList().enqueue(movFriend, CollisionOp.Operation.REMOVE);
        CommandCenter.getInstance().spawnDiablo(false);
    }

	/**
	 * Tick.
	 */
//some methods for timing events in the game,
	//such as the appearance of UFOs, floaters (power-ups), etc. 
	public void tick() {
		if (nTick == Integer.MAX_VALUE)
			nTick = 0;
		else
			nTick++;
	}

	/**
	 * Gets tick.
	 *
	 * @return the tick
	 */
	public int getTick() {
		return nTick;
	}

	private void spawnSaints(int numSaints) {
		int columns = (numSaints + SAINTS_PER_COLUMN - 1) / SAINTS_PER_COLUMN;
		for (int i = 1; i <= numSaints; i++) {
			int column = (i + SAINTS_PER_COLUMN - 1) / SAINTS_PER_COLUMN;
			double columnSpacing = (ARENA_WIDTH / 2) / (columns + 1);
			double x = SAINT_X_TERRITORY + (columnSpacing * column);
			double rowSpacing = (ARENA_HEIGHT / (SAINTS_PER_COLUMN + 1));
			int yPos = (i % SAINTS_PER_COLUMN) == 0 ? 5 : (i % SAINTS_PER_COLUMN);
			double y = yPos * rowSpacing;
			Point startingPoint = new Point((int) x, (int) y);
			Saint saint = new Saint(startingPoint);
			CommandCenter.getInstance().getOpsList().enqueue(saint, CollisionOp.Operation.ADD);
		}
	}

    private void spawnAndStopFloaters() {

        if (CommandCenter.getInstance().isMaxThrowingSpeedFloater() && nTick > throwingFloaterTimer) {
            CommandCenter.getInstance().setMaxThrowingSpeedFloater(false);
        }
        if (CommandCenter.getInstance().isMaxWalkingSpeedFloater() && nTick > walkingFloaterTimer) {
            CommandCenter.getInstance().setMaxWalkingSpeedFloater(false);
        }

        if (nTick % (SPAWN_NEW_THROWING_FLOATER - nLevel * 7) == 0) {
            CommandCenter.getInstance().getOpsList().enqueue(new MaxThrowingFloater(), CollisionOp.Operation.ADD);
        }
        if (nTick % (SPAWN_NEW_WALKING_FLOATER - nLevel * 7) == 0) {
            CommandCenter.getInstance().getOpsList().enqueue(new MaxWalkingFloater(), CollisionOp.Operation.ADD);
        }
    }

	// Called when user presses 's'
	private void startGame() {
		CommandCenter.getInstance().clearAll();
		CommandCenter.getInstance().initGame();
		CommandCenter.getInstance().setLevel(0);
		CommandCenter.getInstance().setPlaying(true);
		CommandCenter.getInstance().setPaused(false);
		//if (!bMuted)
		// clpMusicBackground.loop(Clip.LOOP_CONTINUOUSLY);
	}

	private boolean isLevelClear(){
		//if there are no more Saints on the screen
		boolean areSaintsGone = true;
		for (Movable movFoe : CommandCenter.getInstance().getMovFoes()) {
			if (movFoe instanceof Saint){
				areSaintsGone = false;
				break;
			}
		}

		return areSaintsGone;
	}

	private void checkNewLevel(){
        if (isLevelClear() ){
            CommandCenter.getInstance().clearAll();
            if (CommandCenter.getInstance().getDiablo() !=null) {
                CommandCenter.getInstance().getDiablo().setProtected(true);
            }
            if (CommandCenter.getInstance().getLevel() != 0) {
                CommandCenter.getInstance().setNewLevel(true);
            }
            spawnSaints(CommandCenter.getInstance().getLevel() + 1);
            CommandCenter.getInstance().spawnBalls(CommandCenter.getInstance().getLevel() + 1);
            CommandCenter.getInstance().spawnDiablo(true);
			CommandCenter.getInstance().setLevel(CommandCenter.getInstance().getLevel() + 1);
		}
	}




	// Varargs for stopping looping-music-clips
	private static void stopLoopingSounds(Clip... clpClips) {
		for (Clip clp : clpClips) {
			clp.stop();
		}
	}

	// ===============================================
	// KEYLISTENER METHODS
	// ===============================================

	@Override
	public void keyPressed(KeyEvent e) {
        Diablo diablo = CommandCenter.getInstance().getDiablo();
        int nKey = e.getKeyCode();
        pressedKeys.add(nKey);

        if (nKey == START && !CommandCenter.getInstance().isPlaying()) {
            startGame();
        } else if (nKey == START && CommandCenter.getInstance().isNewLevel()) {
            CommandCenter.getInstance().setNewLevel(false);
        } else if (nKey == PAUSE){
            CommandCenter.getInstance().setPaused(!CommandCenter.getInstance().isPaused());
            if (CommandCenter.getInstance().isPaused()) {
                stopLoopingSounds(clpMusicBackground);
            } else {
                clpMusicBackground.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } else if (nKey == QUIT) {
            System.exit(0);
        }

		if (diablo != null) {
			for (Integer pressedKey : pressedKeys) {
				if (pressedKey == KeyEvent.VK_UP) {
					diablo.walkingUp = true;
				}
				if (pressedKey == KeyEvent.VK_DOWN) {
					diablo.walkingDown = true;
				}
				if (pressedKey == KeyEvent.VK_LEFT) {
					diablo.walkingLeft = true;
				}
				if (pressedKey == KeyEvent.VK_RIGHT) {
					diablo.walkingRight = true;
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Diablo diablo = CommandCenter.getInstance().getDiablo();
		int releasedKey = e.getKeyCode();

        if (releasedKey == MUTE) {
            if (!bMuted){
                stopLoopingSounds(clpMusicBackground);
                bMuted = !bMuted;
            }
            else {
                clpMusicBackground.loop(Clip.LOOP_CONTINUOUSLY);
                bMuted = !bMuted;
            }
        }

		if (diablo != null) {
			if (releasedKey == KeyEvent.VK_UP) {
				diablo.walkingUp = false;
			}
			if (releasedKey == KeyEvent.VK_DOWN) {
				diablo.walkingDown = false;
			}
			if (releasedKey == KeyEvent.VK_LEFT) {
				diablo.walkingLeft = false;
			}
			if (releasedKey == KeyEvent.VK_RIGHT) {
				diablo.walkingRight = false;
			}
			pressedKeys.remove(releasedKey);
		}
	}

	@Override
	// Just need it b/c of KeyListener implementation
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Diablo diablo = CommandCenter.getInstance().getDiablo();
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (diablo.hasBall && !diablo.isCatching) {
				diablo.isThrowing = true;
			}
		} else if (SwingUtilities.isRightMouseButton(e)) {
			if (!diablo.hasBall) {
				diablo.isCatching = true;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Diablo diablo = CommandCenter.getInstance().getDiablo();
		if (SwingUtilities.isLeftMouseButton(e)) {
			if (diablo.hasBall) {
				diablo.hasBall = false;
				diablo.isThrowing = false;
				diablo.isReleasingThrow = true;
                Sound.playSound("diablo-grunt.wav");
                int uid = CommandCenter.getInstance().getSpawnedBallCount() + 1;
                CommandCenter.getInstance().setSpawnedBallCount(uid);
				CommandCenter.getInstance().getOpsList().enqueue(new Ball(uid, diablo, e.getX(), e.getY()), CollisionOp.Operation.ADD);
			} else {
                diablo.dodge(e.getX(), e.getY());
            }
		} else if (SwingUtilities.isRightMouseButton(e)) {
			diablo.isCatching = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}


