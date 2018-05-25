import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Player extends Model implements KeyListener {

	private int count;
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean upPressed;
	private boolean isOnGround;
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

	public Player(int x, int y, int w, int h, String image)
	{
		super(x,y,w,h,image);
		count = 0;
		leftPressed = false;
		rightPressed = false;
		upPressed = false;
		isOnGround = false;
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
	}

	public int getCount()
	{
		return count;
	}

	public void incrementCount()
	{
		count++;
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
					remainingDist = (int)(m.getY()-this.getMaxY());
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
						falling = false;
						isOnGround = true;
						jumping = false;
						//System.out.println("top");
					}
					else if(diff1<0 && diff1>-10)
					{
						bottom = true;
						jumping = false;
						//System.out.println("bottom");
					}
					else if(diff2<0 && diff2>-10)
					{
						right = true;
						jumping = false;
						//System.out.println("right");
					}
					else if(diff3<0 && diff3>-10)
					{
						left = true;
						jumping = false;
						//System.out.println("left");
					}
					
						/*
					if(diff2 > diff1)
					{
						if(diff1 > 0)
							bottom = true;
						else 
							top = true;
						//bottom = true;
						System.out.println("bottom = " + bottom);
					}
					else
					{
						if(diff2 > 0)
							left = true;
						else
							right = true;
						System.out.println("right = " + right);
					}
					*/
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
				
				/*
				System.out.println("Player x = " + this.getX());
				if(m instanceof Platform)
					System.out.println("Platform max x = " + m.getMaxX());
					*/
				
				/*
				if(this.getMaxX() - m.getX() <= 1 && m instanceof Platform)
				{
					//(Math.abs(m.getX()-this.getMaxX()) <= 1)
					System.out.println("intersects on side 1");
					isOnGround = false;
					falling = true;
					jumping = false;
				}
				else if(m.getMaxX() - this.getX() <= 1 && m instanceof Platform)
				{
					//(Math.abs(m.getX()-this.getMaxX()) <= 1)
					isOnGround = false;
					falling = true;
					jumping = false;
				}
				else if(Math.abs(m.getY() - fallDist) <= this.getMaxY() && m instanceof Platform)
				{
					System.out.println("hits platform");
					//m.getY()-fallDist <= this.getMaxY()
					//m.getY() - this.getMaxY() <= 1
					remainingDist = (int)(m.getY()-this.getMaxY());
					//System.out.println(m.getY());
					//System.out.println(this.getMaxY());
					isOnGround = true;
					falling = false;
					jumping = false;
				}
				else if(m.getMaxY() - this.getY() <= 1)
				{
					System.out.println("top collision");
					isOnGround = false;
					falling = true;
					jumping = false;
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
				*/
			}
		}
	}
	
	public void jump()
	{
		if(setTarget)
		{
			targetY = (int)(this.getY()-200);
			System.out.println(targetY);
		}
		setTarget = false;
		//System.out.println(targetY);
		//System.out.println(this.getY());
		if(Math.abs(targetY - this.getY()) <= 1)
		{
			System.out.println("target hit");
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
		this.setLocation((int)(this.getX()), (int)(this.getY()+fallDist));
		/*
		if(remainingDist != 0)
		{
			this.setLocation((int)(this.getX()), (int)(this.getY()+remainingDist));
			remainingDist = 0;
		}
		else
		{
			this.setLocation((int)(this.getX()), (int)(this.getY()+fallDist));
		}
		*/
	}
	
	public void run()
	{
		this.manageCollisions();
		//System.out.println(isOnGround + " " + falling + " " + jumping);
		if(top)
			this.setLocation((int)(this.getX()), (int)(this.getY()+remainingDist+1));
		
		if(!top && !jumping)
		{
			falling = true;
		}
		else if(top && upPressed && !falling)
		{
			if(!jumping && !falling)
				setTarget = true;
			jumping = true;
		}

		//System.out.println("top = " + top + ", bottom = " + bottom + ", right = " + right + ", left = " + left);
		
		if(falling)
		{
			this.fall();
		}
		if(jumping)
		{
			this.jump();
		}

		if(leftPressed && (falling || jumping) && !right)
		{
			this.setLocation((int)(this.getX()-2), (int)(this.getY()));
		}
		else if(rightPressed && (falling || jumping) && !left)
		{
			this.setLocation((int)(this.getX()+2), (int)(this.getY()));
		}
		else if(isOnGround && leftPressed && !right)
		{
			this.setLocation((int)(this.getX()-4), (int)(this.getY()));
		}
		else if(isOnGround && rightPressed && !left)
		{
			this.setLocation((int)(this.getX()+4), (int)(this.getY()));
		}

		/*
			if(leftPressed)
			{
				this.setLocation((int)(this.getX()-2), (int)(this.getY()));
			}
			else if(rightPressed)
			{
				this.setLocation((int)(this.getX()+2), (int)(this.getY()));
			}
		 */

	/*
		System.out.println(isOnGround + " " + falling + " " + jumping);
		if(isOnGround && leftPressed)
		{
			this.setLocation((int)(this.getX()-4), (int)(this.getY()));
		}
		else if(isOnGround && rightPressed)
		{
			this.setLocation((int)(this.getX()+4), (int)(this.getY()));
		}
		else if(!isOnGround && !jumping)
		{
			falling = true;
			this.setLocation((int)(this.getX()), (int)(this.getY()+6));
		}
		else if(isOnGround && upPressed && !falling)
		{
			jumping = true;
			int targetY = (int)(this.getY()-27);
			if(Math.abs(targetY - this.getY()) < 1)
			{
				//this.setLocation((int)(this.getX()), (int)(this.getY() + 3));
				falling = true;
			}
			if(leftPressed)
			{
				this.setLocation((int)(this.getX()-2), (int)(this.getY()));
			}
			else if(rightPressed)
			{
				this.setLocation((int)(this.getX()+2), (int)(this.getY()));
			}
			this.setLocation((int)(this.getX()), (int)(this.getY()-3));
		}

		else if(isOnGround && upPressed)
		{
			this.setLocation((int)(this.getX()), (int)(this.getY()-6));
		}
	 */
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
