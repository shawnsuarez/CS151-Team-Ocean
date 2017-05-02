import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class MancalaPanel extends JPanel
{
	private MancalaBoard mb;
	private DrawableStyle ds;
	
	public MancalaPanel(MancalaBoard mb)
	{
		this.mb = mb;
		this.setLayout(new GridLayout());
	}
	
	public MancalaPanel(MancalaBoard mb, DrawableStyle ds)
	{
		this.mb = mb;
		this.ds = ds;
		this.setLayout(new GridLayout());
	}
	
	public void setStyle(DrawableStyle newDS)
	{
		this.ds = newDS;
		this.repaint();
	}
	
	public DrawableStyle getStyle()
	{
		return this.ds;
	}
	
	public String getStyleName()
	{
		return this.ds.getStyleName();
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		if(ds != null)
			ds.draw(g2);
	}
}
