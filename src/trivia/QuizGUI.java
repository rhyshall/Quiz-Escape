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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.ParagraphView;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

import common.Constants;

public class QuizGUI extends JFrame implements ActionListener
{
  public static QuizState quizState;
	
  public QuizGUI()
  {
	super();
	
	quizState = new QuizState();
  }
  
  public void run()
  {
	setProperties();
	setContent();
	
	setVisible(true);
  }
  
  @Override
  public void actionPerformed(ActionEvent e) 
  {
	//		
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
	JButton optionA;
	JButton optionB;
	JButton optionC;
	JButton optionD;
	JPanel buttonPanelA;
	JPanel buttonPanelB;
	JPanel buttonPanelC;
	JPanel buttonPanelD;
	
	optionA = new JButton(quizState.getQuiz().getOptionA());
	optionB = new JButton(quizState.getQuiz().getOptionB());
	optionC = new JButton(quizState.getQuiz().getOptionC());
	optionD = new JButton(quizState.getQuiz().getOptionD());
	
	optionA.setPreferredSize(new Dimension(optionWidth, 
			                               optionHeight));
	optionB.setPreferredSize(new Dimension(optionWidth, 
			                               optionHeight));
	optionC.setPreferredSize(new Dimension(optionWidth, 
			                               optionHeight));
	optionD.setPreferredSize(new Dimension(optionWidth, 
			                               optionHeight));
	
	optionA.setBackground(new Color(20,
			                        138,
			                        255));
	optionB.setBackground(new Color(20,
						            138,
						            255));
	optionC.setBackground(new Color(20,
						            138,
						            255));
	optionD.setBackground(new Color(20,
						            138,
						            255));
	
	optionA.setForeground(new Color(255,
						            255,
						            255));
	optionB.setForeground(new Color(255,
									255,
									255));
	optionC.setForeground(new Color(255,
									255,
									255));
	optionD.setForeground(new Color(255,
									255,
									255));
	
	buttonPanelA = new JPanel();
	buttonPanelB = new JPanel();
	buttonPanelC = new JPanel();
	buttonPanelD = new JPanel(); 
	
	buttonPanelA.add(optionA);
	buttonPanelB.add(optionB);
	buttonPanelC.add(optionC);
	buttonPanelD.add(optionD);
	
	buttonPanelA.setBorder(BorderFactory.createEmptyBorder(32, 
												           0, 
												           0, 
												           0));
	buttonPanelB.setBorder(BorderFactory.createEmptyBorder(15, 
												           0, 
												           0, 
												           0));
	buttonPanelC.setBorder(BorderFactory.createEmptyBorder(15, 
												           0, 
												           0, 
												           0));
	buttonPanelD.setBorder(BorderFactory.createEmptyBorder(15, 
												           0, 
												           30, 
												           0));
	
	getContentPane().add(buttonPanelA);
	getContentPane().add(buttonPanelB);
	getContentPane().add(buttonPanelC);
	getContentPane().add(buttonPanelD);
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
