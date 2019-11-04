package common;

import map.MapGUI;
import map.MapState;

public class Main 
{
  public static void main(String args[])
  {
	MapState mapState = new MapState();
	
    MapGUI mapGUI = new MapGUI();
	mapGUI.run();
  }
}

