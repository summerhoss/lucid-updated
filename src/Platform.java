import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Platform extends Rectangle
{
	Image platType;
	
	public Platform(int x, int y, int w, int h, String image)
	{
		super(x, y, w, h);
		
		ClassLoader cldr = getClass().getClassLoader();
		
		ImageIcon platIcon = new ImageIcon(cldr.getResource(image + ".png"));
		platType = platIcon.getImage();
	}
	
	public Image getType()
	{
		return platType;
	}
}
