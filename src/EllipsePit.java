import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class EllipsePit extends DesignPit {
    public static final int STONE_WIDTH = 5;
    public static final int STONE_HEIGHT = 5;

    public EllipsePit(int xPosition, int yPosition, int pitWidth, int pitHeight) {
        super(new Ellipse2D.Double(xPosition, yPosition, pitWidth, pitHeight));
    }
    
    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        int maxX = getWidth() / 4;
        int maxY = getHeight() / 4;
        int pitStone = getStone();
        g2.setColor(Color.BLACK);
        int count = 0;
        int maxStone = 8;
        while(count < pitStone) {
            int stoneRemain = pitStone - count;
            if(stoneRemain <= maxStone) maxStone = stoneRemain;            
            for (int j = 0; j < maxStone; j++) {
                g2.fill(new Ellipse2D.Double(getX() + (getWidth() / 2) + 
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
