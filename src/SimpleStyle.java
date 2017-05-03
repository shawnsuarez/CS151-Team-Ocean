import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.util.*;

public class SimpleStyle implements DrawableStyle
{
	/**
	 * Draws the Mancala Board
	 */
	public void drawBody(Graphics2D g2)
	{
		Rectangle2D.Double mancalaBody = new Rectangle2D.Double(TOP_X, TOP_Y, BOARD_WIDTH, BOARD_HEIGHT);
		g2.draw(mancalaBody);
	}
	
	/**
	 * Draws the Upper pits with the proper amount of stones in each pit
	 * Note: Drawn left to right
	 */
	public void drawUpperPits(Graphics2D g2, ArrayList<Pit> pits)
	{
		for(int i = 12; i > 6; i++)
		{
			
		}
	}
	
	/**
	 * Draws the Lower pits with the proper amount of stones in each pit
	 * Note: Drawn left to right
	 */
	public void drawLowerPits(Graphics2D g2, ArrayList<Pit> pits)
	{
		for(int i = 0; i < 6; i++)
		{
			
		}
	}
	
	/**
	 * Draws the Score pits with the proper amount of stones in each pit.
	 * pits.get(6) - Player 1
	 * pits.get(13) - Player 2
	 */
	public void drawScorePits(Graphics2D g2, ArrayList<Pit> pits)
	{
		Pit player1 = pits.get(6);
		Pit player2 = pits.get(13);
	}
	
	/**
	 * Gets the Style name
	 * @return the style name
	 */
	public String getStyleName()
	{
		return "Simple";
	}
}
