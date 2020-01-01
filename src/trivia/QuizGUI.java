package trivia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	Font questionFont;
			
	getContentPane().setLayout(new BoxLayout(getContentPane(), 
				                             BoxLayout.Y_AXIS));
		
	questionArea = new JTextPane();
	questionArea.setBackground(new Color(255,
				                         243,
				                         111));
	questionArea.setPreferredSize(new Dimension(Constants.QUESTION_WIDTH, 
				                                Constants.QUESTION_HEIGHT));
	textAreaBorder = BorderFactory.createLineBorder(new Color(94,
				                                              93,
				                                              70));
	questionArea.setEditable(false);	
		
	questionFont = new Font("Serif", 
				            Font.PLAIN, 
				            25);
	questionArea.setFont(questionFont);
	questionArea.setBorder(new CompoundBorder(textAreaBorder,
				                              BorderFactory.createEmptyBorder(20, 
																		      20, 
																		      20, 
																		      20)));	
	questionArea.setText('\n' + quizState.getQuiz().getQuestion());  
	alignText(questionArea);
		
	JPanel questionPanel = new JPanel();
	questionPanel.setBackground(new Color(255,
			                              255,
			                              255));
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
	line.setBackground(new Color(100,
			                     100,
			                     100));
	
	linePanel = new JPanel();
	linePanel.setPreferredSize(new Dimension(Constants.MAP_WIDTH, 
                                             0));
	linePanel.setBackground(new Color(255,
			                          255,
			                          255));
	linePanel.add(line);
	getContentPane().add(linePanel);
  }
  
  private void setOptions()
  {
	int optionHeight = Constants.OPTION_HEIGHT;
	int optionWidth = Constants.OPTION_WIDTH;
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
    
    optionA.setBackground(new Color(20,
                                    138,
                                    255));
    optionB.setBackground(new Color(20,
                                    138,
                                    255));
    
	optionA.setForeground(new Color(255,
                                    255,
                                    255));
    optionB.setForeground(new Color(255,
			                        255,
			                        255));
    
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
      
      optionC.setBackground(new Color(20,
	                                  138,
	                                  255));
      optionD.setBackground(new Color(20,
	                                  138,
	                                  255));
      
  	  optionC.setForeground(new Color(255,
			                          255,
			                          255));
      optionD.setForeground(new Color(255,
			                          255,
			                          255));
      
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
