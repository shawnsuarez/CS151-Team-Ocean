import java.awt.*;
import java.util.*;

/**
 * DrawableStyle interface
 * @author Team Ocean - Shawn Darrel Suarez
 *
 */
public interface DrawableStyle
{
	final int BOARD_WIDTH = 1375;
	final int BOARD_HEIGHT = 650;
	
	final int TOP_X = 5;
	final int TOP_Y = 10;
	
	final int UPPER_X_START = TOP_X + 225;//325;
	final int UPPER_Y_START = TOP_Y + 130; //150;
	
	final int LOWER_X_START = TOP_X + 225;
	final int LOWER_Y_START = TOP_Y + 360; //450
	
	final int PIT_WIDTH = 125;
	final int PIT_HEIGHT = 125;
	final int PIT_SPACING = PIT_WIDTH + 35;
	
	final int SCORE_P_X = TOP_X + 50;
	final int SCORE_P_Y = TOP_Y + 50; //110
	final int SCORE_P_WIDTH = 125;
	final int SCORE_P_HEIGHT = 500;
	final int SCORE_P_SPACING = 1150;
	
	final int STONE_SIZE = 20;
	final int SCORE_SIZE = 20;
	
	void drawBody(Graphics2D g2);
	void drawLowerPits(Graphics2D g2, ArrayList<Pit> pits);
	void drawUpperPits(Graphics2D g2, ArrayList<Pit> pits);
	void drawScorePits(Graphics2D g2, ArrayList<Pit> pits);
	ArrayList<? extends StylePit> getStylePits();
	String getStyleName();
}
