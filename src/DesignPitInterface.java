import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public interface DesignPitInterface
{
    public void setStone(int stones);    
    public int getStone();
    public void draw(Graphics2D g2);
    public int getX();
    public int getY();
    public int getWidth();
    public int getHeight();
    public boolean contains(Point2D p);
}