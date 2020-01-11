package common;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class Fonts 
{  
  public static Font getTextSize(int textLength,
		                         char textType)
  {
	Font font = null;
    String workDir = "";
	String filePath = "";
	String fontType= "";
			  
	workDir = Paths.get(".").toAbsolutePath().normalize().toString();
	
	if (textType == 'Q')
	{
	  filePath = workDir + Constants.QUEST_FONT_FILE_PATH; 
	}
	
	else 
	{
	  filePath = workDir + Constants.ANSWER_FONT_FILE_PATH; 
	}
			  	  
	try
	{
	  FileInputStream fileStream = new FileInputStream(new File(filePath));
	  DataInputStream dataStream = new DataInputStream(fileStream);
	  BufferedReader reader = new BufferedReader(new InputStreamReader(dataStream));
	  String fileLine = "";
	  String fontSizeDelimiter = Constants.FONT_SIZE_DELIMITER;
	  String textSizeDelimiter = Constants.TEXT_SIZE_DELIMITER;
	  String bounds = "";
	  int lowerBound = 0;
	  int upperBound = 0;
	  int fontSize = 0;
	  
	  while ((fileLine = reader.readLine()) != null)   
	  {
		StringTokenizer fontSizeTokenizer = new StringTokenizer(fileLine,
					                                            fontSizeDelimiter);		
		bounds = fontSizeTokenizer.nextToken();
		
		StringTokenizer textSizeTokenizer = new StringTokenizer(bounds,
                                                                textSizeDelimiter);

		if (textSizeTokenizer.countTokens() == 1)
		{
		  if (upperBound == 0)
		  {
	        lowerBound = -1;
		    upperBound = Integer.parseInt(textSizeTokenizer.nextToken());
		  }
		  
		  else
		  {
			lowerBound = Integer.parseInt(textSizeTokenizer.nextToken());
			upperBound = 10000;
		  }
		}
		
		else 
		{
		  lowerBound = Integer.parseInt(textSizeTokenizer.nextToken());
		  upperBound = Integer.parseInt(textSizeTokenizer.nextToken());
		}
		
		if ((textLength > lowerBound) 
		  && (textLength <= upperBound))
		{
		  fontSize = Integer.parseInt(fontSizeTokenizer.nextToken());
		  
		  if (textType == 'Q')
		  {
		    fontType = "TimesRoman"; 
		  }
		  
		  else
		  {
			fontType = "Arial Rounded MT Bold";
		  }
		  
		  font = new Font(fontType, 
                          Font.PLAIN, 
                          fontSize);
		} 
	  }
	  
	  dataStream.close();
    }
	  
	catch (Exception e)
	{ 
	  System.err.println("Error: " + e.getMessage());
	} 

	return font;
  }
}
