package common;

public class Constants 
{
  //start prompt
  public static final String START_PROMPT_TEXT = "Press Space Bar to Play"; /* title of welcome screen */
	
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
  public static final String TITLE = "Quiz Escape";
  
  //square types on game map
  public static final int PLAYER_SQUARE = 1; /* player's current position */
  public static final int BLOCK_SQUARE = 2; /* block- correct answer required to uncover */
  public static final int GROUND_SQUARE = 3; /* free area- block used to exist but was removed */
  public static final int BLACK_HOLE_SQUARE = 4; /* block- wrong answer was given so remains permanent */
  public static final int QUIZ_SQUARE = 5; /* block- player may uncover if next question answered correctly */
  public static final int FINISH_SQUARE = 6; /* finish line */
  
  //wait-time parameters
  public static final int FLASH_DELAY = 125; /* flash duration (in milliseconds) of each square when choosing next quiz square */
  public static final int QUIZ_LOAD_TIME = 1150; /* time-to-wait (in milliseconds) before quiz screen pops up */
  public static final int MAIN_LOAD_TIME = 1600; /* time-to-wait (in milliseconds) to transition from quiz screen to main screen */
  public static final int BLACK_HOLES_LOAD_TIME = 375; /* time-to-wait (in milliseconds) between space bar trigger and spawning black holes */
  public static final int QUIZ_SQUARE_LOAD_TIME = 1500; /* time-to-wait (in milliseconds) between spawning black holes and spawning quiz squares */
  public static final int SPAWN_BLACK_HOLES_DELAY = 200; /* delay between black hole spawns */
  public static final int WIN_FLASH_DELAY = 100; /* flash duration (in milliseconds) of each square when constructing win message */
  public static final int ENCORE_WIN_DELAY = 2750; /* time-to-wait (in milliseconds) before play-again screen pops up, after winning */
  public static final int ENCORE_LOSS_DELAY = 1150; /* time-to-wait (in milliseconds) before play-again screen pops up, after losing */
  public static final int WIN_MSG_ON_FINISH = 800; /* time-to-wait (in milliseconds) to display win message after finish block is reached */
  
  //number of answer choices for each question
  public static final int ANSWER_CHOICES_CNT = 4; 
  
  //question file parameters
  public static final String TRIVIA_FILE_PATH = "\\data\\quiz_set.tsv"; /* path to trivia question file from project directory */
  public static final String TRIVIA_FILE_DELIMITER = "\t"; /* delimiter of trivia question file */
  public static final int SUGG_MAX_QUEST = 2000; /* estimated value for number of questions in trivia file */
  
  //black hole constants
  public static final int MIN_BLACK_HOLE_CNT = 30; /* minimum start count of black hole obstacles */
  public static final int MAX_BLACK_HOLE_CNT = 34; /* maximum start count of black hole obstacles */
  
  //direction constants
  public static final int UP = 0; /* represents "up" direction */
  public static final int RIGHT = 1; /* represents "right" direction */
  public static final int DOWN = 2; /* represents "down" direction */
  public static final int LEFT = 3; /* represents "left" direction */
  public static final int DIRECTION_CNT = 4; /* number of direction constants */
  
  //win configuration file parameters
  public static final String WIN_FILE_PATH = "\\data\\winConfig.txt"; /* path to win message text file from project directory */
  public static final String WIN_FILE_DELIMITER = " "; /* delimiter of win message text file */
  public static final int WIN_MSG_BACKGROUND = 0; /* represents background square of win message */
  public static final int WIN_MSG_FOREGROUND = 1; /* represents text square of win message */
  
  //font configuration file parameters
  public static final String QUEST_FONT_FILE_PATH = "\\data\\questionFonts.txt"; /* path to question font configuration from project directory */
  public static final String ANSWER_FONT_FILE_PATH = "\\data\\answerFonts.txt"; /* path to answer font configuration from project directory */
  public static final String FONT_SIZE_DELIMITER = "|"; /* separates question size range from font size */
  public static final String TEXT_SIZE_DELIMITER = " "; /* separates text size lower bound from text size upper bound */
  
  public static final String WIN_TEXT = "Great Job! You Escaped!"; /* Displayed text when player wins */
  public static final String LOSE_TEXT = "You Lose! The Black Holes Surround You!"; /* Displayed text when player loses */
  
  public static final int ENCORE_SCREEN_WIDTH = 450; /* width of encore screen in pixels */
  public static final int ENCORE_SCREEN_HEIGHT = 450; /* height of encore screen in pixels */
  public static final int ENCORE_BUTTON_WIDTH = 150; /* width of play-again buttons */
  public static final int ENCORE_BUTTON_HEIGHT = 40; /* height of play-again buttons */
}
