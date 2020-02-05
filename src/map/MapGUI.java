package map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import common.Colours;
import common.Constants;
import generator.GenColour;
import generator.GenCoordinate;
import generator.GenFlashCnt;
import generator.GenQuizSquare;
import trivia.QuizGUI;

public class MapGUI extends JFrame implements KeyListener
{
  public MapState mapState;
  public Trivia trivia;
  public QuizGUI quizGUI;
  public static final int screenWidth = Constants.MAP_WIDTH;
  public static final int screenHeight = Constants.MAP_HEIGHT;
  public static final int horSquareCnt = Constants.HOR_SQUARE_CNT;
  public static final int vertSquareCnt = Constants.VERT_SQUARE_CNT;
  public int topBorderSize;
  public int bottomBorderSize;
  public int leftBorderSize;
  public int rightBorderSize;
  public int blockWidth;
  public int blockHeight;
  public static boolean victory;
  public boolean hasStarted = false;
  JButton promptStart;
	
  public MapGUI()
  {
	super();
	
	Colours.assemble();
	MapState.construct(); 	
	trivia = new Trivia(); 
	victory = false;
  }
	
  public void run()
  {
	setProperties();
	setVisible(true);
	
	saveBorderDim();
	saveBlockDim();
	
	startPrompt();
  }
  
  @Override
  public void keyTyped(KeyEvent e)
  {
	;
  }
  
  @Override
  public void keyPressed(KeyEvent e)
  { 
	if (victory == false)
	{
	  switch(e.getKeyCode())
	  {
	    case KeyEvent.VK_UP:
	    {
		  if (MapState.canMoveUp() == true)
		  {
		    MapState.movePlayerUp();
		    repaint();
		    victory = true;
		    if (victory == true)
		    {
			  victory();
		    }
		  }
		
		  break;
	    }
	  
	    case KeyEvent.VK_RIGHT:
	    {
		  if (MapState.canMoveRight() == true)
		  {
		    MapState.movePlayerRight();
		    repaint();
		  
		    if (victory == true)
		    {
			  victory();
		    }
	      }
		
		  break;
	    }
		 
	    case KeyEvent.VK_DOWN:
	    {
		  if (MapState.canMoveDown() == true)
	      { 
	        MapState.movePlayerDown();
	        repaint();
	      
	        if (victory == true)
		    {
			  victory();
		    }
	      
	        break;
		  }
	    }
	  
	    case KeyEvent.VK_LEFT:
	    {
		  if (MapState.canMoveLeft() == true)
		  {
		    MapState.movePlayerLeft();
		    repaint();
		  
		    if (victory == true)
		    {
			  victory();
		    }
		  }
		
		  break;
	    }
	 
	    case KeyEvent.VK_ENTER:
	    { 
	      if (hasStarted == true)
	      {
		    if (MapState.isBesideQuestion(MapState.playerXPos,
				                          MapState.playerYPos) == true)
		    {
	          QuizSquares quizSquares = new QuizSquares();
	          FlashQuizSquare.quizSquares = quizSquares;
	      
		      GenQuizSquare targetSquare = new GenQuizSquare(quizSquares);
		      FlashQuizSquare.targetSquare = targetSquare;
		  
		      MapState.setActiveQuiz(targetSquare);
		  
		      flashSquares();
		    }
		    
		    repaint();		
		    break;
	      }
	      
	      else
	      {
	        //remove(promptStart);
	        hasStarted = true;
	      }
	    }
	  }
	}
  }
  
  public void keyReleased(KeyEvent e)
  {
	;
  }
  
  public void paint(Graphics g)
  {
	int i = 0;
	int j = 0;
	int xPos = 0;
	int yPos = 0;
	Color colour = null;
	GenCoordinate genCoord;
	
	if (victory == true)
	{ 
	  //display win screen
	  if (GenCoordinate.isBuilt() == false)
	  {
		GenCoordinate.construct();
	  }

	  genCoord = new GenCoordinate();
	  i = genCoord.xPos;
	  j = genCoord.yPos;
	
	  if ((i == -1)
	    || (j == -1))
	  {
		FlashWin.stop();
	  }
	  
	  else
	  {
	    xPos = calcNextXPos(i);
	    yPos = calcNextYPos(j);
		
	    colour = getColourType(i,
                               j);
        g.setColor(colour);

        g.fillRect(xPos, 
                   yPos, 
                   blockWidth, 
                   blockHeight);

        g.setColor(Color.black);

        g.drawRect(xPos, 
                   yPos, 
                   blockWidth, 
                   blockHeight);
	  }
	}
	
	else
	{
	  //display game map
      for(i = 0; i < horSquareCnt; i++)
	  {
        xPos = calcNextXPos(i);
    	
	    for(j = 0; j < vertSquareCnt; j++)
	    {
		  yPos = calcNextYPos(j);
	    
	      colour = getColourType(i,
	    		                 j);
	      g.setColor(colour);

	      g.fillRect(xPos, 
	    		     yPos, 
	    		     blockWidth, 
	    		     blockHeight);
	      
	      g.setColor(Color.black);
	      
	      g.drawRect(xPos, 
	    		     yPos, 
	    		     blockWidth, 
	    		     blockHeight);
	    }
	  }
	}
  }
  
  private void setProperties()
  {  
	//set basic window frame properties
	setSize(screenWidth,
	    	screenHeight);
	setTitle(Constants.TITLE);
	addKeyListener(this);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  private void saveBorderDim()
  {
	//get border dimensions
	Insets insets = getInsets();
	
	//save border dimensions
	topBorderSize = insets.top;
	bottomBorderSize = insets.bottom;
	leftBorderSize = insets.left;
	rightBorderSize = insets.right;
  }
  
  private void saveBlockDim()
  {
	//calculate and save block dimensions
	blockWidth = (screenWidth-leftBorderSize-rightBorderSize)/horSquareCnt;
	blockHeight = (screenHeight-topBorderSize-bottomBorderSize)/vertSquareCnt;
  }
  
private void startPrompt()
  {
	JPanel promptPanel;
	  
	promptStart = new JButton(Constants.START_PROMPT_TEXT);
	promptStart.setBounds(186, 
			              275, 
			              311, 
			              56); 
	promptStart.setBackground(Colours.LIGHT_GREY_1);
	Border thickBorder = new LineBorder(Colours.BLACK_1, 
			                            1);
	promptStart.setBorder(thickBorder);
	promptStart.setFont(new Font("TimesRoman",
			                     Font.PLAIN,
			                     22));

	promptPanel = new JPanel();
	promptPanel.setLayout(null);
	promptPanel.setPreferredSize(new Dimension(200, 
			                                   25));
	promptPanel.add(promptStart); 
	promptStart.addKeyListener(this);
	promptStart.setFocusable(true); 
	getContentPane().add(promptPanel);
  }
  
  private int calcNextXPos(int nextIndex)
  {
	int xPos = 0;
	
	xPos = (blockWidth*nextIndex)+leftBorderSize;
	
	return xPos;
  }
  
  private int calcNextYPos(int nextIndex)
  {
	int yPos = 0;
	  
	yPos = (blockHeight*nextIndex)+topBorderSize;
	  
	return yPos;
  }
  
  private Color getColourType(int xPos, 
		                      int yPos)
  {
	int squareType = 0;
	int winText = Constants.WIN_MSG_FOREGROUND;
	Color colour = null;
	
	if (victory == true)
	{
	  squareType = WinState.winConfig[xPos][yPos];
	  
	  if (squareType == winText)
	  {
		GenColour genColour = new GenColour();
		colour = genColour.colour; 
	  }
	  
	  else
	  {
		colour = Colours.BLACK_1;
	  }
	}
	
	else
	{
	  squareType = MapState.mapConfig[xPos][yPos];
	
	  switch(squareType)
	  {
	    case Constants.PLAYER_SQUARE:
	    {
		  colour = Colours.BLUE_1;
		  break;
	    }
	   
	    case Constants.BLOCK_SQUARE:
	    {
		  colour = Colours.LIGHT_GREY_1;
		  break;
	    }
	  
	    case Constants.GROUND_SQUARE:
	    {
		  colour = Colours.LIGHT_GREY_2;
		  break;
	    }
	  
	    case Constants.BLACK_HOLE_SQUARE:
	    {
		  colour = Colours.DARK_GREY_1;
		  break;
	    }
	  
	    case Constants.QUIZ_SQUARE:
	    {
		  colour =  Colours.YELLOW_1;
		  break;
	    }
	  
	    case Constants.FINISH_SQUARE:
	    {
		  colour = Colours.GREEN_2;
		  break;
	    }
	  
	    default:
	    {
		  colour = Color.WHITE;
		  break;
	    }
	  }
	}
	
	return colour;
  }
  
  public void flashSquares()
  {
	GenFlashCnt genFlashCnt;
	Timer timer = new Timer(true);
	TimerTask flashQuizSquare = new FlashQuizSquare(this,
			                                        quizGUI);
	genFlashCnt = new GenFlashCnt(); 

	timer.scheduleAtFixedRate(flashQuizSquare,
			                  0,
			                  Constants.FLASH_DELAY);	
  }
  
  public void victory()
  {
	WinState winState = new WinState();
	Timer timer = new Timer(true);
	TimerTask flashWin = new FlashWin(this);
	
	winState.buildWinMsg();
	
	timer.scheduleAtFixedRate(flashWin,
                              0,
                              Constants.WIN_FLASH_DELAY);	
  }
}
