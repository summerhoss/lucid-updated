import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Player extends Model implements KeyListener {

	private int gemCount;
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean upPressed;
	private boolean isOnGround;
	
	public Player(int x, int y, int w, int h, String image)
	{
		super(x,y,w,h,image);
		gemCount = 0;
		leftPressed = false;
		rightPressed = false;
		upPressed = false;
		isOnGround = true;
	}
	
	public int getGemCount()
	{
		return gemCount;
	}
	
	public void incrementGemCount()
	{
		gemCount++;
	}
	
	public void run()
	{
		ArrayList<Model> collisions = checkCollisions(Level.getGameObjects());
		for(Model m : collisions)
		{
			if(this.intersects(m) && (m.getY() - this.getMaxY() <= 1))
			{
				isOnGround = true;
			}
			else if(this.intersects(m))
			{
				m.collidedAction();
			}
		}
		if(leftPressed)
		{
			this.translate((int)(this.getX()-4), (int)(this.getY()));
		}
		else if(rightPressed)
		{
			this.translate((int)(this.getX()+4), (int)(this.getY()));
		}
		else if(upPressed)
		{
			this.translate((int)(this.getX()), (int)(this.getY()+6));
		}
		else if(!isOnGround)
		{
			if(leftPressed)
			{
				this.translate((int)(this.getX()-2), (int)(this.getY()));
			}
			else if(rightPressed)
			{
				this.translate((int)(this.getX()+2), (int)(this.getY()));
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
