package map;

import java.util.ArrayList;

import common.Constants;

public class QuizSquares
{
  public static ArrayList<QuizSquare> entries;
  
  public QuizSquares()
  {
	buildSquareList();
  }
  
  private void buildSquareList()
  {
	int[][] mapConfig = MapState.mapConfig;
	int playerXPos = MapState.playerXPos;
	int playerYPos = MapState.playerYPos;	
	entries = new ArrayList<QuizSquare>(4);
	
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
  
  private void saveSquareToList(int xPos, int yPos)
  {
	QuizSquare q = new QuizSquare();
	q.xPos = xPos;
	q.yPos = yPos;
	
	entries.add(q);
  }
}
