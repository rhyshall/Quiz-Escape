package common;

public class Constants 
{
  //game map dimensions
  public static final int MAP_WIDTH = 700; /* width of game map in pixels */
  public static final int MAP_HEIGHT = 700; /* height of game map in pixels */
  public static final int QUIZ_WIDTH = 700; /* width of quiz screen in pixels */
  public static final int QUIZ_HEIGHT = 700; /* height of quiz screen in pixels */
  public static final int QUESTION_WIDTH = 440; /* width of question text area in pixels */
  public static final int QUESTION_HEIGHT = 200; /* height of question text area in pixels */
  public static final int OPTION_WIDTH = 340; /* width of answer option in pixels */
  public static final int OPTION_HEIGHT = 50; /* height of answer option in pixels */
  
  //amount of squares on game map
  public static final int VERT_SQUARE_CNT = 12; /* height of map in squares */
  public static final int HOR_SQUARE_CNT = 11; /* width of map in squares */
  
  //title of application seen at top of window
  public static final String TITLE = "Steampunk Trivia";
  
  //square types on game map
  public static final int PLAYER_SQUARE = 1; /* player's current position */
  public static final int BLOCK_SQUARE = 2; /* block- correct answer required to uncover */
  public static final int GROUND_SQUARE = 3; /* free area- block used to exist but was removed */
  public static final int BLACK_HOLE_SQUARE = 4; /* block- wrong answer was given so remains permanent */
  public static final int QUIZ_SQUARE = 5; /* block- player may uncover if next question answered correctly */
  public static final int FINISH_SQUARE = 6; /* finish line */
  
  //wait-time parameters
  public static final int FLASH_DELAY = 150; /* flash duration (in milliseconds) of each square when choosing next quiz square */
  public static final int QUIZ_LOAD_TIME = 1250; /* time-to-wait (in milliseconds) before quiz screen pops up */
  public static final int MAIN_LOAD_TIME = 1700; /* time-to-wait (in milliseconds) to transition from quiz screen to main screen */
  
  //number of answer choices for each question
  public static final int ANSWER_CHOICES_CNT = 4; 
  
  //question file parameters
  public static final String TRIVIA_FILE_PATH = "\\data\\quiz_set.tsv"; /* path to trivia question file from project directory */
  public static final String TRIVIA_FILE_DELIMITER = "\t"; /* delimiter of trivia question file */
  public static final int SUGG_MAX_QUEST = 2000; /* estimated value for number of questions in trivia file */
}
