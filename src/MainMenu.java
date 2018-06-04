import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
public class MainMenu extends JFrame implements ActionListener
{
	private Image background;
	private JButton play;
	private JButton rules;
	private JButton credits;
	private JPanel panel;
	
	private Timer timer;
	private int guiWidth;
	private int guiHeight;
	
	public MainMenu()
	{
		//close window
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//to load images
		ClassLoader cldr = getClass().getClassLoader();
		
		//set size
		guiWidth = 900; 
		guiHeight = 700; 

		//images
		ImageIcon bgIcon = new ImageIcon(cldr.getResource("bg1.png"));
		background = bgIcon.getImage();
		
		//timer
		timer = new Timer(10, this);
		timer.start();
		
		//initialize buttons
		play = new JButton("Play lucid");
	//	play.setBounds(x, y, 20, 20);
		rules = new JButton("Rules of the Game");
		credits = new JButton("Credits");
		
		//make visible
		setSize(guiWidth,guiHeight);
		setVisible(true);
		
	}
	
	public void paint(Graphics g)
	{
		Image offImage = createImage(900, 700);
		// Creates an off-screen drawable image to be used for
		// double buffering; XSIZE, YSIZE are each of type �int�;
		// represents size of JFrame or JPanel, etc
		Graphics buffer = offImage.getGraphics();
		// Creates a graphics context for drawing to an 
		// off-screen image
		paintOffScreen(buffer);		// your own method
		g.drawImage(offImage, 0, 0, null);	
		// draws the image with upper left corner at 0,0
	}

	public void paintOffScreen(Graphics g)
	{
		g.drawImage(background, 0, 0, guiWidth, guiHeight, null);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}