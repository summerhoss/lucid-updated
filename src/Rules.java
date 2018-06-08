import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;
public class Rules extends JFrame implements ActionListener
{
	private Image background;
	private JButton play;
	private JButton back;
	
	private Timer timer;
	private int guiWidth;
	private int guiHeight;
	
	public Rules()
	{	
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
	ImageIcon bgIcon = new ImageIcon(cldr.getResource("instructions.png"));
	background = bgIcon.getImage();
	
	//initialize playButton
    back = new JButton("Back to Main Menu");
    back.setSize(150, 30);
    back.setLocation(600, 600);
    back.setBackground(Color.magenta);
    back.addActionListener(this);
    
    this.setLayout(null);
    this.add(back);
    
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
	if(e.getSource() == back)
	{ 
		setVisible(false);
		MainMenu menu = new MainMenu();
	}
}

}

