package map;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import common.Constants;
import generator.GenQuizSquare;
import trivia.QuizGUI;

class FlashQuizSquare extends TimerTask 
{
  MapGUI mapGUI;
  QuizGUI quizGUI;
  public static QuizSquares quizSquares;
  public static GenQuizSquare targetSquare;
  int i = 0;
	
  public FlashQuizSquare(MapGUI mapGUI,
		                 QuizGUI quizGUI)
  {
	this.mapGUI = mapGUI;
	this.quizGUI = quizGUI;
  }
	
  @Override
  public void run() 
  {
	int quizSquareCnt = quizSquares.entries.size();
    int flashCnt = quizSquareCnt * 4;
    int nextFlashIndex;
    int prevFlashIndex;
    QuizSquare nextFlashSquare;
    QuizSquare prevFlashSquare;
	  
	if (i <= flashCnt)
	{  		
	  if (i > 0)
	  {
		prevFlashIndex = (i-1) % quizSquareCnt;
		prevFlashSquare = quizSquares.entries.get(prevFlashIndex);
		
		MapState.mapConfig[prevFlashSquare.xPos][prevFlashSquare.yPos] = Constants.BLOCK_SQUARE;
	  }
	  
	  if (i == flashCnt)
	  {	  
		MapState.mapConfig[targetSquare.xPos][targetSquare.yPos] = Constants.QUIZ_SQUARE;
	  }
	  
	  else
	  {
	    nextFlashIndex = i % quizSquareCnt;
	    nextFlashSquare = quizSquares.entries.get(nextFlashIndex);
	    
	    MapState.mapConfig[nextFlashSquare.xPos][nextFlashSquare.yPos] = Constants.QUIZ_SQUARE;
	  }
	  
	  mapGUI.repaint();
	  i++;
	}
	
	else
	{
	  i = 0;	  
	  cancel();
	  
	  try 
	  {
		TimeUnit.MILLISECONDS.sleep(1200);
	  } 
	  catch (InterruptedException e) 
	  {
		e.printStackTrace();
	  }
	  
	  mapGUI.setVisible(false);
	  
	  quizGUI = new QuizGUI();
	  quizGUI.run();
	  
	  return;
	}
  }
}
