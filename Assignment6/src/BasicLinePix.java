import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
/**
 * The BasicLinePix class sets up the drawing panel for a user to draw lines or other shapes on as well
 * as sets up menu options for them to be able to save their work, open previous work, or create a new work as
 * well as to be able to exit the program.
 * 
 * @author Jacob Thomas
 *
 */
public class BasicLinePix extends JFrame {

	
	private JPanel drawingPanel; // user draws here

	private Container cp;
	private JPanel statusBar;
	private JLabel statusLabel;// used to show informational messages
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private EventHandler eh;
	
	ArrayList<Lines> allLines=new ArrayList<Lines>(); //this array list holds all the created line objects
	ArrayList<String> lineString = new ArrayList<>(); //this array list is used to convert line objects to strings
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BasicLinePix lp = new BasicLinePix();	
		lp.setVisible(true);
	}

	/**
	 * This method creates the drawing panel as well as establishes an eventHandler that will be used later
	 * in the program. It also defaults the program to start in the middle of the user's screen.
	 */
	public BasicLinePix() {
		setTitle("Basic Line Pix 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		eh = new EventHandler(this);

		drawingPanel = makeDrawingPanel();
		drawingPanel.addMouseListener(eh);
		drawingPanel.addMouseMotionListener(eh);

		statusBar = createStatusBar();

		cp.add(drawingPanel, BorderLayout.CENTER);
		cp.add(statusBar, BorderLayout.SOUTH);

		buildMenu();

		pack();
		setLocationRelativeTo(null);
	}

	/**
	 * This method builds the menu that will allow a user to save their work,
	 * open a previous work, create a new work, or exit the program.
	 */
	public void buildMenu() {	
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

	/**
	 * This method establishes the dimensions of the drawing panel and sets its background
	 * to yellow
	 * 
	 * @return p
	 */
	private JPanel makeDrawingPanel() {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(500, 400));
		p.setBackground(Color.YELLOW);

		return p;
	}
	
	/**
	 * This method creates a new status bar and sets a label for the program
	 * @return
	 */
	private JPanel createStatusBar() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		statusLabel = new JLabel("Drawing Program");
		panel.add(statusLabel, BorderLayout.CENTER);

		panel.setBorder(new BevelBorder(BevelBorder.LOWERED));

		return panel;
	}

	/**
	 * 	This method overrides the paint method defined in JFrame
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	
		Graphics g1 = drawingPanel.getGraphics();
		
		for (Lines l: allLines) //this loop is used to have each line object draw itself back onto the drawing panel and utilizes the draw method in the Lines class
		{
			l.draw(g1);
		}

		// Send a message to each line in the drawing to
		// draw itself on g1
	}

	/**
	 * The EventHandler class is a nested private class within the BasicLinePix class and it handles
	 * all the action performers for the JMenu options
	 * 
	 * @author Jacob Thomas
	 *
	 */
	private class EventHandler implements ActionListener, MouseListener, MouseMotionListener {
		BasicLinePix e;
		public EventHandler(BasicLinePix e) //helps to allow BasicLinePix to dispose of itself
		{
			this.e=e;
		}

		private int startX, startY; // line's start coordinates
		private int lastX, lastY; // line's most recent end point
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getActionCommand().equals("New")) {
				BasicLinePix newWindow= new BasicLinePix();
				newWindow.setVisible(true);
				e.dispose(); //disposes the old drawing panel so that only one panel is open at a time
				allLines=new ArrayList<Lines>();
			}
			
			if (arg0.getActionCommand().equals("Save")) {
				JFileChooser fc = new JFileChooser();
				//FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text"); //helps to make it obvious to the user that text files are preferred
				//fc.setFileFilter(filter);
				int fileWindow = fc.showSaveDialog(BasicLinePix.this); //creates the save window
				
				for (Lines l : allLines) { //makes each line object into a string so that its coordinates can be put into a text file.
				    lineString.add(l.toString());
				}
				BufferedWriter fileWriter;
				try {
					if (fileWindow==JFileChooser.APPROVE_OPTION){
						
					FileWriter fw=new FileWriter(fc.getSelectedFile()); //maybe should automatically add the .txt file extension to a user's file name
//					if (fc.getSelectedFile().equals(fc.getSelectedFile()+".txt")){
					
//					}
//					else {
//					 fw=new FileWriter(fc.getSelectedFile()+ ".txt");
//							 }
					fileWriter = new BufferedWriter(fw);
					for(String l: lineString) {
						  fileWriter.write(l);
						  fileWriter.newLine();
						}
						fileWriter.close();
					}
					else {
						//prevents errors being thrown when the user decides to cancel the save dialog window
					}
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
			
			if (arg0.getActionCommand().equals("Open")) {
				JFileChooser fc = new JFileChooser();
				//FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
				//fc.setFileFilter(filter);
				int fileWindow = fc.showOpenDialog(BasicLinePix.this);
				
	            ArrayList<Lines> newAllLines=new ArrayList<Lines>();

				Scanner scanner;
				try {
					if (fileWindow==JFileChooser.APPROVE_OPTION){
					scanner = new Scanner(fc.getSelectedFile()); //the scanner is used to extract the coordinates of a line and puts it into a new array list of lines
					while (scanner.hasNext()){
		            int newStartX=scanner.nextInt();
		            int newStartY=scanner.nextInt();
		            int newLastX=scanner.nextInt();
		            int newLastY=scanner.nextInt(); 
		            Lines newLines=new Lines (newStartX, newStartY, newLastX, newLastY);
		            newAllLines.add(newLines); //this array list is used to prevent conflict between coordinates in the first allLines array list
					}
					}
					else{
						//prevents errors being thrown when the user decides to cancel the open dialog window
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}      
	            allLines=newAllLines; //sets the original lines array list to the array list that contains the saved line coordinates 
	            repaint(); //paints the saved line objects onto the drawing panel
			}
			
			else if (arg0.getActionCommand().equals("Exit")) {
				statusLabel.setText("Exiting program...");
				System.exit(0);
			} 
		}

		/**
		 * This starts the recording of a line's coordinates as the mouse is pressed down.
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			startX = e.getX();
			startY = e.getY();

			// initialize lastX, lastY
			lastX = startX;
			lastY = startY;
		}

		/**
		 * This method redraws the line that was the user drew and helps it to stick onto the drawing panel
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			// Implement rubber-band cursor
			Graphics g = drawingPanel.getGraphics();
			g.setColor(Color.black);
		
			g.setXORMode(drawingPanel.getBackground());
		
			// REDRAW the line that was drawn 
			// most recently during this drag
			// XOR mode means that yellow pixels turn black
			// essentially erasing the existing line
			g.drawLine(startX, startY, lastX, lastY);
		
			// draw line to current mouse position
			// XOR mode: yellow pixels become black
			// black pixels, like those from existing lines, temporarily become
			// yellow
			g.drawLine(startX, startY, e.getX(), e.getY());
			lastX = e.getX();
			lastY = e.getY();		
		}
		
		/**
		 * This method turns the line that a user draws into a line object and adds it to an array list of lines
		 */
		@Override
		public void mouseReleased(MouseEvent arg0)
		{
			Lines test=new Lines(startX, startY, lastX, lastY);
			allLines.add(test);
		}
		
		/**
		 * This method does nothing
		 */
		@Override
		public void mouseClicked(MouseEvent arg0) {
		}
		
		/**
		 * This method does nothing
		 */
		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		
		/**
		 * This method does nothing
		 */
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		
		/**
		 * This method does nothing
		 */
		@Override
		public void mouseMoved(MouseEvent arg0) {
		}
	}
}
