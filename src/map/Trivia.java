package map;

import trivia.QuizBag;

public class Trivia 
{
  private QuizBag quizBag;
  
  public Trivia()
  {
	quizBag = new QuizBag();
	quizBag.fillBag();
  }
  
  public QuizBag getQuizBag()
  {
	return quizBag;
  }
}
