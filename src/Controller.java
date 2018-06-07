
public class Controller
{
	protected static Level level;
	private static View viewer;
	
	public static void main(String[] args)
	{
		new MainMenu();
		//MainMenu menu = new MainMenu();
	//	Controller c = new Controller();		
	}
	
	public Controller()
	{
		level = new Level();
		viewer = new View(level);
	}
	
	
	
}
