
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
	
		public void setVisible(Boolean b)
	{
		visible = b;
	}
}
