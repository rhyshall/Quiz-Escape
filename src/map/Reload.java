package map;

public class Reload 
{
  public static void restartMapGUI(MapGUI mapGUI)
  {
    mapGUI.dispose();
	  
	MapGUI newMapGUI = new MapGUI();
	newMapGUI.run();
  }
}
