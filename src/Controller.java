import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.Timer;

public class Controller implements ActionListener
{
	protected static Level level1;
	protected static Level level2;
	private static Timer timer;
	private static View viewer;
	
	public static void main(String[] args)
	{
		Controller c = new Controller();
		level1.createLevel1();
		timer.start();
		/*
		while(!level2.isComplete())
		{
			for(Model m: level2.getGameObjects())
			{
				if(m instanceof Player)
				{
					((Player)m).run(level2);
				}
				else
				{
					m.run();
				}
			}
		}
		*/
		
	}
	
	public Controller()
	{
		level1 = new Level();
		level2 = new Level();
		timer = new Timer(10, this);
		viewer = new View(level1);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(!level1.isComplete())
		{
			for(Model m: level1.getGameObjects())
			{
				if(m instanceof Player)
				{
					((Player)m).run(level1);
				}
				else
				{
					m.run();
				}
			}
		}
		else
		{
			level2.createLevel2();
			viewer.setLevel(level2);
		}
	}
	
}
