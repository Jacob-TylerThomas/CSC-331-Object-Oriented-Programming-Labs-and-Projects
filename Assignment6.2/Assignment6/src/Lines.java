import java.awt.Graphics;

/**
 * The Lines class helps to create line objects so that when a user draws a line onto the drawing panel, 
 * their line is able to stay on the panel if the panel were to be minimized.
 * 
 * @author Jacob Thomas
 *
 */
public class Lines extends Shapes{


private int startX, startY, lastX, lastY;

/**
 * This method initializes the coordinates for a line.
 * @param startX
 * @param startY
 * @param lastX
 * @param lastY
 */
public Lines(int startX, int startY, int lastX, int lastY) {
	this.startX=startX;
	this.startY=startY;
	this.lastX=lastX;
	this.lastY=lastY;
}

/**
 * This method is used in conjunction with the overridden paint method in BasicLinePix so that a line can draw itself 
 * onto the drawing pad
 * @param g1
 */

@Override
public void draw(Graphics g1)
{
	g1.drawLine(startX, startY, lastX, lastY);
}

/**
 * This method creates a toString method for a line
 */
public String toString()
{
	return "L" + " " + startX + " " + startY + " " + lastX + " " + lastY;
}

}