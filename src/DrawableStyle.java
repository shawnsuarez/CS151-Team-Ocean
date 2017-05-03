import java.awt.*;
import java.util.*;

public interface DrawableStyle
{
	final int BOARD_WIDTH = 1366;
	final int BOARD_HEIGHT = 768;
	
	final int TOP_X = 100;
	final int TOP_Y = 10;
	
	final int UPPER_X_START = 325;
	final int UPPER_Y_START = 150;
	
	final int LOWER_X_START = 325;
	final int LOWER_Y_START = 450;
	
	final int PIT_WIDTH = 125;
	final int PIT_HEIGHT = 125;
	final int PIT_SPACING = PIT_WIDTH + 35;
	
	final int SCORE_P_X = TOP_X + 50;
	final int SCORE_P_Y = TOP_Y + 110;
	final int SCORE_P_WIDTH = 125;
	final int SCORE_P_HEIGHT = 500;
	final int SCORE_P_SPACING = 1150;
	
	final int STONE_SIZE = 20;
	final int SCORE_SIZE = 20;
	
	void drawBody(Graphics2D g2);
	void drawLowerPits(Graphics2D g2, ArrayList<Pit> pits);
	void drawUpperPits(Graphics2D g2, ArrayList<Pit> pits);
	void drawScorePits(Graphics2D g2, ArrayList<Pit> pits);
	String getStyleName();
}
