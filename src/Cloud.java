
public class Cloud extends Platform 
{
	int minX;
	int maxX;
	int direction;
	
	public Cloud(int x, int y, int w, int h, String image, int min, int max)
	{
		super(x, y, w, h, image);
		
		minX = min;
		maxX = max;
		direction = Moveable.R;
	}
	
	public void shift()
	{
		if(direction == Moveable.R && x <= maxX)
			setLocation(x + 1, y);
		else if(direction == Moveable.L && x >= minX)
			setLocation(x - 1, y);
		else if(x >= maxX)
			direction = Moveable.L;
		else 
			direction = Moveable.R;
	}
}
