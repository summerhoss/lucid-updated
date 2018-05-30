
public class Seed extends Model
{
	private boolean visible;
	public Seed()
	{
		visible = true;	
	}
	
	public Seed(int x, int y, int w, int h, String name)
	{
		super(x,y,w,h,name);
	}
	
	public boolean getVisible()
	{
		//returns whether or not the gem is visible
		return visible;
	}

	public void collidedAction()
	{
		toggleExist();
	}
}
