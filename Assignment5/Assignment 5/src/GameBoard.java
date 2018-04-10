import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class holds all necessary methods to help create a working game of Concentration.
 * 
 * @author Jacob Thomas
 *
 */

public class GameBoard extends JFrame{

	private static final long serialVersionUID = 1L;
	private List<Card> cardList = new ArrayList<Card>();
	private ArrayList<Color> colorList=new ArrayList<>();
	private Card firstCard;
	private Card secondCard;
	private Card cardPicked;
	private Timer timer;
	private static int counter=0;

	/**
	 * This method does a lot of the work for the GameBoard class. Its responsibilities are as listed:
	 * Sets up the actual game board.
	 * Populates an array list with colors.
	 * Populates a list of Card objects and then shuffles them.
	 * Initializes a timer for the game.
	 * Sets the "back" of every card as light gray.
	 * 
	 */
	public GameBoard() {
		
		setTitle("Concentration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(600, 600));
		panel.setBackground(Color.white);
		panel.setLayout(new FlowLayout());
		panel.setLayout(new GridLayout(3,1, 10, 10));
				
		colorList.add(Color.RED);
		colorList.add(Color.BLUE);
		colorList.add(Color.GREEN);
		colorList.add(Color.ORANGE);
		colorList.add(Color.YELLOW);
		colorList.add(Color.MAGENTA);
		
		for (Color cardColor: colorList)
		{
			Card firstCard=new Card();
			Card secondCard=new Card();
			firstCard.setColor(cardColor);
			secondCard.setColor(cardColor);
			firstCard.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					cardPicked=firstCard;
					playerTurn();
				}			
			});
			
			secondCard.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
							cardPicked=secondCard;
							playerTurn();
				}
				
			});
			cardList.add(firstCard);
			cardList.add(secondCard);
		}
		
		Collections.shuffle(cardList);

		timer=new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e){
				checkCards();
			}
		});
		
		timer.setRepeats(false);
		
		for(Card card: cardList)
		{
			card.setBackground(Color.lightGray);
			panel.add(card);
		}
		
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null); //sets the game board to the middle of the screen 
		setVisible(true);	
	}
	/**
	 * This method first checks to see if the cards exist, then sets the background of the cards to the color they were assigned in the GameBoard method.
	 * This method also increases the counter once the user has clicked on a card.
	 */
	public void playerTurn(){
        if (firstCard == null && secondCard == null){
            firstCard = cardPicked;
            firstCard.setBackground(firstCard.getColor());
            counter++;
        }

        if (firstCard != null && firstCard != cardPicked && secondCard == null){
            secondCard = cardPicked;
            secondCard.setBackground(secondCard.getColor());
            timer.start();
            counter++;

        }
	}

	/**
	 * This method checks to see if the color of the two selected cards match. If the cards do not match, their backgrounds are reset back to light gray.
	 * This method also checks to see if the game has ended and, if so, let's the user decide if they would like to play a new game or not.
	 */
    public void checkCards(){
        if (firstCard.getColor() == secondCard.getColor()){
            firstCard.setEnabled(false); 
            secondCard.setEnabled(false);
            firstCard.setMatched(true); 
            secondCard.setMatched(true);
            if (this.hasGameEnded()){
            	String message="You clicked" + " " + counter + " " + "times." + "\n" + "Would you like to play again?";
            	String title="Game Won!";
            	int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
            	if (reply == JOptionPane.YES_OPTION)
                {
                  newGame();
                  this.dispose();
                }
            	if(reply==JOptionPane.NO_OPTION)
            	{
            		System.exit(0);
            	}
            }
        }

        else{
            firstCard.setBackground(Color.lightGray);
            secondCard.setBackground(Color.lightGray);
        }
        firstCard = null;
        secondCard = null;
    }

    /**
     * This method creates a new game if the player chooses to play the game again.
     */
    private void newGame()
    {
    	new GameBoard();
    }
    /**
     * This method checks to see if all the cards in the card list have been matched. If not it returns false. 
     * Otherwise the method returns true by default. It also prints out the number of clicks the user made to the console.
     * 
     * @return true
     */
    public boolean hasGameEnded()
    {
        for(Card c: this.cardList)
        {
            if (c.isMatched() == false)
            {
            	return false;
            }
        }
        System.out.print("Number of times clicked:" + " " + counter);
        return true;
    }
}
