package generator;

import map.QuizSquare;
import map.QuizSquares;
import java.util.Random;

public class GenQuizSquare 
{
  public int xPos;	
  public int yPos;
	
  public GenQuizSquare(QuizSquares quizSquareList)
  {
	generateXY(quizSquareList);
  }
  
  private void generateXY(QuizSquares quizSquareList)
  {
	int squareCnt = 0;
	int randNum = 0;
	Random randGen = new Random();
	QuizSquare quizSquare;
	
	squareCnt = (quizSquareList.entries).size();	
	randNum = randGen.nextInt(squareCnt);
	
	quizSquare = (quizSquareList.entries).get(randNum);
	xPos = quizSquare.xPos;
	yPos = quizSquare.yPos;
  }
}
