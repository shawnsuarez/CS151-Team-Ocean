import java.awt.*;

/**
 * StylePit interface
 * @author Team Ocean
 *
 */
public interface StylePit
{
	void draw(Graphics2D g2);
	boolean contains(Point p);
	int getPitIndex();
}
