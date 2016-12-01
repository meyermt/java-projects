package _08final.mvc.view;

import _08final.mvc.controller.Game;
import _08final.mvc.model.CommandCenter;
import _08final.mvc.model.Diablo;
import _08final.mvc.model.Movable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class GamePanel extends Panel {
	
	// ==============================================================
	// FIELDS 
	// ============================================================== 
	 
	// The following "off" vars are used for the off-screen double-bufferred image. 
	private Dimension dimOff;
	private Image imgOff;
	private Graphics grpOff;
	
	private GameFrame gmf;
	private Font fnt = new Font("Monospaced", Font.BOLD, 30);
	private Font fntBig = new Font("Monospaced", Font.BOLD + Font.ITALIC, 36);
	private FontMetrics fmt; 
	private int nFontWidth;
	private int nFontHeight;
	private String strDisplay = "";
	

	// ==============================================================
	// CONSTRUCTOR 
	// ==============================================================
	
	public GamePanel(Dimension dim){
	    gmf = new GameFrame();
		gmf.getContentPane().add(this);
		gmf.pack();
		initView();
		
		gmf.setSize(dim);
		gmf.setTitle("Game Base");
		gmf.setResizable(false);
		gmf.setVisible(true);
		this.setFocusable(true);
	}
	
	
	// ==============================================================
	// METHODS 
	// ==============================================================
	
	private void drawScore(Graphics g) {
		g.setColor(Color.white);
		g.setFont(fnt);
		if (CommandCenter.getInstance().getScore() != 0) {
			g.drawString("SCORE :  " + CommandCenter.getInstance().getScore(), nFontWidth, (int) Game.DIM.getHeight() - 50);
		} else {
			g.drawString("NO SCORE", nFontWidth, (int) Game.DIM.getHeight() - 50);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void update(Graphics g) {
		if (grpOff == null || Game.DIM.width != dimOff.width
				|| Game.DIM.height != dimOff.height) {
			dimOff = Game.DIM;
			imgOff = createImage(Game.DIM.width, Game.DIM.height);
			grpOff = imgOff.getGraphics();
		}
		// Fill in background with black.
		grpOff.setColor(Color.black);
		grpOff.fillRect(0, 0, Game.DIM.width, Game.DIM.height);

		drawScore(grpOff);
		
		if (!CommandCenter.getInstance().isPlaying()) {
			displayTextOnScreen();
		} else if (CommandCenter.getInstance().isPaused()) {
			strDisplay = "Game Paused";
			grpOff.drawString(strDisplay,
					(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4);
		} else if (CommandCenter.getInstance().isNewLevel()) {
            String strDisplay1 = "Diablo, You Are Rising";
            grpOff.drawString(strDisplay1,
                    (Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4);
            String strDisplay2 = "Press Enter to compete at Level " + CommandCenter.getInstance().getLevel();
            grpOff.drawString(strDisplay2,
                    (Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 2);
        }
		
		//playing and not paused!
		else {
			//if we are playing, we know we can draw the arena
			grpOff.drawImage(readBackground(), 0, 0, null);

			//draw them in decreasing level of importance
			//friends will be on top layer and debris on the bottom
			iterateMovables(grpOff,
					(ArrayList<Movable>) CommandCenter.getInstance().getMovNeutrals(),
					(ArrayList<Movable>)  CommandCenter.getInstance().getMovFriends(),
					(ArrayList<Movable>)  CommandCenter.getInstance().getMovFoes(),
					(ArrayList<Movable>)  CommandCenter.getInstance().getMovFloaters(),
					(ArrayList<Movable>)  CommandCenter.getInstance().getMovDebris());
;

			drawNumberDiablosLeft(grpOff);
			if (CommandCenter.getInstance().isGameOver()) {
                CommandCenter.getInstance().clearAll();
				CommandCenter.getInstance().setPlaying(false);
				//bPlaying = false;
			}
		}
		//draw the double-Buffered Image to the graphics context of the panel
		g.drawImage(imgOff, 0, 0, this);
	}

	private BufferedImage readBackground() {
		BufferedImage someImage = null;
		try {
			someImage = ImageIO.read(new File("dball-arena.bmp"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return someImage;
	}


	
	//for each movable array, process it.
	private void iterateMovables(Graphics g, ArrayList<Movable>...movMovz){
		for (ArrayList<Movable> movMovs : movMovz) {
			for (Movable mov : movMovs) {
				mov.move();
				mov.draw(g);
			}
		}
		
	}

	// Draw the number of falcons left on the bottom-right of the screen. 
	private void drawNumberDiablosLeft(Graphics g) {
        Diablo diablo = CommandCenter.getInstance().getDiablo();
        int xCoord = Game.ARENA_WIDTH / 4;
        int yCoord = (int) Game.DIM.getHeight() - Diablo.DIABLO_DIAMETER - 20;
        for (int nD = 1; nD < CommandCenter.getInstance().getNumDiablos(); nD++) {
            g.drawImage(diablo.getDiabloPic().getScaledInstance(Diablo.DIABLO_RADIUS, Diablo.DIABLO_RADIUS, 0), xCoord, yCoord, null);
            xCoord = xCoord + Diablo.DIABLO_RADIUS + 5;
        }
	}
	
	private void initView() {
		Graphics g = getGraphics();			// get the graphics context for the panel
		g.setFont(fnt);						// take care of some simple font stuff
		fmt = g.getFontMetrics();
		nFontWidth = fmt.getMaxAdvance();
		nFontHeight = fmt.getHeight();
		g.setFont(fntBig);					// set font info
	}
	
	// This method draws some text to the middle of the screen before/after a game
	private void displayTextOnScreen() {

		strDisplay = "DIABLO DODGEBALL";
		grpOff.drawString(strDisplay,
				(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4);

		strDisplay = "use the arrow keys to control Diablo";
		grpOff.drawString(strDisplay,
				(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4
						+ nFontHeight + 40);

		strDisplay = "use the left click to throw balls";
		grpOff.drawString(strDisplay,
				(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4
						+ nFontHeight + 80);

        strDisplay = "and jump/dodge in direction of mouse";
        grpOff.drawString(strDisplay,
                (Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4
                        + nFontHeight + 120);

		strDisplay = "use the right click to catch and pick up balls";
		grpOff.drawString(strDisplay,
				(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4
						+ nFontHeight + 160);

		strDisplay = "'Enter' to Start";
		grpOff.drawString(strDisplay,
				(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4
						+ nFontHeight + 200);

		strDisplay = "'P' to Pause";
		grpOff.drawString(strDisplay,
				(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4
						+ nFontHeight + 240);

		strDisplay = "'Q' to Quit";
		grpOff.drawString(strDisplay,
				(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4
						+ nFontHeight + 280);
		strDisplay = "left pinkie on 'A' for Shield";
		grpOff.drawString(strDisplay,
				(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4
						+ nFontHeight + 320);

		strDisplay = "left index finger on 'F' for Guided Missile";
		grpOff.drawString(strDisplay,
				(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4
						+ nFontHeight + 360);

	}
	
	public GameFrame getFrm() {return this.gmf;}
	public void setFrm(GameFrame frm) {this.gmf = frm;}
}