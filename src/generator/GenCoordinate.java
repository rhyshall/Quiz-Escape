package generator;

import java.util.Arrays;
import java.util.Random;

import common.Constants;

public class GenCoordinate 
{
  public int xPos;	
  public int yPos;
  public static boolean[] blackList;
  public static boolean isBuilt = false;
  
  public GenCoordinate()
  {
    int upperBound = 0;
	int index = 0;
	int startIndex = 0;
	Random randGen = new Random();
	boolean noneLeft = false;
	
	upperBound = Constants.HOR_SQUARE_CNT * Constants.VERT_SQUARE_CNT;
	
	index = randGen.nextInt(upperBound);
	startIndex = index;
	
	while (blackList[index] == true)
	{
	  index++;	  
	  index = index%upperBound;
	  
	  if (index == startIndex)
	  {
		noneLeft = true;
		break;
	  }
	}
	
	if (noneLeft == true)
	{
      xPos = -1;
      yPos = -1;
	}
	
	else
	{
	  blackList[index] = true;
	  
	  xPos = index % Constants.HOR_SQUARE_CNT;
	  yPos = index / Constants.HOR_SQUARE_CNT;
	}
  }
  
  public static void construct()
  {
	initBlackList();
	
	isBuilt = true;
  }
  
  public static boolean isBuilt()
  {
	return isBuilt;
  }
  
  private static void initBlackList()
  {
	int upperBound = 0;
	int i;
		
	upperBound = Constants.HOR_SQUARE_CNT * Constants.VERT_SQUARE_CNT;
	blackList = new boolean[upperBound];
	
	for (i = 0; i < upperBound; i++)
	{
      blackList[i] = false;
	}
  }
}
