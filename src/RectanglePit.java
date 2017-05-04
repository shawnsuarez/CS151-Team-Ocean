import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class RectanglePit extends DesignPit
{	
    public static final int STONE_WIDTH = 5;
    public static final int STONE_HEIGHT = 5;
    public RectanglePit(int xPosition, int yPosition, int pitWidth, int pitHeight) {
        super(new Rectangle2D.Double(xPosition, yPosition, pitWidth, pitHeight));
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        int maxX = getWidth() / 4;
        int maxY = getHeight() / 4;
        int pitStone = getStone();
        g2.setColor(Color.RED);
        int count = 0;
        int maxStone = 8;
        while(count < pitStone) {
            int stoneRemain = pitStone - count;
            if(stoneRemain <= maxStone) maxStone = stoneRemain;           
            for (int j = 0; j < maxStone; j++) {
                g2.fill(new Rectangle2D.Double(getX() + (getWidth() / 2) + 
                		(maxX * Math.cos((j * (360 / maxStone)) * Math.PI / (360 / 2)))
                        - (STONE_WIDTH / 2), getY() + (getHeight() / 2) + 
                        (maxY * Math.sin((j * (360 / maxStone)) * Math.PI / (360 / 2)))
                        - (STONE_HEIGHT / 2), STONE_WIDTH, STONE_HEIGHT));
                count++;
            }            
            maxStone += 12;
            maxX += (getWidth() / 6);
            maxY += (getHeight() / 6);
        }
        g2.setColor(Color.BLACK);
    }
}
