package map;

import java.util.TimerTask;

public class PaintMetronome extends TimerTask
{
  public MapGUI mapGUI;
  public static boolean stop;
	
  public PaintMetronome(MapGUI mapGUI)
  {
	this.mapGUI = mapGUI;
	stop = false;
  }
  
  public static void stop()
  {
	stop = true;
  }
  
  @Override
  public void run()
  {
	if (stop == true)
	{
	  cancel();
	  stop = false;
	}
	
	else
	{
	  mapGUI.repaint();
	}
  }
}