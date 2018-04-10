import java.awt.Color;
import javax.swing.JButton;

/**
 * This class creates Card JButtons that may be used in the GameBoard class.
 * @author Jacob Thomas
 *
 */
public class Card extends JButton{
    
	
	private static final long serialVersionUID = 1L;
	private Color a;
    private boolean matched = false;

    /**
     * Sets the color of a variable
     * @param a
     */
    public void setColor(Color a){
         this.a = a;
    }

    /**
     * Gets the color of a variable
     * @return a
     */
    public Color getColor(){
        return this.a;
    }

    /**
     * Sets a card as matched
     * @param matched
     */
    public void setMatched(boolean matched){
        this.matched = matched;
    }
    
    /**
     * Allows a card to know if it has been matched.
     * @return matched
     */
    public boolean isMatched(){
        return this.matched;
    }
    
}