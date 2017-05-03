import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.util.*;

public class SimpleStyle implements DrawableStyle
{
	private Random rand;
	/**
	 * Constructs the Simple Style, initializes a Random generator
	 */
	public SimpleStyle()
	{
		this.rand = new Random(new GregorianCalendar().getTimeInMillis());
	}
	
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
		for(int i = 12; i > 6; i--)
		{
			int pitX = UPPER_X_START + (2*6-i)*(PIT_SPACING);

			Rectangle2D.Double upperPit = new Rectangle2D.Double(pitX, UPPER_Y_START, PIT_WIDTH, PIT_HEIGHT);
			g2.draw(upperPit);
			for(int j = pits.get(i).getStones(); j > 0; j--)
			{
				int randX = rand.nextInt(PIT_WIDTH - STONE_SIZE);
				int randY = rand.nextInt(PIT_HEIGHT - STONE_SIZE);
				Rectangle2D.Double rectStone = new Rectangle2D.Double(randX + pitX, randY + UPPER_Y_START, STONE_SIZE, STONE_SIZE);
				g2.draw(rectStone);
				g2.drawString("" + pits.get(i).getStones(), pitX + PIT_WIDTH/2, UPPER_Y_START + PIT_HEIGHT + SCORE_SIZE);
			}
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
			int pitX = LOWER_X_START + i*(PIT_SPACING);
			
			Rectangle2D.Double lowerPit = new Rectangle2D.Double(pitX, LOWER_Y_START, PIT_WIDTH, PIT_HEIGHT);
			g2.draw(lowerPit);
			for(int j = pits.get(i).getStones(); j > 0; j--)
			{
				int randX = rand.nextInt(PIT_WIDTH - STONE_SIZE);
				int randY = rand.nextInt(PIT_HEIGHT - STONE_SIZE);
				Rectangle2D.Double rectStone = new Rectangle2D.Double(randX + pitX, randY + LOWER_Y_START, STONE_SIZE, STONE_SIZE);
				g2.draw(rectStone);
				g2.drawString("" + pits.get(i).getStones(), pitX + PIT_WIDTH/2, LOWER_Y_START + PIT_HEIGHT + SCORE_SIZE);
			}
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
		
		Rectangle2D.Double p2Pit = new Rectangle2D.Double(SCORE_P_X, SCORE_P_Y, SCORE_P_WIDTH, SCORE_P_HEIGHT);
		Rectangle2D.Double p1Pit = new Rectangle2D.Double(SCORE_P_X + SCORE_P_SPACING, SCORE_P_Y, SCORE_P_WIDTH, SCORE_P_HEIGHT);
		g2.draw(p2Pit);
		g2.draw(p1Pit);
		g2.drawString("" + player2.getStones(), SCORE_P_X + SCORE_P_WIDTH/2, SCORE_P_Y + SCORE_P_HEIGHT + SCORE_SIZE);
		g2.drawString("" + player1.getStones(), SCORE_P_X + SCORE_P_WIDTH/2 + SCORE_P_SPACING, SCORE_P_Y + SCORE_P_HEIGHT + SCORE_SIZE);
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
