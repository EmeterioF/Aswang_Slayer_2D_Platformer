package ui;

import static utilz.Constants.UI.PausedButtons.SOUND_SIZE;
import static utilz.Constants.UI.URMButtons.*;
import static utilz.Constants.UI.VolumeButtons.*;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gameStates.Gamestate;
import gameStates.Playing;
import main.Game;
import utilz.LoadSave;

public class PausedOverlay {
	
	private Playing playing;
	private BufferedImage backgroundImg;
	private int bgX, bgY, bgW, bgH;
	private SoundButton musicButton, sfxButton;
	private UrmButton menuButton, replayButton, unPauseButton;
	private VolumeButton volumeButton;
	
	public PausedOverlay(Playing playing) {
		this.playing = playing;
		loadBackground();
		createSoundButton();
		createUrmButton();
		createVolumeButton();
	}
	
	private void createVolumeButton() {  // positions of VolButtons
		int vX = (int)(310 * Game.SCALE);
		int vY = (int)(240 * Game.SCALE);
		volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT);
	}

	private void createUrmButton() {  // positions of urmButtons
		int menuX = (int)(313 * Game.SCALE);
		int replayX = (int)(387 * Game.SCALE);
		int unPauseX = (int)(462 * Game.SCALE);
		int urmY = (int)(288 * Game.SCALE);
		
		unPauseButton = new UrmButton(unPauseX, urmY, URM_SIZE, URM_SIZE, 0);
		replayButton = new UrmButton(replayX, urmY, URM_SIZE, URM_SIZE,1);
		menuButton= new UrmButton(menuX, urmY, URM_SIZE, URM_SIZE, 2);
	}

	private void createSoundButton() { // positions of soundButtons
		int soundX = (int)(490 * Game.SCALE);
		int musicY = (int)(80 * Game.SCALE);
		int sfxY = (int)(130 * Game.SCALE);
		musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
		sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
	}

	private void loadBackground() {
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSED_BACKGROUND);
		bgW = (int)(backgroundImg.getWidth() * Game.SCALE);
		bgH = (int)(backgroundImg.getHeight() * Game.SCALE);
		bgX = 0;
		bgY = 0;
	}

	public void update() {
		musicButton.update();
		sfxButton.update();
		
		menuButton.update();
		replayButton.update();
		unPauseButton.update();
		
		volumeButton.update();
	}
	
	public void draw(Graphics g) {
		// BACKGROUNMD
		g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);
		
		//SOUND BUTTONS
		musicButton.draw(g);
		sfxButton.draw(g);
		
		menuButton.draw(g);
		replayButton.draw(g);
		unPauseButton.draw(g);
		
		volumeButton.draw(g);
	}
	
	public void mouseDragged(MouseEvent e) {
		if (volumeButton.isMousePressed()) {
			volumeButton.changeX(e.getX());
		}

	}
	public void mousedPressed(MouseEvent e) {
	    if (isIn(e, musicButton)) {
	        musicButton.setMousePressed(true);
	    } else if (isIn(e, sfxButton)) {
	        sfxButton.setMousePressed(true);
	    } else if (isIn(e, menuButton)) {
	        menuButton.setMousePressed(true);
	    } else if (isIn(e, replayButton)) {
	        replayButton.setMousePressed(true);
	    } else if (isIn(e, unPauseButton)) {
	        unPauseButton.setMousePressed(true);
	    }else if (isIn(e, volumeButton)) {
	    	volumeButton.setMousePressed(true);
	    }
	    
	}

	public void mouseReleased(MouseEvent e) {
	    if (isIn(e, musicButton)) {
	        if (musicButton.isMousePressed()) {
	            musicButton.setMuted(!musicButton.isMuted());
	        }
	    } else if (isIn(e, sfxButton)) {
	        if (sfxButton.isMousePressed()) {
	            sfxButton.setMuted(!sfxButton.isMuted());
	        }
	    } else if (isIn(e, menuButton)) {
	        if (menuButton.isMousePressed()) {
	            Gamestate.state = Gamestate.MENU;
	            playing.unpauseGame();
	        }
	    } else if (isIn(e, replayButton)) {
	        if (replayButton.isMousePressed()) {
	        }
	    } else if (isIn(e, unPauseButton)) {
	        if (unPauseButton.isMousePressed()) {
	            playing.unpauseGame();
	        }
	    }
	    // Reset all button states
	    musicButton.resetBools();
	    sfxButton.resetBools();
	    menuButton.resetBools();
	    replayButton.resetBools();
	    unPauseButton.resetBools();
	    volumeButton.resetBools();
	}

	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
		menuButton.setMouseOver(false);
		replayButton.setMouseOver(false);
		unPauseButton.setMouseOver(false);
		volumeButton.setMouseOver(false);
		
		
		if (isIn(e, musicButton))
		    musicButton.setMouseOver(true);
		else if (isIn(e, sfxButton))
			sfxButton.setMouseOver(true); 
		else if (isIn(e, menuButton))
			menuButton.setMouseOver(true); 
		else if (isIn(e, replayButton))
			replayButton.setMouseOver(true); 
		else if (isIn(e, unPauseButton))
			unPauseButton.setMouseOver(true); 
		else if (isIn(e, volumeButton))
			volumeButton.setMouseOver(true); 
		
	}
	
	private boolean isIn(MouseEvent e, PausedButton b) {
	    boolean result = b.getBounds().contains(e.getX(), e.getY());
	    return result;
	}



	
	
}
