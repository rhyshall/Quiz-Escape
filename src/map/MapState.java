package map;

import common.Constants;
import java.util.ArrayList;

public class MapState 
{
  public static int[][] mapConfig;
  public static int playerXPos;
  public static int playerYPos;
	
  public MapState()
  {
    initMapConfig();
  }
  
  public static boolean canMoveUp()
  {
	boolean canMove = true;
	
	if (playerYPos >= (Constants.VERT_SQUARE_CNT-1))
	{
	  canMove = false;
	}
	 
	else 
	{
	  if (mapConfig[playerXPos][playerYPos+1] != Constants.GROUND_SQUARE)
	  {
		canMove = false;
	  }
	}
	
	return canMove;
  }
  
  public static boolean canMoveRight()
  {
	boolean canMove = true;
	
	if (playerXPos >= (Constants.HOR_SQUARE_CNT-1))
	{
	  canMove = false;
	}
	
	else 
	{
	  if (mapConfig[playerXPos+1][playerYPos] != Constants.GROUND_SQUARE)
	  {
		canMove = false;
	  }
	}
	
	return canMove;
  }
  
  public static boolean canMoveDown()
  {
	boolean canMove = true;
	  
	if (playerYPos <= 0)
	{
      canMove = false;
	}
		
	else 
	{
	  if (mapConfig[playerXPos][playerYPos-1] != Constants.GROUND_SQUARE)
	  {
	    canMove = false;
	  }
	} 
	
	return canMove;
  }

  public static boolean canMoveLeft()
  {
	boolean canMove = true;
	
	if (playerXPos <= 0)
	{
	  canMove = false;
	}
	
	else 
	{
	  if (mapConfig[playerXPos-1][playerYPos] != Constants.GROUND_SQUARE)
	  {
		canMove = false;
	  }
	}
	
	return canMove;
  }
  
  public static void movePlayerUp()
  {
	mapConfig[playerXPos][playerYPos] = Constants.GROUND_SQUARE;
	mapConfig[playerXPos][playerYPos+1] = Constants.PLAYER_SQUARE;
	
	playerYPos = playerYPos + 1;
  }
  
  public static void movePlayerRight()
  {
	mapConfig[playerXPos][playerYPos] = Constants.GROUND_SQUARE;
	mapConfig[playerXPos+1][playerYPos] = Constants.PLAYER_SQUARE;
	
	playerXPos = playerXPos + 1;
  }
  
  public static void movePlayerDown()
  {
	mapConfig[playerXPos][playerYPos] = Constants.GROUND_SQUARE;
	mapConfig[playerXPos][playerYPos-1] = Constants.PLAYER_SQUARE;
	
	playerYPos = playerYPos - 1;
  }
  
  public static void movePlayerLeft()
  {
	mapConfig[playerXPos][playerYPos] = Constants.GROUND_SQUARE;
	mapConfig[playerXPos-1][playerYPos] = Constants.PLAYER_SQUARE;
	
	playerXPos = playerXPos - 1;
  }
  
  public static boolean isBesideQueston()
  {
	//if player not on farthest top block
	if (playerYPos < (Constants.VERT_SQUARE_CNT-1))
	{
	  //if next block up is quiz block
	  if (mapConfig[playerXPos][playerYPos+1] == Constants.QUIZ_SQUARE)
	  {
		return true;
	  }
	}
	
	//if player not on farthest right block
	if (playerXPos < (Constants.HOR_SQUARE_CNT-1))
	{
      //if block to right is quiz block
	  if (mapConfig[playerXPos+1][playerYPos] == Constants.QUIZ_SQUARE)
	  {
		return true;
	  }
	}
	
	//if player not on farthest bottom block
	if (playerYPos > 0)
	{
	  //if next block up is quiz block
	  if (mapConfig[playerXPos][playerYPos-1] == Constants.QUIZ_SQUARE)
	  {
		return true;
	  }
	}
	
	//if player not on farthest left block
	if (playerXPos > 0)
	{
	  //if block to left is quiz block
	  if (mapConfig[playerXPos-1][playerYPos] == Constants.QUIZ_SQUARE)
	  {
		return true;
	  }
	}
	
	return false;
  }
  
  private void initMapConfig()
  {
	int vertSquareCnt = Constants.VERT_SQUARE_CNT;
	int horSquareCnt = Constants.HOR_SQUARE_CNT;
	
	//initialize memory for map state data
	mapConfig = new int[horSquareCnt][vertSquareCnt];
	
	//initialize whole map state with block obstacles
	initBlockSquares(vertSquareCnt,
			         horSquareCnt);
	
	//initialize player's original starting position
	initPlayerSquare(vertSquareCnt,
	                 horSquareCnt);
	
	//initialize first question squares
	initQuizSquares(vertSquareCnt,
	                horSquareCnt);
	
	//initialize finish line
	initFinishSquare(vertSquareCnt,
	                 horSquareCnt);
  }
  
  private void initBlockSquares(int vertSquareCnt, int horSquareCnt)
  {
	int blockSquare = Constants.BLOCK_SQUARE;
	int i = 0;
	int j = 0;
	
	for (i = 0; i < horSquareCnt; i++)
	{	  
	  for (j = 0; j < vertSquareCnt; j++)
	  {
		mapConfig[i][j] = blockSquare;
	  }
	}
  }
  
  private void initPlayerSquare(int vertSquareCnt, int horSquareCnt)
  {
    mapConfig[horSquareCnt/2][vertSquareCnt-1] = Constants.PLAYER_SQUARE;
    
    playerXPos = horSquareCnt / 2;
    playerYPos = vertSquareCnt-1;
  }
  
  private void initQuizSquares(int vertSquareCnt, int horSquareCnt)
  {
	int quizSquare = Constants.QUIZ_SQUARE;
	
	mapConfig[(horSquareCnt/2)-1][vertSquareCnt-1] = quizSquare;
	mapConfig[horSquareCnt/2][vertSquareCnt-2] = quizSquare;
	mapConfig[(horSquareCnt/2)+1][vertSquareCnt-1] = quizSquare;
  }
  
  private void initFinishSquare(int vertSquareCnt, int horSquareCnt)
  {
	mapConfig[horSquareCnt/2][0] = Constants.FINISH_SQUARE;
  }
}
