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

	public Player(int x, int y, int w, int h, String image)
	{
		super(x,y,w,h,image);
		count = 0;
		leftPressed = false;
		rightPressed = false;
		upPressed = false;
		isOnGround = false;
		jumping = false;
		//change value of falling here?
		falling = true;
	}

	public int getCount()
	{
		return count;
	}

	public void incrementCount()
	{
		count++;
	}

	public void run()
	{
		ArrayList<Model> collisions = checkCollisions(Level.getGameObjects());
		isOnGround = false;
		falling = true;
		jumping = false;
		for(Model m : collisions)
		{
			if(!(m instanceof Player))
			{
				if(this.intersects(m) && (m.getY() - this.getMaxY() <= 1) && m instanceof Platform)
				{
					isOnGround = true;
					falling = false;
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
			}
		}
		
		System.out.println(isOnGround + " " + falling + " " + jumping);
		if(!isOnGround && !jumping)
		{
			falling = true;
			this.setLocation((int)(this.getX()), (int)(this.getY()+6));
		}
		
		if(isOnGround && leftPressed)
		{
			this.setLocation((int)(this.getX()-4), (int)(this.getY()));
		}
		else if(isOnGround && rightPressed)
		{
			this.setLocation((int)(this.getX()+4), (int)(this.getY()));
		}
		else if(isOnGround && upPressed && !falling)
		{
			jumping = true;
			int targetY = (int)(this.getY()-200);
			this.setLocation((int)(this.getX()), (int)(this.getY()-200));

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
		}
		
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
<<<<<<< HEAD
			
			int targetY = (int)(this.getY()-6);
=======
			jumping = true;
			int targetY = (int)(this.getY()-27);
>>>>>>> c152a076a1afe786ce3bdae09a3435bbc3ffc90b
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
			System.out.println("right");
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
