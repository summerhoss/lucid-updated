import javax.swing.ImageIcon;

public class Flower extends Platform
{
	
	public Flower(int x, int y, int w, int h, String image)
	{
		super(x, y, w, h, image);
	}
	
	public void grow(int seed, int newX, int newY, int newW, int newH, String i)
	{
		if(seed == 1)
		{
			ClassLoader cldr = getClass().getClassLoader();
			
			ImageIcon icon = new ImageIcon(cldr.getResource(i + ".png"));
			image = icon.getImage();
			
			x = newX;
			y = newY;
			width = newW;
			height = newH;
			System.out.println(getX() + " " + getY());
		}
	}
}
