/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TeemoCrush;

import TeemoCrush.ui.TeemoCrushMiniGame;
import TeemoCrush.ui.ErrorHandler;
import xml_utilities.InvalidXMLFileFormatException;
import properties_manager.PropertiesManager;
import xml_utilities.InvalidXMLFileFormatException;
/**
 *
 * @author david
 */
public class TeemoCrush  {
        static TeemoCrushMiniGame miniGame = new TeemoCrushMiniGame();
        
        static String PROPERTY_TYPES_LIST = "property_types.txt";
        static String UI_PROPERTIES_FILE_NAME = "properties.xml";
        static String PROPERTIES_SCHEMA_FILE_NAME = "properties_schema.xsd";    
        static String DATA_PATH = "./data/";
        
    public static void main(String[] args) {
        try{
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        props.addProperty(TeemoCrushPropertyType.UI_PROPERTIES_FILE_NAME, UI_PROPERTIES_FILE_NAME);
        props.addProperty(TeemoCrushPropertyType.PROPERTIES_SCHEMA_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
        props.addProperty(TeemoCrushPropertyType.DATA_PATH.toString(), DATA_PATH);
        props.loadProperties(UI_PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
        
        String gameFlavorFile = props.getProperty(TeemoCrushPropertyType.GAME_FLAVOR_FILE_NAME);
        props.loadProperties(gameFlavorFile, PROPERTIES_SCHEMA_FILE_NAME);
        
        String appTitle = props.getProperty(TeemoCrushPropertyType.GAME_TITLE_TEXT);
        int fps = Integer.parseInt(props.getProperty(TeemoCrushPropertyType.FPS));
        miniGame.initMiniGame(appTitle, fps);
        miniGame.startGame();
        }
                catch(InvalidXMLFileFormatException ixmlffe)
        {
            // LET THE ERROR HANDLER PROVIDE THE RESPONSE
            ErrorHandler errorHandler = miniGame.getErrorHandler();
            errorHandler.processError(TeemoCrushPropertyType.INVALID_XML_FILE_ERROR_TEXT);
            
          
        }
    }
    
     public enum TeemoCrushPropertyType
    {
        UI_PROPERTIES_FILE_NAME,
        PROPERTIES_SCHEMA_FILE_NAME,
        GAME_FLAVOR_FILE_NAME,
        RECORD_FILE_NAME,
        
        AUDIO_PATH,
        DATA_PATH,
        IMG_PATH,
         
        WINDOW_ICON,
        
        
        /* WINDOW DIMENSIONS & FRAME RATE */
        WINDOW_WIDTH,
        WINDOW_HEIGHT,
        FPS,
        GAME_WIDTH,
        GAME_HEIGHT,
        GAME_LEFT_OFFSET,
        GAME_TOP_OFFSET,
        
        /* GAME TEXT */
        GAME_TITLE_TEXT,
        EXIT_REQUEST_TEXT,
        INVALID_XML_FILE_ERROR_TEXT,
        ERROR_DIALOG_TITLE_TEXT,
        
        /* ERROR TYPES */
        AUDIO_FILE_ERROR,
        LOAD_LEVEL_ERROR,
        RECORD_SAVE_ERROR,

        /* IMAGE FILE NAMES */
        PLAY_BUTTON_MOUSE_OVER_IMAGE_NAME,
        PLAY_BUTTON_IMAGE_NAME,
        QUIT_BUTTON_IMAGE_NAME,
        RESET_BUTTON_IMAGE_NAME,
        SHROOM_YELLOW_IMAGE_NAME,
        SHROOM_YELLOW_MOUSE_OVER_IMAGE_NAME,
        YELLOW_VERTICAL_IMAGE_NAME,
        YELLOW_VERTICAL_MOUSE_OVER_IMAGE_NAME,
                GREEN_VERTICAL_IMAGE_NAME,
        GREEN_VERTICAL_MOUSE_OVER_IMAGE_NAME,
                RED_VERTICAL_IMAGE_NAME,
        RED_VERTICAL_MOUSE_OVER_IMAGE_NAME,
                ORANGE_VERTICAL_IMAGE_NAME,
        ORANGE_VERTICAL_MOUSE_OVER_IMAGE_NAME,
                BLUE_VERTICAL_IMAGE_NAME,
        BLUE_VERTICAL_MOUSE_OVER_IMAGE_NAME,
                PURPLE_VERTICAL_IMAGE_NAME,
        PURPLE_VERTICAL_MOUSE_OVER_IMAGE_NAME,
                YELLOW_HORIZONTAL_IMAGE_NAME,
        YELLOW_HORIZONTAL_MOUSE_OVER_IMAGE_NAME,
                GREEN_HORIZONTAL_IMAGE_NAME,
        GREEN_HORIZONTAL_MOUSE_OVER_IMAGE_NAME,
                RED_HORIZONTAL_IMAGE_NAME,
        RED_HORIZONTAL_MOUSE_OVER_IMAGE_NAME,
                ORANGE_HORIZONTAL_IMAGE_NAME,
        ORANGE_HORIZONTAL_MOUSE_OVER_IMAGE_NAME,
                BLUE_HORIZONTAL_IMAGE_NAME,
        BLUE_HORIZONTAL_MOUSE_OVER_IMAGE_NAME,
                PURPLE_HORIZONTAL_IMAGE_NAME,
        PURPLE_HORIZONTAL_MOUSE_OVER_IMAGE_NAME,
        YELLOW_WRAPPER_IMAGE_NAME,
        YELLOW_WRAPPER_MOUSE_OVER_IMAGE_NAME,
        RED_WRAPPER_IMAGE_NAME,
        RED_WRAPPER_MOUSE_OVER_IMAGE_NAME,
        BLUE_WRAPPER_IMAGE_NAME,
        BLUE_WRAPPER_MOUSE_OVER_IMAGE_NAME,
        GREEN_WRAPPER_IMAGE_NAME,
        GREEN_WRAPPER_MOUSE_OVER_IMAGE_NAME,
        ORANGE_WRAPPER_IMAGE_NAME,
        ORANGE_WRAPPER_MOUSE_OVER_IMAGE_NAME,
        PURPLE_WRAPPER_IMAGE_NAME,
        PURPLE_WRAPPER_MOUSE_OVER_IMAGE_NAME,
        BLACK_BALL_IMAGE_NAME,
        BLACK_BALL_MOUSE_OVER_IMAGE_NAME,

        SHROOM_RED_IMAGE_NAME,
        SHROOM_BLUE_IMAGE_NAME,
        SHROOM_GREEN_IMAGE_NAME,
        SHROOM_PURPLE_IMAGE_NAME,
        SHROOM_ORANGE_IMAGE_NAME,
        SHROOM_RED_MOUSE_OVER_IMAGE_NAME,
        SHROOM_BLUE_MOUSE_OVER_IMAGE_NAME,
        SHROOM_GREEN_MOUSE_OVER_IMAGE_NAME,
        SHROOM_PURPLE_MOUSE_OVER_IMAGE_NAME,
        SHROOM_ORANGE_MOUSE_OVER_IMAGE_NAME,
        RESET_BUTTON_MOUSE_OVER_IMAGE_NAME,
        QUIT_BUTTON_MOUSE_OVER_IMAGE_NAME,
        EXIT_IMAGE_NAME,
        EXIT_MOUSE_OVER_IMAGE_NAME,
        SHROOM_IMAGE_NAME,
        SHROOM_MOUSE_OVER_IMAGE_NAME,
        SELECT_SCREEN_IMAGE_NAME,
        RETURN_IMAGE_NAME,
        QUIT_LEVEL_IMAGE_NAME,
        QUIT_LEVEL_MOUSE_OVER_IMAGE_NAME,
        RETURN_MOUSE_OVER_IMAGE_NAME,
        PLAY_IMAGE_NAME,
        PLAY_MOUSE_OVER_IMAGE_NAME,
        SHROOM_STAR_IMAGE_NAME,
        SHROOM_STAR_MOUSE_OVER_IMAGE_NAME,
        SCROLL_UP_BUTTON_IMAGE_NAME,
        SCROLL_DOWN_BUTTON_IMAGE_NAME,
         SCROLL_UP_BUTTON_MOUSE_OVER_IMAGE_NAME,
        SCROLL_DOWN_BUTTON_MOUSE_OVER_IMAGE_NAME,
        WINDOWS_ICON,
        SPLASH_SCREEN_IMAGE_NAME,
        GAME_BACKGROUND_IMAGE_NAME,
        SAGA_SCREEN_IMAGE_NAME,
        START_BUTTON_IMAGE_NAME,
        START_BUTTON_MOUSE_OVER_IMAGE_NAME,
        PLAY_AGAIN_IMAGE_NAME,
        PLAY_AGAIN_MOUSE_OVER_IMAGE_NAME,
        RETURN_SAGA_IMAGE_NAME,
        RETURN_SAGA_MOUSE_OVER_IMAGE_NAME,
        
                // AND THE DIALOGS
        STATS_DIALOG_IMAGE_NAME,
        WIN_DIALOG_IMAGE_NAME,
        LOSS_DIALOG_IMAGE_NAME,
        
                /* TILE LOADING STUFF */
        LEVEL_OPTIONS,
        LEVEL_IMAGE_OPTIONS,
        LEVEL_MOUSE_OVER_IMAGE_OPTIONS,
        TYPE_A_TILES,
        TYPE_B_TILES,
        TYPE_C_TILES,
        
                /* AUDIO CUES */
        SELECT_AUDIO_CUE,
        MATCH_AUDIO_CUE,
        NO_MATCH_AUDIO_CUE,
        BLOCKED_TILE_AUDIO_CUE,
        UNDO_AUDIO_CUE,
        WIN_AUDIO_CUE,
        LOSS_AUDIO_CUE,
        SPLASH_SCREEN_SONG_CUE,
        GAMEPLAY_SONG_CUE
    }
}
