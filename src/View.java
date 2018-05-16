import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class View extends JFrame implements ActionListener
{
	private Level level;
	private int guiWidth;
	private int guiHeight;
	private Image bg;
	private Image gemState1;
	private Image gemState2;
	private Image char1;
	private Image char2;
	private Image char3;
	private Image uni;
	private Image wings;
	private Image tree;
	private Timer timer;
	private int state;

	public View(Level lv)
	{
		level = lv;
		
		//close window
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//to load images
		ClassLoader cldr = getClass().getClassLoader();

		//images
		ImageIcon bgIcon = new ImageIcon(cldr.getResource("bg1.png"));
		bg = bgIcon.getImage();
		ImageIcon gem1Icon = new ImageIcon(cldr.getResource("gemState1.png"));
		gemState1 = gem1Icon.getImage();
		ImageIcon gem2Icon = new ImageIcon(cldr.getResource("gemState2.png"));
		gemState2 = gem2Icon.getImage();
		ImageIcon char2Icon = new ImageIcon(cldr.getResource("char2.png"));
		char2 = char2Icon.getImage();
		ImageIcon char3Icon = new ImageIcon(cldr.getResource("char3.png"));
		char3 = char3Icon.getImage();
		ImageIcon char1Icon = new ImageIcon(cldr.getResource("char.png"));
		char1 = char1Icon.getImage();
		ImageIcon uniIcon = new ImageIcon(cldr.getResource("unicorn.png"));
		uni = uniIcon.getImage();
		ImageIcon wingsIcon = new ImageIcon(cldr.getResource("wings.png"));
		wings = wingsIcon.getImage();
		ImageIcon treeIcon = new ImageIcon(cldr.getResource("tree.png"));
		tree = treeIcon.getImage();
		
		
		//initialize and start timer
		timer = new Timer(10, this);
		timer.start();
		
		//initialize boolean
		state = 0;
		

		//set size
		guiWidth = 1300;
		guiHeight = 1000;

		//make visible
		setSize(guiWidth,guiHeight);
		setVisible(true);

	}
	
	public void paint(Graphics g)
	{
		Image offImage = createImage(1300, 1000);
	// Creates an off-screen drawable image to be used for
	// double buffering; XSIZE, YSIZE are each of type ‘int’;
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
		// sometimes helpful to do this first to clear things:
		//g.clearRect(0, 0, 800, 800);
		
		g.drawImage(bg, 0, 0, guiWidth, guiHeight, null);
		
		//platforms
		for(Platform plat: level.getPlats())
			g.drawImage(plat.getType(), (int)plat.getX(), (int)plat.getY(), (int)plat.getWidth(), (int)plat.getHeight(), null);
		
		g.drawImage(uni, 1150, 850, 100, 100, null);
		g.drawImage(wings, 950, 400, 100, 50, null);
		g.drawImage(tree, 950, 700, 150, 250, null);
		
		if(state >= 50)
		{
			g.drawImage(gemState1, 325, 250, 50, 50, null);
		}
		else
		{
			g.drawImage(gemState2, 325, 250, 50, 50, null);
		}
		
		if(state <= 25)
			g.drawImage(char1, 1100, 100, 50, 100, null);
		else if(state <= 50)
			g.drawImage(char2, 1100, 100, 50, 100, null);
		else if(state <= 75)
			g.drawImage(char1, 1100, 100, 50, 100, null);
		else
			g.drawImage(char3, 1100, 100, 50, 100, null);
			
	}
	
	/**
	public static void main(String[] args)
	{
		View app = new View();
	}
	**/

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(state <= 100)
			state++;
		else
			state = 0;
		
		for(Platform plat: level.getPlats())
			if(plat instanceof Cloud)
				((Cloud) plat).shift();
		
		repaint();
	}

}
