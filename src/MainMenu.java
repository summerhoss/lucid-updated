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
	
	private Timer timer;
	private int guiWidth;
	private int guiHeight;
	
	public MainMenu()
	{
		//TODO: fix error so that it does not show up delayed
		//TODO: fix error so that another screen does not pop up
		//TODO: upload another version of the mainmenu design to make it parallel to the floor
		
		//close window
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//to load images
		ClassLoader cldr = getClass().getClassLoader();
		
		//set size
		guiWidth = 900; 
		guiHeight = 700; 

		timer = new Timer(10, this);
		timer.start();
		
		//images
		ImageIcon bgIcon = new ImageIcon(cldr.getResource("background.png"));
		background = bgIcon.getImage();
		
		//initialize playButton
        play = new JButton("Play lucid");
        play.setSize(100, 30);
        play.setLocation(300, 600);
        play.setBackground(null);
        play.addActionListener(this);
        
        //initialize rules button
        rules = new JButton("Rules of the Game");
        rules.setSize(200,30);
        rules.setLocation(475, 600);
        rules.setBackground(null);
        rules.addActionListener(this);
        
       //TODO: add buttons to make sure they pop up right away
       //TODO: finish level layout with all of the platforms and portals
        // change button background
        //finish background
        //
        
        this.setLayout(null);
        this.add(rules);
        this.add(play);
        
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
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == play)
		{ 
			Controller c = new Controller();
			c.level.createLevel1();
			setVisible(false);
		}
		if(e.getSource() == rules)
		{ 
			Rules r = new Rules();
		}
	}
	
}