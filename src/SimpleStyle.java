import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.util.*;

/**
 * SimpleStyle strategy
 * @author Team Ocean
 *
 */
public class SimpleStyle implements DrawableStyle
{
	private Random rand;
	private ArrayList<SimpleStylePit> simplePits;
	
	/**
	 * Constructs the Simple Style, initializes a Random generator and the StylePit ArrayList
	 */
	public SimpleStyle()
	{
		this.rand = new Random(new GregorianCalendar().getTimeInMillis());
		this.simplePits = new ArrayList<SimpleStylePit>();
		for(int i = 0; i < 14; i++)
		{
			simplePits.add(new SimpleStylePit()); //Fill the array with placeholder pits
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
		g2.setColor(new Color(96, 66, 4));
		g2.fill(mancalaBody);
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

			SimpleStylePit simpleUpperPit = new SimpleStylePit(pitX, UPPER_Y_START, PIT_WIDTH, PIT_HEIGHT, i);
			simplePits.set(i, simpleUpperPit);
			simpleUpperPit.draw(g2);

			for(int j = pits.get(i).getStones(); j > 0; j--)
			{
				int randX = rand.nextInt(PIT_WIDTH - STONE_SIZE);
				int randY = rand.nextInt(PIT_HEIGHT - STONE_SIZE);
				g2.setColor(Color.BLACK);
				
				Rectangle2D.Double rectStone = new Rectangle2D.Double(randX + pitX, randY + UPPER_Y_START, STONE_SIZE, STONE_SIZE);
				g2.draw(rectStone);
				g2.drawString("" + pits.get(i).getStones(), pitX + PIT_WIDTH/2, UPPER_Y_START + PIT_HEIGHT + SCORE_SIZE);
				g2.setColor(Color.BLUE);
				g2.fill(rectStone);
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
			
			SimpleStylePit simpleLowerPit = new SimpleStylePit(pitX, LOWER_Y_START, PIT_WIDTH, PIT_HEIGHT, i);
			simplePits.set(i, simpleLowerPit);
			simpleLowerPit.draw(g2);
			
			for(int j = pits.get(i).getStones(); j > 0; j--)
			{
				int randX = rand.nextInt(PIT_WIDTH - STONE_SIZE);
				int randY = rand.nextInt(PIT_HEIGHT - STONE_SIZE);
				g2.setColor(Color.BLACK);
				
				Rectangle2D.Double rectStone = new Rectangle2D.Double(randX + pitX, randY + LOWER_Y_START, STONE_SIZE, STONE_SIZE);
				g2.draw(rectStone);
				g2.drawString("" + pits.get(i).getStones(), pitX + PIT_WIDTH/2, LOWER_Y_START + PIT_HEIGHT + SCORE_SIZE);
				g2.setColor(Color.BLUE);
				g2.fill(rectStone);
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
		
		SimpleStylePit p1Pit = new SimpleStylePit(SCORE_P_X + SCORE_P_SPACING, SCORE_P_Y, SCORE_P_WIDTH, SCORE_P_HEIGHT, 6);
		SimpleStylePit p2Pit = new SimpleStylePit(SCORE_P_X, SCORE_P_Y, SCORE_P_WIDTH, SCORE_P_HEIGHT, 13);
		
		p1Pit.draw(g2);
		p2Pit.draw(g2);

		for(int i = player1.getStones(); i > 0; i--)
		{
			int randX = rand.nextInt(SCORE_P_WIDTH - STONE_SIZE);
			int randY = rand.nextInt(SCORE_P_HEIGHT - STONE_SIZE);
			g2.setColor(Color.BLACK);
			
			Rectangle2D.Double rectStone = new Rectangle2D.Double(randX + SCORE_P_X + SCORE_P_SPACING, randY + SCORE_P_Y, STONE_SIZE, STONE_SIZE);
			g2.draw(rectStone);
			
			g2.setColor(Color.BLUE);
			g2.fill(rectStone);
		}
		
		for(int j = player2.getStones(); j > 0; j--)
		{
			int randX = rand.nextInt(SCORE_P_WIDTH - STONE_SIZE);
			int randY = rand.nextInt(SCORE_P_HEIGHT - STONE_SIZE);
			g2.setColor(Color.BLACK);
			
			Rectangle2D.Double rectStone = new Rectangle2D.Double(randX + SCORE_P_X, randY + SCORE_P_Y, STONE_SIZE, STONE_SIZE);
			g2.draw(rectStone);
			
			g2.setColor(Color.BLUE);
			g2.fill(rectStone);
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
		return simplePits;
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
