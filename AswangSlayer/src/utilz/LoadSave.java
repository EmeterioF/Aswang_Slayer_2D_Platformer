package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

public class LoadSave {
	// player sprite
	public static final String PLAYER_ATLAS = "player_atlas.png";
	
	// level sprites
	public static final String LEVEL_ATLAS = "outside_sprites.png";
	public static final String LEVEL_ONE_DATA= "level_one_data_long.png";
	
	// level sprites bgs
	public static final String LEVEL_MAIN_BG= "parallax_main_bg.png";
	public static final String PARALLAX_1= "parallax_trees_1_bg.png";
	public static final String PARALLAX_2= "parallax_trees_2_bg.png";
	public static final String PARALLAX_3= "parallax_trees_3_bg.png";
	
	
	// UI sprites
	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String MENU_BACKGROUND = "menu_background.png";
	public static final String PAUSED_BACKGROUND = "pause_menu.png";
	public static final String SOUND_BUTTONS = "sound_button.png";
	public static final String URM_BUTTONS = "urm_buttons.png";
	public static final String VOLUME_BUTTONS = "volume_buttons.png";
	

	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		try {
			img = ImageIO.read(is);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return img;
	}
	
	public static int [][] GetLevelData(){
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		int [][] lvlData = new int[img.getHeight()][img.getWidth()];
		for(int j = 0; j< img.getHeight(); j++) {
			for(int i = 0; i<img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed()/16;
				if(value >= 16) {
					value = 0;
				}
				lvlData[j][i] = value;
			}
		}
		return lvlData;
	}
}
