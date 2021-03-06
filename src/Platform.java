import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Platform extends Model
{
	Image platType;
	
	public Platform(int x, int y, int w, int h, String image)
	{
		super(x, y, w, h, image);
		
		ClassLoader cldr = getClass().getClassLoader();
		
		ImageIcon platIcon = new ImageIcon(cldr.getResource(image + ".png"));
		platType = platIcon.getImage();
	}
}
