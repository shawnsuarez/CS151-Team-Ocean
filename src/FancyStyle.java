import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.*;

/**
 * FancyStyle strategy
 * @author Team Ocean
 *
 */
public class FancyStyle implements DrawableStyle
{
	private Random rand;
	private ArrayList<FancyStylePit> fancyPits;
	
	/**
	 * Constructs the Fancy Style, initializes a Random generator and the StylePit ArrayList
	 */
	public FancyStyle()
	{
		this.rand = new Random(new GregorianCalendar().getTimeInMillis());
		this.fancyPits = new ArrayList<FancyStylePit>();
		for(int i = 0; i < 14; i++)
		{
			fancyPits.add(new FancyStylePit()); //Fill the array with placeholder pits
		}
	}
	
	/**
	 * Draws the Mancala Board Body
	 * @param g2 Graphics2D
	 */
	public void drawBody(Graphics2D g2)
	{
		Rectangle2D.Double mancalaBody = new Rectangle2D.Double(TOP_X, TOP_Y, BOARD_WIDTH, BOARD_HEIGHT);
		g2.draw(mancalaBody);
	}
	
	/**
	 * Draws the Upper pits with the proper amount of stones in each pit
	 * Note: Drawn left to right
	 * @param g2 Graphics2D
	 * @param pits The given pit model
	 */
	public void drawUpperPits(Graphics2D g2, ArrayList<Pit> pits)
	{
		for(int i = 12; i > 6; i--)
		{
			int pitX = UPPER_X_START + (2*6-i)*(PIT_SPACING);

			FancyStylePit fancyUpperPit = new FancyStylePit(pitX, UPPER_Y_START, PIT_WIDTH, PIT_HEIGHT, i);
			fancyPits.set(i, fancyUpperPit);
			fancyUpperPit.draw(g2);

			for(int j = pits.get(i).getStones(); j > 0; j--)
			{
				int randX = rand.nextInt(PIT_WIDTH - STONE_SIZE);
				int randY = rand.nextInt(PIT_HEIGHT - STONE_SIZE);
				g2.setColor(Color.BLACK);
				
				Ellipse2D.Double elliStone = new Ellipse2D.Double(randX + pitX, randY + UPPER_Y_START, STONE_SIZE, STONE_SIZE);
				g2.draw(elliStone);
				g2.drawString("" + pits.get(i).getStones(), pitX + PIT_WIDTH/2, UPPER_Y_START + PIT_HEIGHT + SCORE_SIZE);
				g2.setColor(Color.MAGENTA);
				g2.fill(elliStone);
			}
		}
	}
	
	/**
	 * Draws the Lower pits with the proper amount of stones in each pit
	 * Note: Drawn left to right
	 * @param g2 Graphics2D
	 * @param pits The given pit model
	 */
	public void drawLowerPits(Graphics2D g2, ArrayList<Pit> pits)
	{
		for(int i = 0; i < 6; i++)
		{
			int pitX = LOWER_X_START + i*(PIT_SPACING);
			
			FancyStylePit fancyLowerPit = new FancyStylePit(pitX, LOWER_Y_START, PIT_WIDTH, PIT_HEIGHT, i);
			fancyPits.set(i, fancyLowerPit);
			fancyLowerPit.draw(g2);

			for(int j = pits.get(i).getStones(); j > 0; j--)
			{
				int randX = rand.nextInt(PIT_WIDTH - STONE_SIZE);
				int randY = rand.nextInt(PIT_HEIGHT - STONE_SIZE);
				g2.setColor(Color.BLACK);
				
				Ellipse2D.Double elliStone = new Ellipse2D.Double(randX + pitX, randY + LOWER_Y_START, STONE_SIZE, STONE_SIZE);
				g2.draw(elliStone);
				g2.drawString("" + pits.get(i).getStones(), pitX + PIT_WIDTH/2, LOWER_Y_START + PIT_HEIGHT + SCORE_SIZE);
				g2.setColor(Color.MAGENTA);
				g2.fill(elliStone);
			}
		}
	}
	
	/**
	 * Draws the Score pits with the proper amount of stones in each pit
	 * pits.get(6) - Player 1
	 * pits.get(13) - Player 2
	 * @param g2 Graphics2D
	 * @param pits The given pit model
	 */
	public void drawScorePits(Graphics2D g2, ArrayList<Pit> pits)
	{
		Pit player1 = pits.get(6);
		Pit player2 = pits.get(13);
		
		FancyStylePit p1Pit = new FancyStylePit(SCORE_P_X + SCORE_P_SPACING, SCORE_P_Y, SCORE_P_WIDTH, SCORE_P_HEIGHT, 6);
		FancyStylePit p2Pit = new FancyStylePit(SCORE_P_X, SCORE_P_Y, SCORE_P_WIDTH, SCORE_P_HEIGHT, 13);
		
		p1Pit.draw(g2);
		p2Pit.draw(g2);
		
		for(int i = player1.getStones(); i > 0; i--)
		{
			int randX = rand.nextInt(SCORE_P_WIDTH - STONE_SIZE);
			int randY = rand.nextInt(SCORE_P_HEIGHT - STONE_SIZE);
			g2.setColor(Color.BLACK);
			
			Ellipse2D.Double elliStone = new Ellipse2D.Double(randX + SCORE_P_X + SCORE_P_SPACING, randY + SCORE_P_Y, STONE_SIZE, STONE_SIZE);
			g2.draw(elliStone);
			
			g2.setColor(Color.MAGENTA);
			g2.fill(elliStone);
		}
		
		for(int j = player2.getStones(); j > 0; j--)
		{
			int randX = rand.nextInt(SCORE_P_WIDTH - STONE_SIZE);
			int randY = rand.nextInt(SCORE_P_HEIGHT - STONE_SIZE);
			g2.setColor(Color.BLACK);
			
			Ellipse2D.Double elliStone = new Ellipse2D.Double(randX + SCORE_P_X, randY + SCORE_P_Y, STONE_SIZE, STONE_SIZE);
			g2.draw(elliStone);
			
			g2.setColor(Color.MAGENTA);
			g2.fill(elliStone);
		}
		
		g2.setColor(Color.BLACK);
		g2.drawString("" + player2.getStones(), SCORE_P_X + SCORE_P_WIDTH/2, SCORE_P_Y + SCORE_P_HEIGHT + SCORE_SIZE);
		g2.drawString("" + player1.getStones(), SCORE_P_X + SCORE_P_WIDTH/2 + SCORE_P_SPACING, SCORE_P_Y + SCORE_P_HEIGHT + SCORE_SIZE);
	}
	
	/**
	 * Gets the pit views
	 * @return The pit views
	 */
	public ArrayList<? extends StylePit> getStylePits()
	{
		return fancyPits;
	}
	
	/**
	 * Gets the Style name
	 * @return the style name
	 */
	public String getStyleName()
	{
		return "Fancy";
	}
}
