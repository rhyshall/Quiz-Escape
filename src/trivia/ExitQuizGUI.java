package trivia;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import common.Constants;
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
    
    mapGUI.enable(true);
    mapGUI.toFront();
    
    mapGUI.quizMode = false;
   
    if (MapState.hasLost() == true)
    {
  	  try 
  	  {
  		TimeUnit.MILLISECONDS.sleep(Constants.ENCORE_LOSS_DELAY);
  	  } 
  	  catch (InterruptedException e) 
  	  {
  		e.printStackTrace();
  	  }
    	
      EncoreGUI encoreGUI = new EncoreGUI(mapGUI, 
    		                              false);
      encoreGUI.run();
    }
  }
}

