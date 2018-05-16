import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class Model extends Rectangle {
	Image image;
	/*
	private int myX;
	private int myY;
	private int myW;
	private int myH;
	*/
	
	public Model()
	{
		
	}
	
	public Model(int x, int y, int w, int h, String name)
	{
		super(x, y, w, h);
		/*
		myX = x;
		myY = y;
		myW = w;
		myH = h;
		*/
		
		ClassLoader cldr = getClass().getClassLoader();
		
		ImageIcon icon = new ImageIcon(cldr.getResource(name + ".png"));
		image = icon.getImage();
	}
	
	public Image getType()
	{
		return image;
	}
	
	public void run()
	{
		//Possibly remove this code - has been moved to player
		ArrayList<Model> collisions = checkCollisions(Level.getGameObjects());
		for(Model m : collisions)
		{
			if(this.intersects(m) && (m.getY() - this.getMaxY() <= 1))
			{
				
			}
		}
	}
	
	public ArrayList<Model> checkCollisions(ArrayList<Model> list)
	{
		ArrayList<Model> collided = new ArrayList<Model>();
		for(Model m : list)
		{
			if(this.intersects(m))
			{
				collided.add(m);
			}
		}
		return collided;
	}
	
	public void collidedAction()
	{
		
	}
}
