import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class DesignPit implements DesignPitInterface {
    private int pitStone;
    private Shape pitShape;

    public DesignPit(Shape s) {
        this.pitShape = s;        
        this.pitStone = 0;
    }
    
    @Override
    public void setStone(int stones) {
        this.pitStone = stones;
    }

    @Override
    public int getStone(){
        return this.pitStone;
    }

    @Override
    public int getX() {
        return (int) pitShape.getBounds2D().getX();
    }

    @Override
    public int getY() {
        return (int) pitShape.getBounds2D().getY();
    }

    @Override
    public int getWidth() {
        return (int) pitShape.getBounds2D().getWidth();
    }

    @Override
    public int getHeight() {
        return (int) pitShape.getBounds2D().getHeight();
    }

    @Override
    public void draw(Graphics2D g2) {        
        g2.draw(pitShape);
        
        Font font = new Font("Arial", Font.BOLD, 11);
        drawString(g2, pitShape.getBounds(), "" + this.pitStone, font);
    }
    
    @Override
    public boolean contains(Point2D p) {
        return pitShape.contains(p);
    }    
    
    public void drawString(Graphics graphics, Rectangle rectangle, String string, Font font) {
        FontRenderContext frc = new FontRenderContext(null, true, true);
        Rectangle2D rectangle2D = font.getStringBounds(string, frc);
        int rectangleWidth = (int) Math.round(rectangle2D.getWidth());
        int rectangleHeight = (int) Math.round(rectangle2D.getHeight());
        int rectangleX = (int) Math.round(rectangle2D.getX());
        int rectangleY = (int) Math.round(rectangle2D.getY());
        int a = (rectangle.width / 2) - (rectangleWidth / 2) - rectangleX;
        int b = (rectangle.height / 2) - (rectangleHeight / 2) - rectangleY + 3;
        graphics.setFont(font);
        graphics.drawString(string, rectangle.x + a, rectangle.y + b);
    }
}
