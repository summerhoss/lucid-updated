import java.util.*;

public class Controller
{
	protected static Level level1;
	
	public static void main(String[] args)
	{
		Controller c = new Controller();
		level1.createLevel1();
		View viewer = new View(level1);
	}
	
	public Controller()
	{
		level1 = new Level();
	}
	
}
