/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TeemoCrush;

/**
 *
 * @author david
 */
public class TeemoCrushConstants {
    
        // THIS REPRESENTS THE BUTTONS ON THE SPLASH SCREEN FOR LEVEL SELECTION
    public static final String LEVEL_SELECT_BUTTON_TYPE = "LEVEL_SELECT_BUTTON_TYPE";
    public static final String SAGA_MAP_TYPE = "SAGA_MAP_TYPE";
        // IN-GAME UI CONTROL TYPES
    
    public static final String QUIT_BUTTON_TYPE = "QUIT_BUTTON_TYPE";
    public static final String SAGA_QUIT_BUTTON_TYPE = "SAGA_QUIT_BUTTON_TYPE";
    public static final String SCROLL_UP_BUTTON_TYPE = "SCROLL_UP_BUTTON_TYPE";
    public static final String SCROLL_DOWN_BUTTON_TYPE = "SCROLL_DOWN_BUTTON_TYPE";
    public static final String PLAY_LEVEL_TYPE = "PLAY_LEVEL_TYPE";
    public static final String SHROOM_BUTTON_TYPE = "SHROOM_BUTTON_TYPE";
    public static final String SHROOM_STAR_BUTTON_TYPE = "SHROOM_STAR_BUTTON_TYPE";
        public static final String RETURN_LEVEL_TYPE = "RETURN_LEVEL_TYPE";
    // WE'LL USE THESE STATES TO CONTROL SWITCHING BETWEEN THE THREE
    public static final String SPLASH_SCREEN_STATE = "SPLASH_SCREEN_STATE";
     public static final String QUIT_LEVEL_TYPE = "QUIT_LEVEL_TYPE";
    public static final String SAGA_SCREEN_STATE = "SAGA_SCREEN_STATE";
    public static final String GAME_SCREEN_STATE = "GAME_SCREEN_STATE";    
    public static final String SELECT_SCREEN_STATE = "SELECT_SCREEN_STATE";    
    
    
    public static final String INVISIBLE_STATE = "INVISIBLE_STATE";
    public static final String VISIBLE_STATE = "VISIBLE_STATE";
    public static final String MOUSE_OVER_STATE = "MOUSE_OVER_STATE";
    
        // WE ONLY HAVE A LIMITIED NUMBER OF UI COMPONENT TYPES IN THIS APP
    
    // TILE SPRITE TYPES
    public static final String TILE_A_TYPE = "TILE_A_TYPE";
    public static final String TILE_B_TYPE = "TILE_B_TYPE";
    public static final String TILE_C_TYPE = "TILE_C_TYPE";
    public static final String TILE_SPRITE_TYPE_PREFIX = "TILE_";
    
    // EACH SCREEN HAS ITS OWN BACKGROUND TYPE
    public static final String BACKGROUND_TYPE = "BACKGROUND_TYPE";
    

    // IN-GAME UI CONTROL TYPES
    public static final String PLAY_BUTTON_TYPE = "PLAY_BUTTON_TYPE";
    public static final String RESET_BUTTON_TYPE = "RESET_BUTTON_TYPE";
     public static final String YELLOW_SHROOM_TYPE = "YELLOW_SHROOM_TYPE";
     public static final String YELLOW_VERTICAL_TYPE = "YELLOW_VERTICAL_TYPE";
      public static final String BLUE_VERTICAL_TYPE = "BLUE_VERTICAL_TYPE";
       public static final String GREEN_VERTICAL_TYPE = "GREEN_VERTICAL_TYPE";
        public static final String ORANGE_VERTICAL_TYPE = "ORANGE_VERTICAL_TYPE";
         public static final String RED_VERTICAL_TYPE = "RED_VERTICAL_TYPE";
          public static final String PURPLE_VERTICAL_TYPE = "PURPLE_VERTICAL_TYPE";
          public static final String YELLOW_HORIZONTAL_TYPE = "YELLOW_HORIZONTAL_TYPE";
      public static final String BLUE_HORIZONTAL_TYPE = "BLUE_HORIZONTAL_TYPE";
       public static final String GREEN_HORIZONTAL_TYPE = "GREEN_HORIZONTAL_TYPE";
        public static final String ORANGE_HORIZONTAL_TYPE = "ORANGE_HORIZONTAL_TYPE";
         public static final String RED_HORIZONTAL_TYPE = "RED_HORIZONTAL_TYPE";
          public static final String PURPLE_HORIZONTAL_TYPE = "PURPLE_HORIZONTAL_TYPE";
          public static final String YELLOW_WRAPPER_TYPE = "YELLOW_WRAPPER_TYPE";
          public static final String PURPLE_WRAPPER_TYPE = "PURPLE_WRAPPER_TYPE";
          public static final String BLUE_WRAPPER_TYPE = "BLUE_WRAPPER_TYPE";
          public static final String GREEN_WRAPPER_TYPE = "GREEN_WRAPPER_TYPE";
          public static final String ORANGE_WRAPPER_TYPE = "ORANGE_WRAPPER_TYPE";
          public static final String RED_WRAPPER_TYPE = "RED_WRAPPER_TYPE";
          public static final String BLACK_BALL_TYPE = "BLACK_BALL_TYPE";


     public static final String RED_SHROOM_TYPE = "RED_SHROOM_TYPE";
     public static final String ORANGE_SHROOM_TYPE = "ORANGE_SHROOM_TYPE";
     public static final String BLUE_SHROOM_TYPE = "BLUE_SHROOM_TYPE";
     public static final String GREEN_SHROOM_TYPE = "GREEN_SHROOM_TYPE";
     public static final String PURPLE_SHROOM_TYPE = "PURPLE_SHROOM_TYPE";
     
    public static final String TILE_COUNT_TYPE = "TILE_COUNT_TYPE";
    public static final String TIME_TYPE = "TIME_TYPE"; 
    public static final String STATS_BUTTON_TYPE = "STATS_BUTTON_TYPE";
    public static final String UNDO_BUTTON_TYPE = "UNDO_BUTTON_TYPE";
    public static final String TILE_STACK_TYPE = "TILE_STACK_TYPE";

    // DIALOG TYPES
    public static final String STATS_DIALOG_TYPE = "STATS_DIALOG_TYPE";
    public static final String WIN_DIALOG_TYPE = "WIN_DIALOG_TYPE";
    public static final String LOSS_DIALOG_TYPE = "LOSS_DIALOG_TYPE";
        public static final String PLAY_AGAIN_TYPE = "PLAY_AGAIN_TYPE";
        public static final String RETURN_SAGA_TYPE = "RETURN_SAGA_TYPE";

    // WE'LL USE THESE STATES TO CONTROL SWITCHING BETWEEN THE TWO

    // THE TILES MAY HAVE 4 STATES:
        // - INVISIBLE_STATE: USED WHEN ON THE SPLASH SCREEN, MEANS A TILE
            // IS NOT DRAWN AND CANNOT BE CLICKED
        // - VISIBLE_STATE: USED WHEN ON THE GAME SCREEN, MEANS A TILE
            // IS VISIBLE AND CAN BE CLICKED (TO SELECT IT), BUT IS NOT CURRENTLY SELECTED
        // - SELECTED_STATE: USED WHEN ON THE GAME SCREEN, MEANS A TILE
            // IS VISIBLE AND CAN BE CLICKED (TO UNSELECT IT), AND IS CURRENTLY SELECTED     
        // - NOT_AVAILABLE_STATE: USED FOR A TILE THE USER HAS CLICKED ON THAT
            // IS NOT FREE. THIS LET'S US GIVE THE USER SOME FEEDBACK

    // THE BUTTONS MAY HAVE 2 STATES:
        // - INVISIBLE_STATE: MEANS A BUTTON IS NOT DRAWN AND CAN'T BE CLICKED
        // - VISIBLE_STATE: MEANS A BUTTON IS DRAWN AND CAN BE CLICKED
        // - MOUSE_OVER_STATE: MEANS A BUTTON IS DRAWN WITH SOME HIGHLIGHTING
            // BECAUSE THE MOUSE IS HOVERING OVER THE BUTTON

    // UI CONTROL SIZE AND POSITION SETTINGS
    
    // OR POSITIONING THE LEVEL SELECT BUTTONS
    public static final int LEVEL_BUTTON_WIDTH = 200;
    public static final int LEVEL_BUTTON_MARGIN = 5;
    public static final int LEVEL_BUTTON_Y = 570;

    // FOR STACKING TILES ON THE GRID
    public static final int TILE_IMAGE_WIDTH = 60;
    public static final int TILE_IMAGE_HEIGHT = 60;

    // FOR MOVING TILES AROUND
    public static final int MAX_TILE_VELOCITY = 70;
    
    // UI CONTROLS POSITIONS IN THE GAME SCREEN
    public static final int CONTROLS_MARGIN = 0;
    public static final int PLAY_BUTTON_X = 50;
    public static final int PLAY_BUTTON_Y = 525;
    public static final int BACK_BUTTON_X = PLAY_BUTTON_X + 130 + CONTROLS_MARGIN;
    public static final int BACK_BUTTON_Y = 0;
    public static final int TILE_COUNT_X = BACK_BUTTON_X + 130 + CONTROLS_MARGIN;
    public static final int TILE_COUNT_Y = 0;
    public static final int TILE_COUNT_OFFSET = 145;
    public static final int TILE_TEXT_OFFSET = 60;
    public static final int TIME_X = TILE_COUNT_X + 232 + CONTROLS_MARGIN;
    public static final int TIME_Y = 0;
    public static final int TIME_OFFSET = 130;
    public static final int TIME_TEXT_OFFSET = 55;
    public static final int STATS_X = TIME_X + 310 + CONTROLS_MARGIN;
    public static final int STATS_Y = 0;
    public static final int UNDO_X = STATS_X + 159 + CONTROLS_MARGIN;
    public static final int UNDO_Y = 0;
    public static final int TILE_STACK_X = UNDO_X + 130 + CONTROLS_MARGIN;
    public static final int TILE_STACK_Y = 0;
    public static final int TILE_STACK_OFFSET_X = 30;
    public static final int TILE_STACK_OFFSET_Y = 12;
    public static final int TILE_STACK_2_OFFSET_X = 105;
    
    // STATS DIALOG COORDINATES
    public static final int STATS_LEVEL_INC_Y = 30;
    public static final int STATS_LEVEL_X = 460;
    public static final int STATS_LEVEL_Y = 300;
    public static final int STATS_GAMES_Y = STATS_LEVEL_Y + (STATS_LEVEL_INC_Y * 2);
    public static final int STATS_WINS_Y = STATS_GAMES_Y + STATS_LEVEL_INC_Y;
    public static final int STATS_LOSSES_Y = STATS_WINS_Y + STATS_LEVEL_INC_Y;
    public static final int STATS_WIN_PERC_Y = STATS_LOSSES_Y + STATS_LEVEL_INC_Y;
    public static final int STATS_FASTEST_Y = STATS_WIN_PERC_Y + STATS_LEVEL_INC_Y;
    
    // TEXT TO PUT INSIDE THE STATS DIALOG
    public static final String STATS_LEVEL_GAMES_TEXT = "Games: ";
    public static final String STATS_LEVEL_WINS_TEXT = "Wins: ";
    public static final String STATS_LEVEL_LOSSES_TEXT = "Losses: ";
    public static final String STATS_LEVEL_WIN_PERC_TEXT = "Win%: ";
    public static final String STATS_LEVEL_FASTEST_TEXT = "Fastest Win: ";
    
    // THESE ARE USED FOR FORMATTING THE TIME OF GAME
    public static final long MILLIS_IN_A_SECOND = 1000;
    public static final long MILLIS_IN_A_MINUTE = 1000 * 60;
    public static final long MILLIS_IN_AN_HOUR  = 1000 * 60 * 60;

    // USED FOR DOING OUR VICTORY ANIMATION
    public static final int WIN_PATH_NODES = 4;
    public static final int WIN_PATH_TOLERANCE = 100;
    public static final int WIN_PATH_COORD = 100;

    // COLORS USED FOR RENDERING VARIOUS THINGS, INCLUDING THE
    // COLOR KEY, WHICH REFERS TO THE COLOR TO IGNORE WHEN
    // LOADING ART.

    
    // AND AUDIO STUFF
    public static final String SUCCESS_AUDIO_TYPE = "SUCCESS_AUDIO_TYPE";
    public static final String FAILURE_AUDIO_TYPE = "FAILURE_AUDIO_TYPE";
    public static final String THEME_SONG_TYPE = "THEME_SONG_TYPE";
}
