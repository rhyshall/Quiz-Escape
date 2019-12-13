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
  
  public QuizBag()
  {
	quizList = new ArrayList<Quiz>(1000);
	size = 0;
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

	  while ((fileLine = reader.readLine()) != null)   
	  {
		StringTokenizer tokenizer = new StringTokenizer(fileLine,
				                                        delimiter);
		
		Quiz quiz = new Quiz();
		quiz.setQuestion(tokenizer.nextToken());
		
		answer = tokenizer.nextToken();
		
		quiz.setAnswer(answer);
		quiz.setOptionA(answer);
		quiz.setOptionB(tokenizer.nextToken());
		
		if (tokenizer.hasMoreTokens())
		{		
		  quiz.setOptionC(tokenizer.nextToken());
		  quiz.setOptionD(tokenizer.nextToken());
		  quiz.setIsTrueOrFalse(false);
		}
		
		else
		{
		  quiz.setOptionC(null);
		  quiz.setOptionD(null);
		  quiz.setIsTrueOrFalse(true);
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
}
