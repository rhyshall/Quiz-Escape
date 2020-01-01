package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

import common.Colours;
import common.Constants;
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
	
  public MapGUI()
  {
	super();
	
	Colours.assemble();
	MapState.construct();
	trivia = new Trivia();
  }
	
  public void run()
  {
	setProperties();
	setVisible(true);
	
	saveBorderDim();
	saveBlockDim();
  }
  
  @Override
  public void keyTyped(KeyEvent e)
  {
	;
  }
  
  @Override
  public void keyPressed(KeyEvent e)
  {
	switch(e.getKeyCode())
	{
	  case KeyEvent.VK_UP:
	  {
		if (MapState.canMoveUp() == true)
		{
		  MapState.movePlayerUp();
		  repaint();
		}
	  }
	  
	  case KeyEvent.VK_RIGHT:
	  {
		if (MapState.canMoveRight() == true)
		{
		  MapState.movePlayerRight();
		  repaint();
	    }
	  }
		 
	  case KeyEvent.VK_DOWN:
	  {
		if (MapState.canMoveUp() == true)
	    { 
	      MapState.movePlayerDown();
	      repaint();
		}
	  }
	  
	  case KeyEvent.VK_LEFT:
	  {
		if (MapState.canMoveLeft() == true)
		{
		  MapState.movePlayerLeft();
		  repaint();
		}
	  }
	 
	  case KeyEvent.VK_ENTER:
	  { 
		if (MapState.isBesideQueston() == true)
		{
	      QuizSquares quizSquares = new QuizSquares();
	      FlashQuizSquare.quizSquares = quizSquares;
	      
		  GenQuizSquare targetSquare = new GenQuizSquare(quizSquares);
		  FlashQuizSquare.targetSquare = targetSquare;
		  
		  MapState.setActiveQuiz(targetSquare);
		  
		  flashSquares();
		}
		
		repaint();
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
	Color colour = null;
	
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
	
	return colour;
  }
  
  public void flashSquares()
  {
	Timer timer = new Timer(true);
	TimerTask flashQuizSquare = new FlashQuizSquare(this,
			                                        quizGUI);

	timer.scheduleAtFixedRate(flashQuizSquare,
			                  0,
			                  Constants.FLASH_DELAY);	
  }
  
  
}
