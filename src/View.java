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
	private Image bg2;
	private Image uni;
	private Image tree;
	private Image vine;
	private Image stick;
	private Image stick2;
	private Image sign;
	private Image seed;

	private Timer timer;
	private int state;
	private JLabel countLabel;
	private Image gem;
	private JPanel panel;

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
		ImageIcon bg2Icon = new ImageIcon(cldr.getResource("bg2.png"));
		bg2 = bg2Icon.getImage();
		ImageIcon char2Icon = new ImageIcon(cldr.getResource("char2.png"));
		ImageIcon uniIcon = new ImageIcon(cldr.getResource("unicorn.png"));
		uni = uniIcon.getImage();
		ImageIcon treeIcon = new ImageIcon(cldr.getResource("tree.png"));
		tree = treeIcon.getImage();
		ImageIcon gemIcon = new ImageIcon(cldr.getResource("gemState1.png"));
		gem = gemIcon.getImage();
		ImageIcon vineIcon = new ImageIcon(cldr.getResource("vine.png"));
		vine = vineIcon.getImage();
		ImageIcon stickIcon = new ImageIcon(cldr.getResource("stick.png"));
		stick = stickIcon.getImage();
		ImageIcon stick2Icon = new ImageIcon(cldr.getResource("stick2.png"));
		stick2 = stick2Icon.getImage();
		ImageIcon signIcon = new ImageIcon(cldr.getResource("sign.png"));
		sign = signIcon.getImage();
		ImageIcon seedIcon = new ImageIcon(cldr.getResource("seed.png"));
		seed = seedIcon.getImage();

		//counters
		countLabel = new JLabel("Count: 0");
		panel.add(countLabel);

		//initialize and start timer
		timer = new Timer(10, this);
		timer.start();

		//initialize boolean
		state = 0;

		//set size
		guiWidth = 900; //old val: 1300
		guiHeight = 700; //old val: 1000
		//1280�720

		addKeyListener(this);

		//make visible
		setSize(guiWidth,guiHeight);
		setVisible(true);

	}

	public void setLevel(Level l)
	{
		level = l;
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
		Model temp = null;
		// sometimes helpful to do this first to clear things:
		//g.clearRect(0, 0, 800, 800);
	
		if(level.getLevelNum() == 1)
		{
			g.drawImage(bg, 0, 0, guiWidth, guiHeight, null);
			g.drawImage(uni, 800, 588, 75, 75, null);
			g.drawImage(tree, 650, 500, 100, 165, null);
			g.drawImage(vine, 395, 150, 30, 75, null);
			g.drawImage(sign, 550, 612, 50, 50, null);
			g.drawImage(sign, 800, 100, 50, 50, null);
			g.drawImage(stick, 312, 550, 30, 125, null);
			g.drawImage(stick2, 190, 475, 40, 200, null);
		}
		else if(level.getLevelNum() == 2)
		{
			g.drawImage(bg2, 0, 0, guiWidth, guiHeight, null);
		}

		//platforms
		for(Model m: level.getGameObjects())
		{
			if(m.exists())
				g.drawImage(((Model)m).getType(), (int)((Model)m).getX(), (int)((Model)m).getY(), (int)((Model)m).getWidth(), (int)((Model)m).getHeight(), null);
			if(m.exists() == false)
				temp = m;
			if(m instanceof Player)
				countLabel = new JLabel(((Player) m).getCount());
		}
		level.getGameObjects().remove(temp);
		g.drawString(countLabel.getText(), 25, 75);
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
		/*
		for(Model m: level.getGameObjects())
		{
			if(m instanceof Player)
			{
				((Player)m).run(level);
			}
			else
			{
				m.run();
			}
		}
		*/
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
