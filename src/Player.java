import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Player extends Model implements KeyListener {

	private int count;
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean upPressed;
	private boolean isOnGround;

	public Player(int x, int y, int w, int h, String image)
	{
		super(x,y,w,h,image);
		count = 0;
		leftPressed = false;
		rightPressed = false;
		upPressed = false;
		isOnGround = false;
		System.out.println("is on ground?" + isOnGround);
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
		for(Model m : collisions)
		{
			if(!(m instanceof Player))
			{
				if(this.intersects(m) && (m.getY() - this.getMaxY() <= 1))
				{
					isOnGround = true;
				}
				else if(this.intersects(m) && m instanceof Gem)
				{
					m.collidedAction();
					incrementCount();
					incrementCount();
					incrementCount();
				}
				else if(this.intersects(m) && m instanceof Seed)
				{
					m.collidedAction();
					incrementCount();
				}
				else if(this.intersects(m))
				{
					m.collidedAction();
				}
			}
		}
		
		if(isOnGround && leftPressed)
		{
			System.out.println("left is pressed");
			this.setLocation((int)(this.getX()-4), (int)(this.getY()));
		}
		else if(isOnGround && rightPressed)
		{
			this.setLocation((int)(this.getX()+4), (int)(this.getY()));
		}
		else if(isOnGround && upPressed)
		{
			this.setLocation((int)(this.getX()), (int)(this.getY()-6));
		}
		
		if(!isOnGround)
		{
			System.out.println("falling");
			int targetY = (int)(this.getY()-6);
			if(Math.abs(targetY - this.getY()) < 1)
			{
				this.setLocation((int)(this.getX()), (int)(this.getY() + 3));
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
