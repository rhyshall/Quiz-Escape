package trivia;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

import common.Constants;

public class QuizBag 
{
  private ArrayList<Quiz> quizList;
  private int size;
  private int[] blackList;
  private int blackListSize;
  
  public QuizBag()
  {
	quizList = new ArrayList<Quiz>(Constants.SUGG_MAX_QUEST);
	size = 0;
	
	blackList = new int[Constants.SUGG_MAX_QUEST];
	blackListSize = 0;
  }
  
  public void fillBag()
  {
    String workDir = "";
    String filePath = "";
	  
    workDir = Paths.get(".").toAbsolutePath().normalize().toString();
	filePath = workDir + Constants.TRIVIA_FILE_PATH; 
	  	  
	try
	{
	  FileInputStream fileStream = new FileInputStream(new File(filePath));
      DataInputStream dataStream = new DataInputStream(fileStream);
	  BufferedReader reader = new BufferedReader(new InputStreamReader(dataStream));
	  String fileLine = "";
	  String delimiter = Constants.TRIVIA_FILE_DELIMITER;
	  String answer = "";
	  
	  //get junk from first line of file
	  fileLine = reader.readLine();
	  
	  while ((fileLine = reader.readLine()) != null)   
	  {
		StringTokenizer tokenizer = new StringTokenizer(fileLine,
				                                        delimiter);
		
		Quiz quiz = new Quiz();
		quiz.setQuestion(tokenizer.nextToken());
		
		answer = tokenizer.nextToken();
		
		quiz.setOptionA(answer);
		quiz.setOptionB(tokenizer.nextToken());
		
		if (tokenizer.hasMoreTokens())
		{
		  quiz.setAnswer(answer);
			
		  quiz.setOptionC(tokenizer.nextToken());
		  quiz.setOptionD(tokenizer.nextToken());
		  quiz.setIsTrueOrFalse(false);
		}
		
		else
		{
		  quiz.setOptionC(null);
		  quiz.setOptionD(null);
		  quiz.setIsTrueOrFalse(true);
		  
		  answer = answer.replaceAll("\\s+", 
                                     "");
		  quiz.setAnswer(answer);
		}
		
		quizList.add(quiz);
		size++;
	  }
   
	  dataStream.close();
    }
	  
	catch (Exception e)
	{ 
	  System.err.println("Error: " + e.getMessage());
	} 
  }
  
  public Quiz grab(int index)
  {
	blackList[blackListSize] = index;
	blackListSize++;
	  
	return quizList.get(index);
  }
  
  public int getSize()
  {
	return size;
  }
  
  public int[] getBlackList() 
  {
	return blackList;  
  }
  
  public int getBlackListSize()
  {
	return blackListSize;
  }
}
