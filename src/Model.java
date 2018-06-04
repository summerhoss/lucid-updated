import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public abstract class Model extends Rectangle {
	Image image;
	Boolean exists;
	
	public Model()
	{
		
	}
	
	public Model(int x, int y, int w, int h, String name)
	{
		super(x, y, w, h);
		
		if(!name.equals("none"))
		{
		ClassLoader cldr = getClass().getClassLoader();
		
		ImageIcon icon = new ImageIcon(cldr.getResource(name + ".png"));
		image = icon.getImage();
		}
		
		exists = true;
	}
	
	public Image getType()
	{
		return image;
	}
	
	public void run()
	{
		
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
	
	public boolean exists()
	{
		return exists;
	}
	
	public void toggleExist()
	{
		exists = !exists;
	}
}
