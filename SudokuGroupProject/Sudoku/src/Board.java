import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.EventHandler;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class Board extends JFrame {
	
	//private Cell cellsList[];
	private Cell[][] cellsList = new Cell[9][9];
	private Border blackLine =  BorderFactory.createLineBorder(Color.BLACK, 5);
	private Container cp;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private EventHandler eh;


	public Board()
	{
		setTitle("Sudoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp = getContentPane();
		cp.setLayout(new BorderLayout());
		eh = new EventHandler();
		
		JPanel panel = new JPanel(new GridLayout(9,9,5,5));
		panel.setPreferredSize(new Dimension(700,700));	

		for (int row= 0;row<9; row++)
		{
			for (int col=0;col<9;col++)
			{
				cellsList[row][col] = new Cell(row,col);
				Cell thisCell = cellsList[row][col];
				thisCell.decideBackground();
				thisCell.setOpaque(true);
				thisCell.setText("");
				thisCell.setEditable(true);
				thisCell.addActionListener(eh);
				thisCell.addMouseListener(eh);
				panel.add(thisCell);		
			}
		}		
		getContentPane().add(panel, BorderLayout.NORTH);
		buildMenu();
		pack();
		setVisible(true);
	}
	
	public void buildMenu()
	{
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		

		JMenuItem menuItem = new JMenuItem("New");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Open");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Save");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(eh);
		fileMenu.add(menuItem);

		menuBar.add(fileMenu);

		setJMenuBar(menuBar); 
	}
	
	public static void main(String[] args) {
		Board bof = new Board();
	}

	private class EventHandler implements ActionListener, MouseListener
	{
		JFileChooser fc = new JFileChooser();
		
		public void actionPerformed(ActionEvent eventObject)
		{
			Object o = eventObject.getSource();
			
			if ( o instanceof JMenuItem)
			{	
				
				
				//here you need to make the methods for each option
				//create tostring for each board, use 0 as empty spaces
				
				if (eventObject.getActionCommand().equals("Exit")) 
				{
					System.exit(0);
				}
	
				else if (eventObject.getActionCommand().equals("New"))
				{
					//create method
					//makeNewFile();			
				}
	
				else if(eventObject.getActionCommand().equals("Save"))
				{
					//create method
					//saveAFile();
					System.out.println(this);
				}
	
				else if(eventObject.getActionCommand().equals("Open"))
				{
					//create method
					//openAFile();
				}
			}
			
			else if ( o instanceof Cell)
			{
				// if cell is clicked
				//find out which cell it was
				//set value to be input IF that is a possible solution
				
				int row = ((Cell) o).getRow();
				int col = ((Cell) o).getCol();
				
				//when something is typed into the cell, it deletes everything except the first character
				//maybe also call helper here to check to see that the one number entered
				//is an appropriate answer
				//also every time this chunk is ran, every cells' helper must be 
				//effected, as a change in one cell effects all other cells. 
				((Cell) o).addKeyListener(new KeyAdapter()
				{
					public void keyTyped(KeyEvent e)
					{
						String text = ((Cell) o).getText();
						if (text.length() >1)
						{
							((Cell) o).setText(text.replace((text.substring(1)),""));
							System.out.println("please only enter a single digit number");
							try{
								int textint = Integer.parseInt(text);
							} catch (NumberFormatException nfe)
							{
								System.out.println("please only enter a single digit number");
								((Cell) o).setText("");
							}	
						}
					}
				});
	
			}
			
			
		}
		
		@Override
		public String toString()
		{
			String written = "";
			for (int row= 0;row<9; row++)
			{
				for (int col=0;col<9;col++)
				{
					if (cellsList[row][col].getText().equals(""))
					{
						written += "O";
					}
					else
					{
						written += cellsList[row][col].getText();
					}	
				}
				written += "\n";
			}
			//System.out.print(written);
			return written;
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			
			
			// call Helper to see which numbers could be in this cell

		}
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub


		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
}
