import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class SimpleStyle implements DrawableStyle
{
	/**
	 * Draws the Mancala Board
	 */
	public void draw(Graphics2D g2)
	{
		Rectangle2D.Double mancalaBody = new Rectangle2D.Double(50, 50, 1366, 768);
		
		g2.draw(mancalaBody);
	}
	
	public String getStyleName()
	{
		return "Simple";
	}
}
