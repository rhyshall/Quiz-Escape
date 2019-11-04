package generator;

import map.MapState;
import map.QuizSquare;
import java.util.ArrayList;
import java.util.Random;

import common.Constants;

public class GenQuizSquare 
{
  ArrayList<QuizSquare> quizSquareList;
  public int genXPos;	
  public int genYPos;
	
  public GenQuizSquare()
  {
	buildSquareList();
	generateXY();
  }
  
  private void buildSquareList()
  {
	int[][] mapConfig = MapState.mapConfig;
	int playerXPos = MapState.playerXPos;
	int playerYPos = MapState.playerYPos;	
	quizSquareList = new ArrayList<QuizSquare>(4);
	
	//if player not on farthest top block
	if (playerYPos < (Constants.VERT_SQUARE_CNT-1))
	{
	  //if next block up is quiz block
	  if (mapConfig[playerXPos][playerYPos+1] == Constants.QUIZ_SQUARE)
	  {
		saveSquareToList(playerXPos,
				         playerYPos+1);
	  }
	}
		
	//if player not on farthest right block
	if (playerXPos < (Constants.HOR_SQUARE_CNT-1))
	{
      //if block to right is quiz block
	  if (mapConfig[playerXPos+1][playerYPos] == Constants.QUIZ_SQUARE)
	  {
		saveSquareToList(playerXPos+1,
			             playerYPos);
	  }
	}
		
	//if player not on farthest bottom block
	if (playerYPos > 0)
	{
	  //if next block up is quiz block
	  if (mapConfig[playerXPos][playerYPos-1] == Constants.QUIZ_SQUARE)
	  {
		saveSquareToList(playerXPos, 
				         playerYPos-1);
	  }
	}
		
	//if player not on farthest left block
	if (playerXPos > 0)
	{
	  //if block to left is quiz block
	  if (mapConfig[playerXPos-1][playerYPos] == Constants.QUIZ_SQUARE)
	  {
		saveSquareToList(playerXPos-1, 
				         playerYPos);
	  }
	}
  }
  
  private void generateXY()
  {
	int squareCnt = 0;
	Random randGen = new Random();
	QuizSquare quizSquare;
	int randNum = 0;
	
	squareCnt = quizSquareList.size();	
	randNum = randGen.nextInt(squareCnt);
	
	quizSquare = quizSquareList.get(randNum);
	genXPos = quizSquare.xPos;
	genYPos = quizSquare.yPos;
  }
  
  private void saveSquareToList(int xPos, int yPos)
  {
	QuizSquare q = new QuizSquare();
	q.xPos = xPos;
	q.yPos = yPos;
	
	quizSquareList.add(q);
  }
}
