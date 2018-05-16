import java.util.*;

public class Level<E>
{
	private static ArrayList<Model> gameObjects;
	
	public Level()
	{
		gameObjects = new ArrayList<Model>();
	}
	
	public static ArrayList<Model> getGameObjects()
	{
		return gameObjects;
	}
	
	public static void addGameObject(Model m)
	{
		gameObjects.add((Model)m);
	}
	
	public void createLevel1()
	{
		//level 1
		//ArrayList<Platform> plats = new ArrayList<Platform>();
		//ArrayList<Cloud> clouds = new ArrayList<Cloud>();
		
		//create platforms for level1
		gameObjects.add(new Platform(300, 800, 100, 50, "platform"));
		gameObjects.add(new Platform(500, 700, 125, 125, "lolli"));
		gameObjects.add(new Platform(250, 600, 150, 150, "lolli2"));
		gameObjects.add(new Platform(150, 500, 50, 50, "platform"));
		gameObjects.add(new Platform(50, 400, 50, 50, "platform"));
		gameObjects.add(new Platform(250, 350, 50, 50, "platform"));
		gameObjects.add(new Platform(0, 250, 150, 50, "platform"));
		gameObjects.add(new Cloud(300, 350, 100, 60, "cloud", 300, 450));
		gameObjects.add(new Platform(550, 350, 100, 50, "platform"));
		gameObjects.add(new Platform(525, 150, 150, 100, "flower"));
		
		//create characters and objects
		
		//ground
		for(int x = 0; x < 1300; x += 100)
			gameObjects.add(new Platform(x, 950, 100, 50, "platform"));
		
		//instantiate level 1
		//level1 = new Level(plats);
	}
	
	public void createLevel2()
	{
		
	}
	
}
