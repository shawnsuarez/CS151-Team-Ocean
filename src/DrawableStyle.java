import java.awt.*;
import java.util.*;

public interface DrawableStyle
{
	final int BOARD_WIDTH = 1366;
	final int BOARD_HEIGHT = 768;
	final int TOP_X = 100;
	final int TOP_Y = 20;
	final int PIT_SPACING = 20;
	
	void drawBody(Graphics2D g2);
	void drawLowerPits(Graphics2D g2, ArrayList<Pit> pits);
	void drawUpperPits(Graphics2D g2, ArrayList<Pit> pits);
	void drawScorePits(Graphics2D g2, ArrayList<Pit> pits);
	String getStyleName();
}
