import java.awt.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.util.*;

/**
 * The View that draws the Mancala Board
 * @author Team Ocean - Shawn Darrel Suarez
 *
 */
public class MancalaPanel extends JPanel
{
	private MancalaBoard mb;
	private DrawableStyle ds;
	
	/**
	 * Constructs the MancalaPanel with a reference to the Model
	 * Also creates
	 * @param mb
	 */
	public MancalaPanel(MancalaBoard mb)
	{
		this.mb = mb;
		this.setLayout(new GridLayout());
	}
	
	/**
	 * Constructs the MancalaPanel with a reference to the Model and a set Strategy
	 * @param mb The Model of a mancala board
	 * @param ds The strategy to draw the board
	 */
	public MancalaPanel(MancalaBoard mb, DrawableStyle ds)
	{
		this(mb);
		this.ds = ds;
	}
	
	/**
	 * Sets the strategy to draw the board
	 * @param newDS The Strategy to draw the board
	 */
	public void setStyle(DrawableStyle newDS)
	{
		this.ds = newDS;
		this.repaint();
	}
	
	/**
	 * Gets the strategy
	 * @return The strategy
	 */
	public DrawableStyle getStyle()
	{
		return this.ds;
	}
	
	/**
	 * Getst the name of the current strategy
	 * @return The strategy name
	 */
	public String getStyleName()
	{
		return this.ds.getStyleName();
	}
	
	/**
	 * Paints the Panel
	 * @param g Graphics
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		if(ds != null)
		{
			ds.drawBody(g2);
			ds.drawUpperPits(g2, mb.getPits());
			ds.drawLowerPits(g2, mb.getPits());
			ds.drawScorePits(g2, mb.getPits());
		}
	}
}
