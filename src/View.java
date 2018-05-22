import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class View extends JFrame implements ActionListener, KeyListener
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
	private boolean gemState;
	private Image platform;

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
		
		ImageIcon platformIcon = new ImageIcon(cldr.getResource("platform.png"));
		platform = platformIcon.getImage();
		
		//initialize and start timer
		timer = new Timer(10, this);
		timer.start();
		
		//initialize boolean
		state = 0;
		gemState = true;
		

		//set size
		guiWidth = 1300; //old val: 1300
		guiHeight = 800; //old val: 1000
		//1280�720
		
		addKeyListener(this);

		//make visible
		setSize(guiWidth,guiHeight);
		setVisible(true);

	}
	
	public void paint(Graphics g)
	{
		Image offImage = createImage(1300, 800);
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
		// sometimes helpful to do this first to clear things:
		//g.clearRect(0, 0, 800, 800);
		
		g.drawImage(bg, 0, 0, guiWidth, guiHeight, null);
		
		//platforms
		for(Model m: Level.getGameObjects())
		{
			g.drawImage(((Model)m).getType(), (int)((Model)m).getX(), (int)((Model)m).getY(), (int)((Model)m).getWidth(), (int)((Model)m).getHeight(), null);
		}
		
		g.drawImage(uni, 1150, 850, 100, 100, null);
		g.drawImage(wings, 950, 400, 100, 50, null);
		g.drawImage(tree, 950, 700, 150, 250, null);
		
		if(state >= 50)
			g.drawImage(gemState1, 325, 250, 50, 50, null);
		else
			g.drawImage(gemState2, 325, 250, 50, 50, null);
		
		if(!gemState)
			g.fillRect(325, 250, 50, 50);
		
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
		for(Model m: Level.getGameObjects())
		{
			if(m instanceof Player)
			{
				m.run();
			}
			if(m instanceof Platform)
			{
				if(m instanceof Cloud)
					((Cloud) m).shift();
			}
			if(m instanceof Gem)
			{		
				if(((Gem) m).getVisible() == false)
				{
					gemState = false;
				}
			}
			if(m instanceof Seed)
			{
				
			}
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			level.getPlayer().setTrue(Moveable.L);
			break;
		case KeyEvent.VK_RIGHT:
			level.getPlayer().setTrue(Moveable.R);
			break;
		case KeyEvent.VK_UP:
			level.getPlayer().setTrue(Moveable.U);
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_LEFT:
				level.getPlayer().setFalse(Moveable.L);
				break;
			case KeyEvent.VK_RIGHT:
				level.getPlayer().setFalse(Moveable.R);
				break;
			case KeyEvent.VK_UP:
				level.getPlayer().setFalse(Moveable.U);
				break;
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
