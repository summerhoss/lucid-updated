
public class Gem extends Model{
	private boolean state1;
	private boolean state2;
	private boolean visible;
	
	public Gem()
	{
		visible = true;
		state1 = true;
		state2 = false;
	}
	
	public Gem(int x, int y, int w, int h, String name)
	{
		super(x,y,w,h,name);
	}

	public void collidedAction()
	{
		visible = false;
	}
}
