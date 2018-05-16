import java.util.*;

public class Controller<E>
{
	protected static Level level1;
	protected ArrayList<Model> gameObjects;
	
	public static void main(String[] args)
	{
		level1.createLevel1();
		View viewer = new View(level1);
	}
	
	public Controller()
	{
		level1 = new Level();
		gameObjects = new ArrayList<Model>();
	}
	
	public ArrayList<Model> getGameObjects()
	{
		return gameObjects;
	}
	
	public void addGameObject(E m)
	{
		if(m instanceof Model)
		{
			gameObjects.add((Model)m);
		}
	}
}
