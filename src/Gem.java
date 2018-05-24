
public class Gem extends Model{
	private boolean visible;
	
	public Gem()
	{
		//initialize instance variables
		visible = true;
	}
	
	public Gem(int x, int y, int w, int h, String name)
	{
		super(x,y,w,h,name);
	}

	public void collidedAction()
	{
		toggleExist();
	}
}
