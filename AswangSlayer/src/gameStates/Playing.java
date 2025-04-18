package gameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PausedOverlay;
import utilz.LoadSave;

public class Playing extends State implements StateMethods{
	
	private Player player;
	private LevelManager levelManager;
	private PausedOverlay pausedOverlay;
	private boolean paused = false;	
	
	//screen movement variables
	private int xLvlOffset;
	private int leftBorder = (int)(0.4 * Game.GAME_WIDTH);
	private int rightBorder = (int)(0.6 * Game.GAME_WIDTH);
	private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
	private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
	private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;
	
	private BufferedImage mainBackground, parallax_1, parallax_2, parallax_3;
	
	public Playing(Game game) {
		super(game);
		initClasses();
		pausedOverlay = new PausedOverlay(this);
		
		mainBackground = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_MAIN_BG);
		parallax_1 = LoadSave.GetSpriteAtlas(LoadSave.PARALLAX_1);
		parallax_2 = LoadSave.GetSpriteAtlas(LoadSave.PARALLAX_2);
		parallax_3 = LoadSave.GetSpriteAtlas(LoadSave.PARALLAX_3);
	}

	
	private void initClasses() {
		levelManager = new LevelManager(game);
		player = new Player(0,0, (int)(126 *Game.SCALE), (int)(126 * Game.SCALE)); // player pos, scale
		player.loadLvlData(levelManager.getCurrentLevel().getLvlData());
	}
	
	
	public Player getPlayer() {
		return player;
	}


	@Override
	public void update() {
		if(!paused) {
			levelManager.update();
			player.update();
			checkCloseToBorder();
		}else {
			pausedOverlay.update();
		}
		
	}	



	private void checkCloseToBorder() {
		int playerX = (int) player.getHitbox().x;
		int diff = playerX - xLvlOffset;
		
		if(diff > rightBorder)
			xLvlOffset += diff - rightBorder;
		else if (diff < leftBorder)
			xLvlOffset += diff - leftBorder;
		
		if(xLvlOffset > maxLvlOffsetX)
			xLvlOffset = maxLvlOffsetX;
		else if (xLvlOffset < 0)
			xLvlOffset = 0;
	}


//	@Override
//	public void draw(Graphics g) {
//		g.drawImage(mainBackground, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
//		
//		levelManager.draw(g, xLvlOffset);
//		player.render(g, xLvlOffset);
//		
//		if(paused) {
//			
//			pausedOverlay.draw(g);
//		}
//		
//	}

	
	@Override
	public void draw(Graphics g) {
	    // 1. Draw the main background (static, does not move)
	    g.drawImage(mainBackground, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);

	    // 2. Get the level width in pixels
	    int levelWidth = lvlTilesWide * Game.TILES_SIZE; // Total level width in pixels

	    // 3. Calculate offsets for the parallax layers based on xLvlOffset
	    int parallax1Offset = (int) (xLvlOffset * 0.2); // Slowest layer
	    int parallax2Offset = (int) (xLvlOffset * 0.5); // Middle speed
	    int parallax3Offset = (int) (xLvlOffset * 0.7); // Fastest layer

	    // 4. Draw the parallax layers (use level dimensions, NOT screen dimensions)
	    g.drawImage(parallax_1, -parallax1Offset, 0, levelWidth, Game.GAME_HEIGHT, null);
	    g.drawImage(parallax_2, -parallax2Offset, 0, levelWidth, Game.GAME_HEIGHT, null);
	    g.drawImage(parallax_3, -parallax3Offset, 0, levelWidth, Game.GAME_HEIGHT, null);

	    // 5. Draw the level and the player
	    levelManager.draw(g, xLvlOffset);
	    player.render(g, xLvlOffset);

	    // 6. Draw paused overlay if the game is paused
	    if (paused) {
	        pausedOverlay.draw(g);
	    }
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
			player.setAttacking(true);
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		if(paused)
			pausedOverlay.mousedPressed(e);
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		if(paused)
			pausedOverlay.mouseReleased(e);		
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		if(paused)
			pausedOverlay.mouseMoved(e);		
	}
	
	public void mouseDragged(MouseEvent e) {
		if(paused)
			pausedOverlay.mouseDragged(e);
	}



	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		case KeyEvent.VK_ESCAPE:
			paused = !paused;	
			break;
		}
	}



	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(false);
			break;
		case KeyEvent.VK_D:
			player.setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(false);
			break;
		}
		
	}
	
	public void unpauseGame() {
		paused = false;
	}
	
	public void windowFocusLost() {
		player.resetDirBooleans();
	}
}
