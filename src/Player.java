import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Player extends Model implements KeyListener {

	private int count;
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean upPressed;
	private boolean jumping;
	private boolean falling;
	private int fallDist;
	private int remainingDist;
	private int targetY;
	private boolean setTarget;
	private boolean top;
	private boolean bottom;
	private boolean left;
	private boolean right;
	private Image lookRight;
	private Image rStep1;
	private Image rStep2;
	private Image lookLeft;
	private Image lStep1;
	private Image lStep2;
	private int stepCounter;
	
	public Player(int x, int y, int w, int h, String image)
	{
		super(x,y,w,h,image);
		ClassLoader cldr = getClass().getClassLoader();

		ImageIcon rightIcon = new ImageIcon(cldr.getResource("char.png"));
		lookRight = rightIcon.getImage();
		ImageIcon rStep1Icon = new ImageIcon(cldr.getResource("char3.png"));
		rStep1 = rStep1Icon.getImage();
		ImageIcon rStep2Icon = new ImageIcon(cldr.getResource("char2.png"));
		rStep2 = rStep2Icon.getImage();
		ImageIcon leftIcon = new ImageIcon(cldr.getResource("charl.png"));
		lookLeft = leftIcon.getImage();
		ImageIcon lStep1Icon = new ImageIcon(cldr.getResource("charl3.png"));
		lStep1 = lStep1Icon.getImage();
		ImageIcon lStep2Icon = new ImageIcon(cldr.getResource("charl2.png"));
		lStep2 = lStep2Icon.getImage();
		count = 0;
		leftPressed = false;
		rightPressed = false;
		upPressed = false;
		jumping = false;
		falling = true;
		fallDist = 5;
		remainingDist = 0;
		targetY = 0;
		setTarget = true;
		top = false;
		bottom = false;
		left = false;
		right = false;
		stepCounter = 0;
	}

	public String getCount()
	{
		return "Count: " + Integer.toString(count);
	}

	public void incrementCount()
	{
		count++;
	}
	
	public void changeImage()
	{
		if(rightPressed)
		{
			if(stepCounter == 20)
				image = rStep2;
			
			else if(stepCounter == 60)	
				image = rStep1;
			
			else if(stepCounter == 80 || stepCounter == 40)
				image = lookRight;		
		}
		
		else if(leftPressed)
		{
			if(stepCounter == 20)
				image = lStep2;
			
			else if(stepCounter == 60)	
				image = lStep1;
			
			else if(stepCounter == 80 || stepCounter == 40)
				image = lookLeft;		
		}
		
		if(stepCounter >= 80)
			stepCounter = 0;
		
	}

	public void manageCollisions()
	{
		ArrayList<Model> collisions = checkCollisions(Level.getGameObjects());
		//isOnGround = false;
		top = false;
		bottom = false;
		right = false;
		left = false;
		falling = true;
		remainingDist = 0;
		int diff1;
		int diff2;
		int diff3;
		int diff4;
		
		for(Model m : collisions)
		{	
			if(!(m instanceof Player))
			{
				if(m instanceof Platform)
				{
					diff1 = (int)this.getY() - (int)m.getMaxY();
					diff2 = (int)this.getX() - (int)m.getMaxX();
					diff3 = (int)m.getX() - (int)this.getMaxX();
					diff4 = (int)m.getY() - (int)this.getMaxY();
					/*
					System.out.println("bottom = " + diff1);
					System.out.println("right = " + diff2);
					System.out.println("left = " + diff3);
					System.out.println("top = " + diff4);
					*/
					
					if(diff4<0 && diff4>-10)
					{
						top = true;
						remainingDist = (int)(m.getY()-this.getMaxY());
						//System.out.println("top");
					}
					else if(diff1<0 && diff1>-10)
					{
						bottom = true;
						//System.out.println("bottom");
					}
					else if(diff2<0 && diff2>-10)
					{
						right = true;
						//System.out.println("right");
					}
					else if(diff3<0 && diff3>-10)
					{
						left = true;
						//System.out.println("left");
					}
				}
				else if(m instanceof Gem)
				{
					m.collidedAction();
					incrementCount();
					incrementCount();
					incrementCount();
				}
				else if(m instanceof Seed)
				{
					m.collidedAction();
					incrementCount();
				}
				else
				{
					m.collidedAction();
				}
			}
		}
	}
	
	public void jump()
	{
		//Set max jump height
		if(setTarget)
		{
			targetY = (int)(this.getY()-150);
		}
		setTarget = false;
		//Fall once it reaches max height or adjusts position upward
		if(Math.abs(targetY - this.getY()) <= 1)
		{
			falling = true;
			jumping = false;
		}
		else
		{
			this.setLocation((int)(this.getX()), (int)(this.getY()-10));
		}
	}
	
	public void fall()
	{
		//Continuously  moves player downward
		this.setLocation((int)(this.getX()), (int)(this.getY()+fallDist));
	}
	
	public void run()
	{
		this.manageCollisions();
		//System.out.println("top = " + top + ", bottom = " + bottom + ", right = " + right + ", left = " + left);
		changeImage();
		//Set booleans and manage position based on collisions calculated in manageCollisions
		if(top)
		{
			falling = false;
		}
		if(top || bottom)
		{
			jumping = false;
		}
		if(top && !(left || right || bottom))
		{
			this.setLocation((int)(this.getX()), (int)(this.getY()+remainingDist+1));
		}
		
		/*
		 * if(top || left || right || bottom)
		{
			jumping = false;
		}
		 */
		
		//Determine falling and jumping
		if(!top && !jumping)
		{
			falling = true;
		}
		else if(top && upPressed && !jumping && !falling)
		{
			if(!jumping && !falling)
			{
				setTarget = true;
			}
			jumping = true;
		}
		
		//Jumping and falling
		if(falling)
		{
			this.fall();
		}
		if(jumping)
		{
			this.jump();
		}

		//Adjust left and right position in the air and on the ground
		if(leftPressed && (falling || jumping) && !right)
		{
			this.setLocation((int)(this.getX()-2), (int)(this.getY()));
		}
		else if(rightPressed && (falling || jumping) && !left)
		{
			this.setLocation((int)(this.getX()+2), (int)(this.getY()));
		}
		else if(top && leftPressed && !right)
		{
			this.setLocation((int)(this.getX()-4), (int)(this.getY()));
		}
		else if(top && rightPressed && !left)
		{
			this.setLocation((int)(this.getX()+4), (int)(this.getY()));
		}
		
		if(leftPressed || rightPressed)
			stepCounter++;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			leftPressed = true;
			break;
		case KeyEvent.VK_RIGHT:
			rightPressed = true;
			break;
		case KeyEvent.VK_UP:
			upPressed = true;
			break;
		}
	
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			leftPressed = false;
			break;
		case KeyEvent.VK_RIGHT:
			rightPressed = false;
			break;
		case KeyEvent.VK_UP:
			upPressed = false;
			break;
		}
	
	}
	
	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
	
	}
	
	public void setTrue(int dir)
	{
		switch(dir)
		{
		case Moveable.R:
			rightPressed = true;
			break;
		case Moveable.L:
			leftPressed = true;
			break;
		case Moveable.U:
			upPressed = true;
			break;
		}
	}
	
	public void setFalse(int dir)
	{
		switch(dir)
		{
		case Moveable.R:
			rightPressed = false;
			break;
		case Moveable.L:
			leftPressed = false;
			break;
		case Moveable.U:
			upPressed = false;
			break;
		}
	}
}
