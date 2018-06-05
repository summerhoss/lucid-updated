import javax.swing.JLabel;
import javax.swing.JPanel;

public class Unicorn extends Model
{
	private boolean collidedPlayer;
	
	public Unicorn(int x, int y, int w, int h, String name)
	{
		super(x, y, w, h, name);
		collidedPlayer = false;
	}
	
	public void collidedAction() 
	{

	}
}
