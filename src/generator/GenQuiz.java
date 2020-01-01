package generator;

import java.util.Random;

import common.Constants;
import trivia.Quiz;
import trivia.QuizBag;

public class GenQuiz 
{
  private Quiz quiz;
	
  public GenQuiz(QuizBag quizBag)
  {
	Quiz nextQuiz = new Quiz();
	int randNum = 0;
	Random randGen = new Random();
	int bagSize = 0;
	int[] blackList;
	int blackListSize = 0;	
	
	blackList = quizBag.getBlackList();
	blackListSize = quizBag.getBlackListSize();
	
	bagSize = quizBag.getSize();
	
	//get question index at random
	//if already chosen, get another index until otherwise
	while(true)
	{
	  randNum = randGen.nextInt(bagSize);
		
      if (allowQuizIndex(blackList,
    		             blackListSize,
    		             randNum)== false)
      {
    	continue;
      }
      
      else
      {
    	break;
      }
	}
	
	nextQuiz = quizBag.grab(randNum); 
	
	quiz = new Quiz();
	quiz.setQuestion(nextQuiz.getQuestion());
	quiz.setAnswer(nextQuiz.getAnswer());
	quiz.setOptionA(nextQuiz.getOptionA());
	quiz.setOptionB(nextQuiz.getOptionB());
	quiz.setOptionC(nextQuiz.getOptionC());
	quiz.setOptionD(nextQuiz.getOptionD());
	quiz.setIsTrueOrFalse(nextQuiz.getIsTrueOrFalse());
  }
  
  public Quiz getQuiz()
  {
	return quiz;
  }
  
  private boolean allowQuizIndex(int[] blackList,
		                         int blackListSize,
		                         int index)
  {
	int i;
	boolean allowQuizIndex = true;
	  
	for (i = 0; i < blackListSize; i++)
	{
      if (blackList[i] == index)
      {
    	allowQuizIndex = false;
    	break;
      }
	}
	
	return allowQuizIndex;
  }
}
