import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class FancyStyle implements DrawableStyle
{
	/**
	 * Draws the Mancala Board
	 */
	public void draw(Graphics2D g2)
	{
		Ellipse2D.Double mancalaBody = new Ellipse2D.Double(50, 50, 1366, 768);
		g2.draw(mancalaBody);
	}
	
	public String getStyleName()
	{
		return "Fancy";
	}
}
