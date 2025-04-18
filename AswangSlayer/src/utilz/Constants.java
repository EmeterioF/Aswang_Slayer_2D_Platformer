package utilz;

import main.Game;

public class Constants {
	
	public static class UI{
		
		public static class VolumeButtons{
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;
			
			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);

		}
		
		public static class PausedButtons{
			public static final int SOUND_SIZE_DEFAULT = 42;
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
			
		}
		
		public static class URMButtons{
			public static final int URM_SIZE_DEFAULT = 56;
			public static final int URM_SIZE = (int) (URM_SIZE_DEFAULT * Game.SCALE);
		}
		
		public static class Buttons{
			public static final int B_WIDTH_DEFAULT = 276; // might want to change if button errors
			public static final int B_HEIGHT_DEFAULT = 55;
			public static final int B_WIDTH = (int)(B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int)(B_HEIGHT_DEFAULT * Game.SCALE);
			
		}
	}
	
	public static class Directions{
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	
	public static class PlayerConstants{
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int JUMP_1 = 2;
		public static final int JUMP_2 = 3;
		public static final int DASH = 4;
		public static final int FALL = 5;
		public static final int GROUND = 6;
		public static final int ATTACK_1= 7;
		public static final int ATTACK_2= 8;
		public static final int ATTACK_3= 9;
		public static final int DEATH= 10;
		public static final int HIT= 11;
		
		
		public static int GetSpriteAmount(int playerAction) {
			
			switch(playerAction) {
				case IDLE : return 30;
				case FALL :  
				case RUNNING : return 25;
				case GROUND :
				case HIT:
				case JUMP_1 : 
				case JUMP_2 : return 20; 
				case ATTACK_1:
				case DASH : return 13;
				case DEATH: 
				case ATTACK_2: return 15;
				case ATTACK_3: return 11;
				default : return 1;
			}
			
		}
	}
}
