
public class Seed extends Model
{
	private boolean visible;
	public Seed()
	{
		visible = true;	
	}
	
	public void collidedAction()
	{
		visible = false;
	}
}
