import java.util.*;

public class Level
{
	private static ArrayList<Model> gameObjects;
	private Player lucy;
	private int levelNum;
	private boolean complete;

	public Level()
	{
		gameObjects = new ArrayList<Model>();
		lucy = null;
		levelNum = 0;
		complete = false;
	}
	
	public boolean isComplete()
	{
		return complete;
	}
	
	public void setComplete(boolean value)
	{
		complete = value;
	}

	public static ArrayList<Model> getGameObjects()
	{
		return gameObjects;
	}

	public static void addGameObject(Model m)
	{
		gameObjects.add((Model)m);
	}
	
	public static void removeGameObject(Model m)
	{
		gameObjects.remove((Model)m);
	}

	public void createLevel1()
	{
		//level 1
		//ArrayList<Platform> plats = new ArrayList<Platform>();
		//ArrayList<Cloud> clouds = new ArrayList<Cloud>();

		//ground
		for(int x = 0; x < 900; x += 50)
			gameObjects.add(new Platform(x, 663, 75, 37, "platform"));

		//create platforms for level1
		gameObjects.add(new Platform(175, 575, 75, 37, "platform"));
		gameObjects.add(new Platform(300, 500, 75, 75, "lolli"));
		gameObjects.add(new Platform(150, 400, 100, 100, "lolli2"));
		gameObjects.add(new Platform(100, 325, 37, 37, "platform"));
		gameObjects.add(new Platform(37, 250, 37, 37, "platform"));
		gameObjects.add(new Platform(175, 200, 37, 37, "platform"));
		gameObjects.add(new Platform(0, 150, 75, 37, "platform"));
		gameObjects.add(new Seed(50, 135, 15, 15, "seed"));
		gameObjects.add(new Cloud(212, 200, 75, 50, "cloud", 212, 300));
		gameObjects.add(new Platform(375, 225, 75, 37, "platform"));
		gameObjects.add(new Platform(375, 125, 75, 50, "flower"));
		gameObjects.add(new Platform(663, 525, 75, 37, "treeplat"));
		gameObjects.add(new Cloud(650, 375, 75, 50, "cloud", 550, 675));
		gameObjects.add(new Platform(700, 225, 37, 37, "platform"));
		gameObjects.add(new Platform(750, 150, 75, 37, "platform"));
		gameObjects.add(new Platform(825, 150, 75, 37, "platform"));

		//create characters and objects
		lucy = new Player(100, 575, 37, 75, "char");
		gameObjects.add(lucy);
		gameObjects.add(new Gem(400, 100, 25, 25, "gemState1"));
		gameObjects.add(new Portal(870,115,25,25,"none",0,0,2));
		
		levelNum = 1;
	}

	public void createLevel2()
	{
		levelNum = 2;
		//clear ArrayList
		gameObjects = new ArrayList<Model>();
		
		//create characters and objects
		lucy = new Player(825, 75, 37, 75, "char");
		gameObjects.add(lucy);
		
		//create platforms
		
		
		//create ground
		for(int x = 400; x < 900; x += 50)
			gameObjects.add(new Platform(x, 663, 75, 37, "blackplat"));
	}
	
	public int getLevelNum()
	{
		return levelNum;
	}

	public Player getPlayer()
	{
		return lucy;
	}

}
