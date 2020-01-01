package trivia;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class ExitQuizGUI extends TimerTask
{
  int waitTime;
  QuizGUI quizGUI;
  
  public ExitQuizGUI(int waitTime,
		             QuizGUI quizGUI)
  {
	this.waitTime = waitTime;
	this.quizGUI = quizGUI;
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
  }
}

