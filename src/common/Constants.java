package common;

public class Constants 
{
  //game map dimensions
  public static final int MAP_WIDTH = 700; /* width of game map in pixels */
  public static final int MAP_HEIGHT = 700; /* height of game map in pixels */
  
  //amount of squares on game map
  public static final int VERT_SQUARE_CNT = 12; /* height of map in squares */
  public static final int HOR_SQUARE_CNT = 11; /* width of map in squares */
  
  //title of application seen at top of window
  public static final String TITLE = "Steampunk Trivia";
  
  //square types on game map
  public static final int PLAYER_SQUARE = 1; /* player's current position */
  public static final int BLOCK_SQUARE = 2; /* block- correct answer required to uncover */
  public static final int GROUND_SQUARE = 3; /* free area- block used to exist but was removed */
  public static final int PERM_BLOCK_SQUARE = 4; /* block- wrong answer was given so remains permanent */
  public static final int QUIZ_SQUARE = 5; /* block- player may uncover if next question answered correctly */
  public static final int FINISH_SQUARE = 6; /* finish line */
}
