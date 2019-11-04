package map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import common.Constants;

public class MapGUI extends JFrame implements KeyListener
{
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
		  //
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
		  
	    g.drawRect(xPos,
	    		   yPos,
	    		   blockWidth,
	    		   blockHeight);
	    
	    colour = getColourType(i,
	    		               j);
	    g.setColor(colour);
	  
	    if (colour.equals(Color.BLUE) || 
	    	colour.equals(new Color(255, 255, 80)) /* light yellow */ || 
	    	colour.equals(Color.BLACK) || 
	    	colour.equals(Color.GREEN))
	    {
	      g.fillRect(xPos, 
	    		     yPos, 
	    		     blockWidth, 
	    		     blockHeight);
	    }	   
	    
	    else
	    {
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
  
  private Color getColourType(int xPos, int yPos)
  {
	int squareType = 0;
	Color colour = null;
	
	squareType = MapState.mapConfig[xPos][yPos];
	
	switch(squareType)
	{
	  case Constants.PLAYER_SQUARE:
	  {
		colour = Color.BLUE;
		break;
	  }
	   
	  case Constants.BLOCK_SQUARE:
	  {
		colour = Color.GRAY;
		break;
	  }
	  
	  case Constants.GROUND_SQUARE:
	  {
		colour = Color.LIGHT_GRAY;
		break;
	  }
	  
	  case Constants.PERM_BLOCK_SQUARE:
	  {
		colour = Color.BLACK;
		break;
	  }
	  
	  case Constants.QUIZ_SQUARE:
	  {
		colour = new Color(255, 255, 80); /* light yellow */
		break;
	  }
	  
	  case Constants.FINISH_SQUARE:
	  {
		colour = Color.GREEN;
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
}
