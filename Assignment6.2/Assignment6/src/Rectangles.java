import java.awt.Graphics;

/**
 *  * The Rectangles class helps to create line objects so that when a user draws a rectangle onto the drawing panel, 
 * their rectangle is able to stay on the panel if the panel were to be minimized.
 * @author Jacob Thomas
 *
 */
public class Rectangles extends Shapes {
	private int width, height;
	private int startX, startY, lastX, lastY;
	
	/**
	 * This method initializes the coordinates for a rectangle.
	 * @param startX
	 * @param startY
	 * @param lastX
	 * @param lastY
	 */
	public Rectangles(int startX, int startY, int lastX, int lastY)
	{
		this.startX = startX;
		this.startY = startY;
		this.width = Math.abs(lastX-startX);
		this.height = Math.abs(lastY-startY);
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.drawRect(startX, startY, width, height);
	}
	
	@Override
	public String toString() {
		return "R" + " "+  startX + " " + startY + " " + lastX + " " + lastY;
	
	}
}
