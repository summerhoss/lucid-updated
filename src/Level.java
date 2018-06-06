import java.util.*;

public class Level
{
	private static ArrayList<Model> gameObjects;
	private Player lucy;
	private int levelNum;
	private boolean complete;
	private boolean flowerGrown;
	private boolean cutPlayed;

	public Level()
	{
		gameObjects = new ArrayList<Model>();
		lucy = null;
		levelNum = 0;
		complete = false;
		flowerGrown = false;
		cutPlayed = false;
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
		levelNum = 1;

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
		gameObjects.add(new Flower(400, 210, 40, 15, "dirt", 375, 125, 75, 50, "flower"));
		gameObjects.add(new Unicorn(800, 588, 75, 75, "Unicorn"));
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
	}

	public void createLevel2()
	{
		levelNum = 2;
		//clear ArrayList
		gameObjects = new ArrayList<Model>();
		
		//create characters and objects
		lucy = new Player(825, 588, 37, 75, "char");
		gameObjects.add(lucy);
		gameObjects.add(new Gem(350, 375, 25, 25, "gemState1"));
		gameObjects.add(new Unicorn(650,140,75,75,"badUni"));
		//gameObjects.add(new Seed(150, 335, 15, 15, "seed"));
		gameObjects.add(new Unicorn(800, 588, 75, 75, "Unicorn"));
		gameObjects.add(new Seed(675, 200, 15, 15, "seed"));
		
		//create platforms
		gameObjects.add(new Platform(515,475,75,37,"blackplat"));
		gameObjects.add(new Platform(650,500,75,75,"hand"));
		gameObjects.add(new Platform(575,315,75,37, "blackplat"));
		gameObjects.add(new Platform(650,215,75,37,"blackplat"));
		gameObjects.add(new Cloud(375,245,75,50,"cloud",400,500));
		gameObjects.add(new Platform(300,150,75,37,"blackplat"));
		//gameObjects.add(new Portal(300,100,75,50,"none",825,588,0));
		gameObjects.add(new Platform(325,400,75,37,"blackplat"));
		gameObjects.add(new Platform(0,200,150,37,"blackplat")); //prev value: 100
		gameObjects.add(new Platform(225,500,75,37,"blackplat"));
		gameObjects.add(new Platform(175,350,37,37,"blackplat"));
		//gameObjects.add(new Portal(100,200,75,100,"none",50,225,0));
		gameObjects.add(new Portal(0,0,100,200,"gemState1",0,0,3));
		gameObjects.add(new Flower(175, 335, 37, 15, "dirt", 150, 250, 75, 50, "crazy_flower"));
		gameObjects.add(new Portal(100,200,75,100,"none",50,237,0));
		
		//create ground
		for(int x = 400; x < 900; x += 50)
			gameObjects.add(new Platform(x, 663, 75, 37, "blackplat"));
	}
	
	public void createLevel3()
	{
		levelNum = 3;
		gameObjects = new ArrayList<Model>();
		
		//create characters and objects
		lucy = new Player(450, 0, 37, 75, "char");
		gameObjects.add(lucy);
	}
	
	public int getLevelNum()
	{
		return levelNum;
	}
	
	public void setLevelNum(int num)
	{
		levelNum = num;
	}

	public Player getPlayer()
	{
		return lucy;
	}
	
	public void cutscene()
	{
		
	}
	
	public boolean getCutPlayed()
	{
		return cutPlayed;
	}
	
	public void cutPlayed()
	{
		cutPlayed = true;
	}

}
