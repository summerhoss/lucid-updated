

public class Portal extends Model {
	private int newX;
	private int newY;
	private int level;
	
	public Portal()
	{
		newX = 0;
		newY = 0;
		level = 0;
	}
	
	public Portal(int x, int y, int w, int h, String name, int moveX, int moveY, int levelNum)
	{
		super(x,y,w,h,name);
		newX = moveX;
		newY = moveY;
		level = levelNum;
	}
}
