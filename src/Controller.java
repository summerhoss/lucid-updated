
public class Controller
{
	protected static Level level;
	private static View viewer;
	
	public static void main(String[] args)
	{
		MainMenu menu = new MainMenu();
	//	Controller c = new Controller();		
	}
	
	public Controller()
	{
		level = new Level();
		viewer = new View(level);
	}
	
	/*
		if(!level.isComplete())
		{
			for(Model m: level.getGameObjects())
			{
				if(m instanceof Player)
				{
					((Player)m).run(level);
				}
				else
				{
					m.run();
				}
			}
		}
		else
		{
			level.createLevel2();
			this.setLevel(level);
			for(Model m: level.getGameObjects())
			{
				if(m instanceof Player)
				{
					((Player)m).run(level);
				}
				else
				{
					m.run();
				}
			}
		}
		repaint();
	 */
	
}
