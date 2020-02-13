package map;

import common.Constants;
import generator.GenQuizSquare;


public class MapState 
{
  public static int[][] mapConfig;
  public static int playerXPos;
  public static int playerYPos;
  public static QuizSquare activeQuizSquare;
	
  public static void construct()
  {
    initMapConfig();
    activeQuizSquare = new QuizSquare();
  }
  
  public static boolean hasLost()
  {
	int i;
	int j;
	int xCount;
	int yCount;
	boolean hasLost = true;
	int blockSquare;
	int quizSquare;
	
	xCount = Constants.HOR_SQUARE_CNT;
	yCount = Constants.VERT_SQUARE_CNT;
	
	blockSquare = Constants.BLOCK_SQUARE;
	quizSquare = Constants.QUIZ_SQUARE;
	
	for (i = 0; i < xCount; i++)
	{
	  for (j = 0; j < yCount; j++)
	  {
		if (mapConfig[i][j] == blockSquare)
		{
		  if ((isBesideGround(i,
			                 j) == true)
		    || (isBesidePlayer(i,
			                   j) == true))
	      {
            hasLost = false;
            break;
	      }
		}
		
		else
		{
		  if (mapConfig[i][j] == quizSquare)
		  {
		    if (isBesidePlayer(i,
					           j) == true)
			{
		      hasLost = false;
		      break;
			}
	      }
		}
	  }
	}
	
	return hasLost;
  }
  
  public static boolean canMoveDown()
  {
	boolean canMove = true;
	
	if (playerYPos >= (Constants.VERT_SQUARE_CNT-1))
	{
	  canMove = false;
	}
	 
	else 
	{
	  if ((mapConfig[playerXPos][playerYPos+1] != Constants.GROUND_SQUARE)
	    && (mapConfig[playerXPos][playerYPos+1] != Constants.FINISH_SQUARE))
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
	  if ((mapConfig[playerXPos+1][playerYPos] != Constants.GROUND_SQUARE)
	    && (mapConfig[playerXPos+1][playerYPos] != Constants.FINISH_SQUARE))
	  {
		canMove = false;
	  }
	}
	
	return canMove;
  }
  
  public static boolean canMoveUp()
  {
	boolean canMove = true;
	  
	if (playerYPos <= 0)
	{
      canMove = false;
	}
		
	else 
	{
	  if ((mapConfig[playerXPos][playerYPos-1] != Constants.GROUND_SQUARE)
		&& (mapConfig[playerXPos][playerYPos-1] != Constants.FINISH_SQUARE))
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
	  if ((mapConfig[playerXPos-1][playerYPos] != Constants.GROUND_SQUARE)
		&& (mapConfig[playerXPos-1][playerYPos] != Constants.FINISH_SQUARE))
	  {
		canMove = false;
	  }
	}
	
	return canMove;
  }
  
  public static void movePlayerUp()
  {
	int nextSquareType; 
		
	nextSquareType = mapConfig[playerXPos][playerYPos-1];	
	removeQuizSquares();
	  
	mapConfig[playerXPos][playerYPos] = Constants.GROUND_SQUARE;
	mapConfig[playerXPos][playerYPos-1] = Constants.PLAYER_SQUARE;	
	playerYPos = playerYPos - 1;
	
	if (nextSquareType == Constants.FINISH_SQUARE)
	{
	  MapGUI.victory = true;
	}
	
	else
	{
	  syncQuizSquares();
	}
  }
  
  public static void movePlayerRight()
  {
	int nextSquareType; 
	
	nextSquareType = mapConfig[playerXPos+1][playerYPos];	
	removeQuizSquares();
	
	mapConfig[playerXPos][playerYPos] = Constants.GROUND_SQUARE;
	mapConfig[playerXPos+1][playerYPos] = Constants.PLAYER_SQUARE;			
	playerXPos = playerXPos + 1;
	
	if (nextSquareType == Constants.FINISH_SQUARE)
	{
	  MapGUI.victory = true;
	}
	
	else
	{
	  syncQuizSquares();
	}
  }
  
  public static void movePlayerDown()
  { 
	int nextSquareType; 
	
	nextSquareType = mapConfig[playerXPos][playerYPos+1];	  
	removeQuizSquares();
	
	mapConfig[playerXPos][playerYPos] = Constants.GROUND_SQUARE;
	mapConfig[playerXPos][playerYPos+1] = Constants.PLAYER_SQUARE;	
	playerYPos = playerYPos + 1;
	
	if (nextSquareType == Constants.FINISH_SQUARE)
	{
	  MapGUI.victory = true;
	}
	
	else
	{
	  syncQuizSquares();
	}
  }
  
  public static void movePlayerLeft()
  {
	int nextSquareType; 
		
	nextSquareType = mapConfig[playerXPos-1][playerYPos];
	removeQuizSquares();
	  
	mapConfig[playerXPos][playerYPos] = Constants.GROUND_SQUARE;
	mapConfig[playerXPos-1][playerYPos] = Constants.PLAYER_SQUARE;	
	playerXPos = playerXPos - 1;
	
	if (nextSquareType == Constants.FINISH_SQUARE)
	{
	  MapGUI.victory = true;
	}
	
	else
	{
	  syncQuizSquares();
	}
  }
  
  public static boolean isBesideQuestion(int xPos,
		                                 int yPos)
  {
	boolean isBesideQuestion = false;
	  
    if (isBesideSquare(xPos,
    		           yPos,
    		           Constants.QUIZ_SQUARE) == true)
    {
      isBesideQuestion = true;
    }
    
    return isBesideQuestion;
  }
  
  public static boolean isBesidePlayer(int xPos,
                                       int yPos)
  {
    boolean isBesidePlayer = false;
  
    if (isBesideSquare(xPos,
    		           yPos,
    		           Constants.PLAYER_SQUARE) == true)
    {
      isBesidePlayer = true;
    }
    
    return isBesidePlayer;
  }
  
  public static boolean isBesideGround(int xPos,
                                       int yPos)
  {
    boolean isBesideGround = false;

    if (isBesideSquare(xPos,
                       yPos,
                       Constants.GROUND_SQUARE) == true)
    {
      isBesideGround = true;
    }

    return isBesideGround;
  }
  
  public static void setActiveQuiz(GenQuizSquare quizSquare)
  {
	activeQuizSquare.xPos = quizSquare.xPos;
	activeQuizSquare.yPos = quizSquare.yPos;
  }
  
  public static void initQuizSquares(int vertSquareCnt, 
                                     int horSquareCnt)
  {
    int quizSquare = Constants.QUIZ_SQUARE;

    mapConfig[(horSquareCnt/2)-1][vertSquareCnt-1] = quizSquare;
    mapConfig[horSquareCnt/2][vertSquareCnt-2] = quizSquare;
    mapConfig[(horSquareCnt/2)+1][vertSquareCnt-1] = quizSquare;
  }
  
  private static void initMapConfig()
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
	
	//initialize finish line
	initFinishSquare(vertSquareCnt,
	                 horSquareCnt);
  }
  
  private static boolean isBesideSquare(int xPos,
		                                int yPos,
		                                int squareType)
  {
	//if square type not on farthest top block
	if (yPos < (Constants.VERT_SQUARE_CNT-1))
	{
	  //if next block up is quiz block
	  if (mapConfig[xPos][yPos+1] == squareType)
	  {
		return true;
	  }
	}
		
	//if square type not on farthest right block
	if (xPos < (Constants.HOR_SQUARE_CNT-1))
	{
	  //if block to right is quiz block
	  if (mapConfig[xPos+1][yPos] == squareType)
	  {
		return true;
	  }
	}
		
	//if square type not on farthest bottom block
	if (yPos > 0)
	{
	  //if next block up is quiz block
	  if (mapConfig[xPos][yPos-1] == squareType)
	  {
		return true;
	  }
	}
		
	//if square type not on farthest left block
	if (xPos > 0)
	{
	  //if block to left is quiz block
	  if (mapConfig[xPos-1][yPos] == squareType)
	  {
		return true;
	  }
	}
	
	return false;
  }
  
  private static void initBlockSquares(int vertSquareCnt, int horSquareCnt)
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
  
  private static void initPlayerSquare(int vertSquareCnt, 
		                               int horSquareCnt)
  {
    mapConfig[horSquareCnt/2][vertSquareCnt-1] = Constants.PLAYER_SQUARE;
    
    playerXPos = horSquareCnt / 2; 
    playerYPos = vertSquareCnt-1;
  }
  
  private static void initFinishSquare(int vertSquareCnt, 
		                               int horSquareCnt)
  {
	mapConfig[horSquareCnt/2][0] = Constants.FINISH_SQUARE;
  }
  
  private static void removeQuizSquares()
  {
	if (playerYPos < (Constants.VERT_SQUARE_CNT-1))
	{
      if (mapConfig[playerXPos][playerYPos+1] == Constants.QUIZ_SQUARE)
	  {
	    mapConfig[playerXPos][playerYPos+1] = Constants.BLOCK_SQUARE;
	  }
	}
	
	if (playerYPos > 0)
	{
	  if (mapConfig[playerXPos][playerYPos-1] == Constants.QUIZ_SQUARE)
	  {
	    mapConfig[playerXPos][playerYPos-1] = Constants.BLOCK_SQUARE;
	  }
	}
	
	if (playerXPos > 0)
	{
	  if (mapConfig[playerXPos-1][playerYPos] == Constants.QUIZ_SQUARE)
	  {
	    mapConfig[playerXPos-1][playerYPos] = Constants.BLOCK_SQUARE;
	  }
    }
  
	if (playerXPos < (Constants.HOR_SQUARE_CNT-1))
	{
	  if (mapConfig[playerXPos+1][playerYPos] == Constants.QUIZ_SQUARE)
	  {
	    mapConfig[playerXPos+1][playerYPos] = Constants.BLOCK_SQUARE;
	  }
    }
  }
  
  private static void syncQuizSquares()
  {
	if (playerYPos < (Constants.VERT_SQUARE_CNT-1))
	{
      if (mapConfig[playerXPos][playerYPos+1] == Constants.BLOCK_SQUARE)
	  {
	    mapConfig[playerXPos][playerYPos+1] = Constants.QUIZ_SQUARE;
	  }
    }
	    
	if (playerYPos > 0)
	{
	  if (mapConfig[playerXPos][playerYPos-1] == Constants.BLOCK_SQUARE)
	  {
	    mapConfig[playerXPos][playerYPos-1] = Constants.QUIZ_SQUARE;
      }
	}
	 
	if (playerXPos > 0)
	{
	  if (mapConfig[playerXPos-1][playerYPos] == Constants.BLOCK_SQUARE)
	  {
	    mapConfig[playerXPos-1][playerYPos] = Constants.QUIZ_SQUARE;
	  }
	}
	      
	if (playerXPos < (Constants.HOR_SQUARE_CNT-1))
	{
	  if (mapConfig[playerXPos+1][playerYPos] == Constants.BLOCK_SQUARE)
	  {
	    mapConfig[playerXPos+1][playerYPos] = Constants.QUIZ_SQUARE;
	  }
	}
  }
}
