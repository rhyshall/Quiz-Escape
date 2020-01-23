package map;

import java.util.TimerTask;

public class FlashWin extends TimerTask
{
  public MapGUI mapGUI;
  public static boolean stop;
	
  public FlashWin(MapGUI mapGUI)
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
	}
	
	else
	{
	  mapGUI.repaint();
	}
  }
}
