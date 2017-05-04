import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * The simple view of the pit. This is used by SimpleStyle
 * @author Team Ocean
 *
 */
public class SimpleStylePit implements StylePit
{
	private int x;
	private int y;
	private int width;
	private int height;
	private int index;
	
	/**
	 * Constructs a placeholder SimpleStylePit
	 */
	public SimpleStylePit()
	{
		
	}
	
	/**
	 * Constructs a SimpleStylePit
	 * @param x The top left bounding x
	 * @param y The top left bounding y
	 * @param width The width of the pit
	 * @param height The height of the pit
	 * @param index The index of the pit model this view represents
	 */
	public SimpleStylePit(int x, int y, int width, int height, int index)
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
		Rectangle2D.Double pit = new Rectangle2D.Double(x, y, width, height);
		g2.draw(pit);
		g2.setColor(new Color(239, 216, 170));
		g2.fill(pit);
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
