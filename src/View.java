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
	private Image vine;
	private Image stick;
	private Image sign;

	private Timer timer;
	private int state;
	private JLabel gemLabel;
	private Image gem;
	private JPanel panel;
	private boolean gemState;
	private Image platform;

	public View(Level lv)
	{
		panel = new JPanel();
		level = lv;

		//close window
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//to load images
		ClassLoader cldr = getClass().getClassLoader();

		//images
		ImageIcon bgIcon = new ImageIcon(cldr.getResource("bg1.png"));
		bg = bgIcon.getImage();
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
		ImageIcon gemIcon = new ImageIcon(cldr.getResource("gemState1.png"));
		gem = gemIcon.getImage();
		ImageIcon vineIcon = new ImageIcon(cldr.getResource("vine.png"));
		vine = vineIcon.getImage();
		ImageIcon stickIcon = new ImageIcon(cldr.getResource("stick.png"));
		stick = stickIcon.getImage();
		ImageIcon signIcon = new ImageIcon(cldr.getResource("sign.png"));
		sign = signIcon.getImage();

		//counters
		gemLabel = new JLabel("hi");
		panel.add(gemLabel);

		//initialize and start timer
		timer = new Timer(10, this);
		timer.start();

		//initialize boolean
		state = 0;

		//set size
		guiWidth = 900; //old val: 1300
		guiHeight = 700; //old val: 1000
		//1280×720

		addKeyListener(this);

		//make visible
		setSize(guiWidth,guiHeight);
		setVisible(true);

	}

	public void paint(Graphics g)
	{
		Image offImage = createImage(900, 700);
		// Creates an off-screen drawable image to be used for
		// double buffering; XSIZE, YSIZE are each of type ï¿½intï¿½;
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

		if(level.getLevelNum() == 1)
		{
			g.drawImage(uni, 800, 588, 75, 75, null);
			g.drawImage(tree, 650, 500, 100, 165, null);
			g.drawImage(vine, 395, 150, 30, 75, null);
			g.drawImage(sign, 550, 612, 50, 50, null);
			g.drawImage(sign, 800, 100, 50, 50, null);
			g.drawImage(stick, 312, 550, 25, 125, null);
		}

		//platforms
		for(Model m: Level.getGameObjects())
		{
			if(m.exists())
				g.drawImage(((Model)m).getType(), (int)((Model)m).getX(), (int)((Model)m).getY(), (int)((Model)m).getWidth(), (int)((Model)m).getHeight(), null);
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
		for(Model m: Level.getGameObjects())
		{
			if(m instanceof Gem)
			{		
				if(((Gem) m).getVisible() == false)
				{
					gemState = false;
				}
			}
			else if(m instanceof Seed)
			{

			}
			else
			{
				m.run();
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
