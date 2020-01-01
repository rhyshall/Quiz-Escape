package trivia;

import java.util.ArrayList;
import java.util.Collections;
import common.Constants;
import generator.GenQuiz;

public class QuizState 
{
  private Quiz quiz;
  private QuizBag quizBag;
  
  public QuizState(QuizBag quizBag)
  {
	quiz = new Quiz();  
	  
	setContent(quizBag);
	shuffleOptions();
  }
  
  public Quiz getQuiz()
  {
	return quiz;
  }
  
  public QuizBag getQuizBag()
  {
	return quizBag;
  }
  
  public void setQuizBag(QuizBag quizBag)
  {
	this.quizBag = quizBag;
  }
  
  private void setContent(QuizBag quizBag)
  {
	GenQuiz genQuiz = new GenQuiz(quizBag);
	Quiz nextQuiz = new Quiz();
	
	nextQuiz = genQuiz.getQuiz();
	  
	quiz.setQuestion(nextQuiz.getQuestion());
	quiz.setAnswer(nextQuiz.getAnswer());
		
	quiz.setOptionA(nextQuiz.getOptionA());
	quiz.setOptionB(nextQuiz.getOptionB());
	quiz.setOptionC(nextQuiz.getOptionC());
	quiz.setOptionD(nextQuiz.getOptionD());
		
	quiz.setIsTrueOrFalse(nextQuiz.getIsTrueOrFalse());
  }
  
  private void shuffleOptions()
  {
	if (quiz.getIsTrueOrFalse() == false)
	{	 
	  int answerChoicesCnt = Constants.ANSWER_CHOICES_CNT;
	  ArrayList<Integer> optionPosList;
	  String[] optionList = new String[answerChoicesCnt];
	  int i = 0;
	  int optionPos = 0;
	  
	  //initialize option positions in order from 1..n
	  optionPosList = initOptionPos();	  
	  
	  //shuffle option positions (eg. 1,2,3,4 => 4,2,1,3)
	  Collections.shuffle(optionPosList); 

	  for (i = 0; i < answerChoicesCnt; i++)
	  {
		optionPos = optionPosList.get(i);

		switch (optionPos)
		{
		  case 1:
		  {
			optionList[i] = quiz.getOptionA();
			break;
		  }
		  
		  case 2:
		  {
			optionList[i] = quiz.getOptionB();
			break;
		  }
		  
		  case 3:
		  {
			optionList[i] = quiz.getOptionC();
			break;
		  }
		  
		  case 4:
		  {
			optionList[i] = quiz.getOptionD();;
			break;
		  }
		}	    
	  } 
	
	  quiz.setOptionA(optionList[0]);
	  quiz.setOptionB(optionList[1]);
	  quiz.setOptionC(optionList[2]);
	  quiz.setOptionD(optionList[3]);
	}
	
	else
	{
	  quiz.setOptionA("True");
	  quiz.setOptionB("False");
	}
  }
  
  private static ArrayList<Integer> initOptionPos()
  {
	int i = 0;  
	int answerChoicesCnt = Constants.ANSWER_CHOICES_CNT;
	ArrayList<Integer> optionPosList = new ArrayList<Integer>(answerChoicesCnt);
	  
    for (i = 1; i <= answerChoicesCnt; i++)
    {
      optionPosList.add(i);
    }
    
    return optionPosList;
  }
}
