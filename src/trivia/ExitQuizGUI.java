package trivia;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import common.EncoreGUI;
import map.MapGUI;
import map.MapState;

public class ExitQuizGUI extends TimerTask
{
  int waitTime;
  QuizGUI quizGUI;
  MapGUI mapGUI;
  
  public ExitQuizGUI(int waitTime,
		             QuizGUI quizGUI,
		             MapGUI mapGUI)
  {
	this.waitTime = waitTime;
	this.quizGUI = quizGUI;
	this.mapGUI = mapGUI;
  }
	
  @Override
  public void run()
  {
    try 
	{
	  TimeUnit.MILLISECONDS.sleep(waitTime);
    } 
	catch (InterruptedException e) 
    {
	  e.printStackTrace();
	}  
    
    quizGUI.dispose();
    mapGUI.repaint();
   
    if (MapState.hasLost() == true)
    {
      EncoreGUI encoreGUI = new EncoreGUI(mapGUI, 
    		                              false);
      encoreGUI.run();
    }
  }
}

