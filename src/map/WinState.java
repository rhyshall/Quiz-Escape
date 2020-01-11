package map;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.StringTokenizer;

import common.Constants;

public class WinState 
{
  public static int[][] winConfig;
  
  public WinState()
  {
	int vertSquareCnt = Constants.VERT_SQUARE_CNT;
	int horSquareCnt = Constants.HOR_SQUARE_CNT;
	  
	winConfig = new int[horSquareCnt][vertSquareCnt];
	
	initWinConfig();
  }
  
  public void buildWinMsg()
  {
    String workDir = "";
	String filePath = "";
		  
	workDir = Paths.get(".").toAbsolutePath().normalize().toString();
	filePath = workDir + Constants.WIN_FILE_PATH; 
		  	  
	try
	{
	  FileInputStream fileStream = new FileInputStream(new File(filePath));
	  DataInputStream dataStream = new DataInputStream(fileStream);
	  BufferedReader reader = new BufferedReader(new InputStreamReader(dataStream));
	  String fileLine = "";
	  String delimiter = Constants.WIN_FILE_DELIMITER;
	  int xPos = 0;
	  int yPos = 0;
	  int foregroundSquare;
	  
	  foregroundSquare = Constants.WIN_MSG_FOREGROUND;

	  while ((fileLine = reader.readLine()) != null)   
	  {
		StringTokenizer tokenizer = new StringTokenizer(fileLine,
					                                    delimiter);
		
		xPos = Integer.parseInt(tokenizer.nextToken());
		yPos = Integer.parseInt(tokenizer.nextToken());
		
		winConfig[xPos][yPos] = foregroundSquare;
	  }
	  
	  dataStream.close();
    }
	  
	catch (Exception e)
	{ 
	  System.err.println("Error: " + e.getMessage());
	} 
  }
  
  private void initWinConfig()
  {
	int i;
	int j;
	int horSquareCnt;
	int vertSquareCnt;
	int backgroundSquare;
	
	horSquareCnt = Constants.HOR_SQUARE_CNT;
	vertSquareCnt = Constants.VERT_SQUARE_CNT;
	backgroundSquare = Constants.WIN_MSG_BACKGROUND;
	
	for (i = 0; i < horSquareCnt; i++)
	{	  
	  for (j = 0; j < vertSquareCnt; j++)
	  {
		winConfig[i][j] = backgroundSquare;
	  }
	}
  }
}
