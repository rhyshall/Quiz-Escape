package generator;

import java.awt.Color;
import java.util.Random;

public class GenColour 
{
  public Color colour;
	
  public GenColour()
  {
	int redAmt = 0;
	int greenAmt = 0;
	int blueAmt = 0;
	int upperBound = 155;
	Random randGen = new Random();	
	
	redAmt = randGen.nextInt(upperBound) + 35;
	greenAmt = randGen.nextInt(upperBound) + 99;
	blueAmt = randGen.nextInt(upperBound) + 99;
	
	colour = new Color(redAmt,
			           greenAmt,
			           blueAmt);
  }
}
