import java.util.*;

public class Controller
{
	protected static Level level1;
	protected static Level level2;
	
	public static void main(String[] args)
	{
		Controller c = new Controller();
		level1.createLevel1();
		View viewer = new View(level1);
		while(!level1.isComplete())
		{
			level1.complete();
		}
		
		level2.createLevel2();
		viewer.setLevel(level2);
		
	}
	
	public Controller()
	{
		level1 = new Level();
		level2 = new Level();
	}
	
}
