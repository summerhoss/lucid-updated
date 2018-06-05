import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import sun.audio.*;

import javax.swing.*;

public class View extends JFrame implements ActionListener, KeyListener
{
	private Level level;
	private int guiWidth;
	private int guiHeight;
	private Image bg;
	private Image bg2;
	private Image tree;
	private Image vine;
	private Image stick;
	private Image stick2;
	private Image sign;
	private Image seed;
	private Timer timer;
	private JLabel countLabel;
	private Image gem;
	private JPanel panel;
	private JFrame dialogue;
	private JLabel talk;
	private Image blackplat;
	private Image hand;
	private Image wrist;
	private Image crazy_flower;
	private Image badUni;
	
	public View(Level lv)
	{
		panel = new JPanel();
		dialogue = new JFrame("Bob the Unicorn");
		level = lv;

		//close window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		dialogue.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		//to load images
		ClassLoader cldr = getClass().getClassLoader();

		//images
		ImageIcon bgIcon = new ImageIcon(cldr.getResource("bg1.png"));
		bg = bgIcon.getImage();
		ImageIcon bg2Icon = new ImageIcon(cldr.getResource("bg2.png"));
		bg2 = bg2Icon.getImage();
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
		ImageIcon blackplatIcon = new ImageIcon(cldr.getResource("blackplat.png"));
		blackplat = blackplatIcon.getImage();
		ImageIcon handIcon = new ImageIcon(cldr.getResource("hand.png"));
		hand = handIcon.getImage();
		ImageIcon wristIcon = new ImageIcon(cldr.getResource("wrist.png"));
		wrist = wristIcon.getImage();
		ImageIcon crazyFlowerIcon = new ImageIcon(cldr.getResource("crazy_flower.png"));
		crazy_flower = crazyFlowerIcon.getImage();
		ImageIcon badUniIcon = new ImageIcon(cldr.getResource("badUni.png"));
		badUni = badUniIcon.getImage();

		//unicorn dialogue
		talk = new JLabel("Hi! I'm Bob the Unicorn.");
		talk.setFont(new Font("", Font.BOLD, 20));
		dialogue.setPreferredSize(new Dimension(400, 300));
		dialogue.getContentPane().add(talk, BorderLayout.CENTER);
		dialogue.pack();

		//counters
		countLabel = new JLabel("Count: 0");
		panel.add(countLabel);

		//initialize and start ]=
		timer = new Timer(10, this);
		timer.start();

		//set size
		guiWidth = 900; //old val: 1300
		guiHeight = 700; //old val: 1000

		addKeyListener(this);

		//make visible
		setSize(guiWidth,guiHeight);
		setVisible(true);

		//playMusic();
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
		// sometimes helpful to do this first to clear things:
		//g.clearRect(0, 0, 800, 800);

		if(level.getLevelNum() == 1)
		{
			g.drawImage(bg, 0, 0, guiWidth, guiHeight, null);
			g.drawImage(tree, 650, 500, 100, 165, null);
			g.drawImage(sign, 550, 612, 50, 50, null);
			g.drawImage(sign, 800, 100, 50, 50, null);
			g.drawImage(stick, 312, 550, 30, 125, null);
			g.drawImage(stick2, 190, 475, 40, 200, null);

			if(level.getPlayer().hasSeed() == -1)
				g.drawImage(vine, 395, 150, 30, 75, null);
		}
		else if(level.getLevelNum() == 2)
		{
			g.drawImage(bg2, 0, 0, guiWidth, guiHeight, null);
			g.drawImage(wrist, 675, 600, 35, 80, null);
			g.drawImage(gem,325,125,25,25,null);
			g.drawImage(sign,50,150,50,50,null);
		}

		//platforms
		Model m;
		for(int i = 0; i < level.getGameObjects().size(); i++)
		{
			m = level.getGameObjects().get(i);
			//if(m.exists())
			g.drawImage(((Model)m).getType(), (int)((Model)m).getX(), (int)((Model)m).getY(), (int)((Model)m).getWidth(), (int)((Model)m).getHeight(), null);
			if(m instanceof Player)
				countLabel.setText(((Player) m).getCount());
		}
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
		Model m;
		if(!level.isComplete())
		{
			for(int i = 0; i < level.getGameObjects().size(); i++)
			{
				m = level.getGameObjects().get(i);
				if(m instanceof Player)
				{
					((Player)m).run(level);
				}
				else
				{
					m.run();
				}
			}
		}
		else
		{
			level.createLevel2();
			this.setLevel(level);
			level.setComplete(false);
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
		case KeyEvent.VK_SPACE:
		{
			if(level.getPlayer().withUni())
				dialogue.setVisible(true);
			level.getPlayer().setUni(false);
			break;
		}

		}

	}
	@Override
	public void keyTyped(KeyEvent e) 
	{
	}

	private class Sound
	{
		private AudioStream song;
		
		public void playMusic() 
		{
			try
			{
				// get the sound file as a resource out of my jar file;
				// the sound file must be in the same directory as this class file.
				// the input stream portion of this recipe comes from a javaworld.com article.
				InputStream inputStream = getClass().getResourceAsStream("air.wav");
				song = new AudioStream(inputStream);
				//AudioPlayer.player.start(audioStream);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}
		
	}
}
