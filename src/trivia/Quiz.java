package trivia;

public class Quiz 
{
  private String question;
  private String answer;
  private String optionA;
  private String optionB;
  private String optionC;
  private String optionD;
  private boolean isTrueOrFalse;
  
  public String getQuestion()
  {
	return question;
  }
  
  public String getAnswer()
  {
	return answer;
  }
  
  public String getOptionA()
  {
	return optionA;
  }
  
  public String getOptionB()
  {
	return optionB;
  }
  
  public String getOptionC()
  {
	return optionC;
  }
  
  public String getOptionD()
  {
	return optionD;
  }
  
  public boolean getIsTrueOrFalse()
  {
	return isTrueOrFalse;
  }
  
  public void setQuestion(String input)
  {
	question = input;
  }
  
  public void setAnswer(String input)
  {
	answer = input;
  }
  
  public void setOptionA(String input)
  {
	optionA = input;
  }
  
  public void setOptionB(String input)
  {
	optionB = input;
  }
  
  public void setOptionC(String input)
  {
	optionC = input;
  }
  
  public void setOptionD(String input)
  {
	optionD = input;
  }
  
  public void setIsTrueOrFalse(boolean input)
  {
	isTrueOrFalse = input;
  }
}
