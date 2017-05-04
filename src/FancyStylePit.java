import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * The fancy view of the pit. This is used by FancyStyle
 * @author Team Ocean
 *
 */
public class FancyStylePit implements StylePit
{
	private int x;
	private int y;
	private int width;
	private int height;
	private int index;
	
	/**
	 * Constructs a placeholder SimpleStylePit
	 */
	public FancyStylePit()
	{
		
	}
	
	/**
	 * Constructs a FancyStylePit
	 * @param x The top left bounding x
	 * @param y The top left bounding y
	 * @param width The width of the pit
	 * @param height The height of the pit
	 * @param index The index of the pit model this view represents
	 */
	public FancyStylePit(int x, int y, int width, int height, int index)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.index = index;
	}
	
	/**
	 * Draws the Pit
	 * @param g2 Graphics2D
	 */
	public void draw(Graphics2D g2)
	{
		g2.setColor(Color.BLACK);
		Ellipse2D.Double pit = new Ellipse2D.Double(x, y, width, height);
		g2.draw(pit);
	}

	/**
	 * Checks if the given point is within the Pit's bounds
	 * @param p The given point
	 * @return True/ False
	 */
	public boolean contains(Point p)
	{
		double pX = p.getX();
		double pY = p.getY();
		
		if(pX >= x && pX <= x+width && pY >= y && pY <= y+height)
			return true;
		return false;
	}
	
	/**
	 * Gets the index of the represented pit
	 * @return The pit index
	 */
	public int getPitIndex()
	{
		return index;
	}
}