import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JTextField;
 

public class Cell extends JTextField {

	int row;
	int col;

	//each cell is a JTextFeild
	public Cell(int r, int c)
	{
		super.setHorizontalAlignment(CENTER);
		row = r;
		col = c;
		this.setText("");
		
	}
	
	public int getCol()
	{
		return col;
	}
	
	public int getRow()
	{
		return row;
	}
	
	
	// use part of this method to add each cell to a 3X3 square.
	//maybe a list? something to keep track of the 3X3's. 
	//also to save from having to rewrite all these loops
	public void decideBackground()
	{
		if (row<3)
		{
			if (col<3)
			{
				this.setBackground(Color.LIGHT_GRAY);
			}
			else if ( col>5)
			{
				this.setBackground(Color.LIGHT_GRAY);
			}
			else
			{
				this.setBackground(Color.RED);
			}
		}
		
		else if (row>5)
		{
			if (col<3)
			{
				this.setBackground(Color.LIGHT_GRAY);
			}
			else if ( col>5)
			{
				this.setBackground(Color.LIGHT_GRAY);
			}
			else
			{
				this.setBackground(Color.RED);
			}
		}		
		else if (col>2)
		{
			if (col<6)
			{
				this.setBackground(Color.LIGHT_GRAY);
			}
			else 
			{
				this.setBackground(Color.RED);
			}	
		}		
		else
		{
			this.setBackground(Color.RED);
		}
	}
		

}

