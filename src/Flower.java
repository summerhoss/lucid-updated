import javax.swing.ImageIcon;

public class Flower extends Platform
{
	int nX;
	int nY;
	int nW;
	int nH;
	String nImage;
	
	public Flower(int x, int y, int w, int h, String image, int newX, int newY, int newW, int newH, String newI)
	{
		super(x, y, w, h, image);
		nX = newX;
		nY = newY;
		nW = newW;
		nH = newH;
		nImage = newI;
	}
	
	public void grow(int seed)
	{
		if(seed == 1)
		{
			ClassLoader cldr = getClass().getClassLoader();
			
			ImageIcon icon = new ImageIcon(cldr.getResource(nImage + ".png"));
			image = icon.getImage();
			
			x = nX;
			y = nY;
			width = nW;
			height = nH;
		}
	}
}
