package trivia;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import common.Colours;
import common.Constants;
import common.Fonts;
import map.MapGUI;
import map.MapState;

import java.util.Timer;
import java.util.TimerTask;

public class QuizGUI extends JFrame implements ActionListener
{
  public static QuizState quizState;
  public static JButton optionA;
  public static JButton optionB;
  public static JButton optionC;
  public static JButton optionD;
  public static MapGUI mapGUI;
  
  public QuizGUI(MapGUI mapGUI)
  {
	super();
	setVisible(false);
	
	this.mapGUI = mapGUI;
	quizState = new QuizState(mapGUI.trivia.getQuizBag());
  }
  
  public void run()
  {
	setProperties();
	setContent();
  }

  @Override
  public void actionPerformed(ActionEvent e) 
  {
	JButton buttonChose;

	buttonChose = (JButton)e.getSource();
	
	revealAnswer(buttonChose);
	
	updateMapState(buttonChose);
	
	//go back to player map screen
	goMapScreen();
  }
  
  private void setProperties()
  {  
	//set basic window frame properties
	setSize(Constants.QUIZ_WIDTH,
			Constants.QUIZ_HEIGHT);
	setTitle(Constants.TITLE);	
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  private void setContent() 
  {
	setQuestionArea();
	setDivider();
	setOptions();
  }
  
  private void setQuestionArea()
  {
    JTextPane questionArea; 
	Border textAreaBorder;
	Font questionFont = null;
	int questionLength = 0;
			
	getContentPane().setLayout(new BoxLayout(getContentPane(), 
				                             BoxLayout.Y_AXIS));
		
	questionArea = new JTextPane();
	questionArea.setBackground(Colours.YELLOW_2);
	questionArea.setPreferredSize(new Dimension(Constants.QUESTION_WIDTH, 
				                                Constants.QUESTION_HEIGHT));
	textAreaBorder = BorderFactory.createLineBorder(Colours.DARK_GREY_3);
	questionArea.setEditable(false);	
		
	questionArea.setBorder(new CompoundBorder(textAreaBorder,
				                              BorderFactory.createEmptyBorder(20, 
																		      20, 
																		      20, 
																		      20)));	
	questionArea.setText('\n' + quizState.getQuiz().getQuestion());  
	questionLength = questionArea.getText().length();
	
	questionFont = Fonts.getTextSize(questionLength,
			                         'Q'); /* question text type */
	questionArea.setFont(questionFont);
	alignText(questionArea);
	
	JPanel questionPanel = new JPanel();
	questionPanel.setBackground(Colours.WHITE_1);
	questionPanel.add(questionArea);
	questionPanel.setBorder(BorderFactory.createEmptyBorder(20, 
				                		                    20, 
				                		                    46, 
				                		                    20));
	getContentPane().add(questionPanel);
  }
  
  private void setDivider()
  {
	JTextField line;
	JPanel linePanel;
	
	line = new JTextField();
	line.setPreferredSize(new Dimension(Constants.MAP_WIDTH,
			                            1));
	line.setBackground(Colours.DARK_GREY_2);
	
	linePanel = new JPanel();
	linePanel.setPreferredSize(new Dimension(Constants.MAP_WIDTH, 
                                             0));
	linePanel.setBackground(Colours.WHITE_1);
	linePanel.add(line);
	getContentPane().add(linePanel);
  }
  
  private void setOptions()
  {
	int optionHeight = Constants.OPTION_HEIGHT;
	int optionWidth = Constants.OPTION_WIDTH;
	int optionALength = 0;
	int optionBLength = 0;
	int optionCLength = 0;
	int optionDLength = 0;
	Font optionAFont;
	Font optionBFont;
	Font optionCFont;
	Font optionDFont;
	JPanel buttonPanelA;
	JPanel buttonPanelB;
	JPanel buttonPanelC;
	JPanel buttonPanelD;
	Quiz nextQuiz;
	boolean isTrueOrFalse = false;
	
	nextQuiz = quizState.getQuiz();
	isTrueOrFalse = nextQuiz.getIsTrueOrFalse();
	
	optionA = new JButton(quizState.getQuiz().getOptionA());
	optionB = new JButton(quizState.getQuiz().getOptionB());
	
	optionA.setPreferredSize(new Dimension(optionWidth, 
                                           optionHeight));
    optionB.setPreferredSize(new Dimension(optionWidth, 
                                           optionHeight));
    
    optionA.setBackground(Colours.BLUE_2);
    optionB.setBackground(Colours.BLUE_2);   
	optionA.setForeground(Colours.WHITE_1);
    optionB.setForeground(Colours.WHITE_1);
    
    optionALength = optionA.getText().length();
    optionAFont = Fonts.getTextSize(optionALength, 
    		                        'A') ;
    optionBLength = optionB.getText().length();
    optionBFont = Fonts.getTextSize(optionBLength, 
    		                        'A') ;
    
    optionA.setFont(optionAFont);
    optionB.setFont(optionBFont);
    
	optionA.addActionListener(this);
	optionB.addActionListener(this);
	
	buttonPanelA = new JPanel();
	buttonPanelB = new JPanel();	
	buttonPanelA.add(optionA);
	buttonPanelB.add(optionB);
	
	buttonPanelA.setBorder(BorderFactory.createEmptyBorder(32, 
	                                                       0, 
	                                                       0, 
	                                                       0));
    buttonPanelB.setBorder(BorderFactory.createEmptyBorder(15, 
	                                                       0, 
	                                                       0, 
	                                                       0));
	
	getContentPane().add(buttonPanelA);
	getContentPane().add(buttonPanelB);
    
	if (isTrueOrFalse == false)
	{
	  optionC = new JButton(quizState.getQuiz().getOptionC());
	  optionD = new JButton(quizState.getQuiz().getOptionD());
	  
	  optionC.setPreferredSize(new Dimension(optionWidth, 
                                             optionHeight));
      optionD.setPreferredSize(new Dimension(optionWidth, 
                                             optionHeight));
      
      optionC.setBackground(Colours.BLUE_2);
      optionD.setBackground(Colours.BLUE_2);     
  	  optionC.setForeground(Colours.WHITE_1);
      optionD.setForeground(Colours.WHITE_1);
      
      optionCLength = optionC.getText().length();
      optionCFont = Fonts.getTextSize(optionCLength, 
      		                          'A') ;
      optionDLength = optionD.getText().length();
      optionDFont = Fonts.getTextSize(optionDLength, 
      		                          'A') ;
      
      optionC.setFont(optionCFont);
      optionD.setFont(optionDFont);
      
      optionC.addActionListener(this);
  	  optionD.addActionListener(this);
  	  
  	  buttonPanelC = new JPanel();
  	  buttonPanelD = new JPanel();   	  
  	  buttonPanelC.add(optionC);
  	  buttonPanelD.add(optionD);
  	  
  	  buttonPanelC.setBorder(BorderFactory.createEmptyBorder(15, 
	                                                         0, 
	                                                         0, 
	                                                         0));
      buttonPanelD.setBorder(BorderFactory.createEmptyBorder(15, 
	                                                         0, 
	                                                         30, 
	                                                         0));
      
      getContentPane().add(buttonPanelC);
  	  getContentPane().add(buttonPanelD);
	}
	
	else
	{
	  JButton dummyButton = new JButton("");
	  JButton dummyButtonTwo = new JButton("");
	  
	  dummyButton.setPreferredSize(new Dimension(optionWidth, 
                                                 optionHeight));
      dummyButtonTwo.setPreferredSize(new Dimension(optionWidth, 
                                                    optionHeight));
      
      dummyButton.setBackground(Colours.YELLOW_1);
      dummyButtonTwo.setBackground(Colours.YELLOW_1);
  	  
  	  buttonPanelC = new JPanel();
  	  buttonPanelD = new JPanel();   	  
  	  buttonPanelC.add(dummyButton);
  	  buttonPanelD.add(dummyButtonTwo);
  	  
  	  buttonPanelC.setBorder(BorderFactory.createEmptyBorder(15, 
                                                             0, 
                                                             0, 
                                                             0));
      buttonPanelD.setBorder(BorderFactory.createEmptyBorder(15, 
                                                             0, 
                                                             30, 
                                                             0));
  	  
      getContentPane().add(buttonPanelC);
      getContentPane().add(buttonPanelD);
	}
  }
  
  private void revealAnswer(JButton buttonChose)
  {
	Quiz quiz;
	String answer;
	boolean answerFound = false;
		  
	quiz = quizState.getQuiz();
	answer = quiz.getAnswer();
		
	if (isCorrectAnswer(buttonChose) == true) 
	{		
	  buttonChose.setBackground(Colours.GREEN_1);
	}
		
	else
	{
	  buttonChose.setBackground(Colours.RED_1);
		  
	  if (optionA.getText().equalsIgnoreCase(answer) == true)
	  {
		optionA.setBackground(Colours.GREEN_1);
			
		answerFound = true;
	  }
		  
	  if (answerFound == false)
	  {
	    if (optionB.getText().equalsIgnoreCase(answer) == true)
		{
		  optionB.setBackground(Colours.GREEN_1);
			  
		  answerFound = true;
		}
	  }
		  
	  if (answerFound == false)
	  {
	    if (optionC.getText().equalsIgnoreCase(answer) == true)
		{
		  optionC.setBackground(Colours.GREEN_1);
		      
		  answerFound = true;
		}
	  }
		  
	  if (answerFound == false)
	  {
		if (optionD.getText().equalsIgnoreCase(answer) == true)
		{
		  optionD.setBackground(Colours.GREEN_1);
			  
		  answerFound = true;
		}
	  }
	}
  }
  
  private void updateMapState(JButton buttonChose)
  {
	int xPos = 0;
	int yPos = 0;
	
	xPos = MapState.activeQuizSquare.xPos;
	yPos = MapState.activeQuizSquare.yPos;
	  
	if (isCorrectAnswer(buttonChose) == true)
	{
	  MapState.mapConfig[xPos][yPos] = Constants.GROUND_SQUARE;
	}
	
	else
	{
	  MapState.mapConfig[xPos][yPos] = Constants.BLACK_HOLE_SQUARE;
	}
	
	MapState.activeQuizSquare.xPos = -1;
	MapState.activeQuizSquare.yPos = -1;
  }
  
  private boolean isCorrectAnswer(JButton buttonChose)
  {
    boolean isCorrectAnswer = false;
	String optionChose;
	Quiz quiz;
	String answer;
		  
	optionChose = buttonChose.getActionCommand();	

	quiz = quizState.getQuiz();
	answer = quiz.getAnswer();
		
	if (optionChose.equalsIgnoreCase(answer)) 
	{		
	  isCorrectAnswer = true;
	}
	
	return isCorrectAnswer;
  }
  
  private void goMapScreen()
  {
    Timer timer; 
	TimerTask exit;
	
	timer = new Timer(true);
	exit = new ExitQuizGUI(Constants.MAIN_LOAD_TIME,
                           this);
	
	//call task to exit quiz GUI in 150 milliseconds
	timer.schedule(exit,
				   150);
	
	mapGUI.setVisible(true);
	this.setVisible(true);
  }
  
  private void alignText(JTextPane questionArea)
  { 
	StyledDocument doc = questionArea.getStyledDocument();
	SimpleAttributeSet center = new SimpleAttributeSet();
	
	StyleConstants.setAlignment(center, 
			                    StyleConstants.ALIGN_CENTER);
	doc.setParagraphAttributes(0, 
			                   doc.getLength(), 
			                   center, 
			                   false); 
  }
}
