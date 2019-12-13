package trivia;

import java.util.ArrayList;
import java.util.Collections;
import common.Constants;

public class QuizState 
{
  public Quiz quiz;
  
  public QuizState()
  {
	quiz = new Quiz();  
	  
	setContent();
	shuffleOptions();
  }
  
  public Quiz getQuiz()
  {
	return quiz;
  }
  
  private void setContent()
  {
	quiz.setQuestion("Why hfhf dyh bdhd fjfjg gog tod hr rjf fjejeje fhfhdkd fhfhfhf idu sd?");
	quiz.setAnswer("A");
		
	quiz.setOptionA("A");
	quiz.setOptionB("B");
	quiz.setOptionC("C");
	quiz.setOptionD("D");
		
	quiz.setIsTrueOrFalse(false);
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
