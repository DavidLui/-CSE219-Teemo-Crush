/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TeemoCrush.ui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import TeemoCrush.data.DataModel;
import mini_game.MiniGame;
import static TeemoCrush.TeemoCrushConstants.*;
import mini_game.Sprite;
import mini_game.SpriteType;
import properties_manager.PropertiesManager;
import TeemoCrush.TeemoCrush.TeemoCrushPropertyType;
import TeemoCrush.file.FileManager;
import TeemoCrush.data.Record;
import TeemoCrush.events.PlayHandler;
import TeemoCrush.events.ExitHandler;
import TeemoCrush.events.ResetHandler;
import TeemoCrush.events.QuitHandler;
import TeemoCrush.events.ScrollUpHandler;
import TeemoCrush.events.ScrollDownHandler;
import TeemoCrush.events.SelectHandler;
import TeemoCrush.events.ReturnHandler;
import TeemoCrush.events.CloseLevelHandler;
import TeemoCrush.events.PlayLevelHandler;
import TeemoCrush.events.QuitLevelHandler;
import TeemoCrush.data.DataModel;
import static TeemoCrush.data.DataModel.shroom;
import TeemoCrush.ui.Panel;
import static TeemoCrush.data.DataModel.shroom2;
import static TeemoCrush.data.DataModel.shrooms;
import java.awt.Color;
import TeemoCrush.events.KeyHandler;
/**
 *
 * @author david
 */
public class TeemoCrushMiniGame extends MiniGame{
     // THE PLAYER RECORD FOR EACH LEVEL, WHICH LIVES BEYOND ONE SESSION
    private Record record;
    private Tile sagaMap;
    private DataModel model;
    // HANDLES ERROR CONDITIONS
    private MiniGame miniGame;
    private ErrorHandler errorHandler;
    private Panel panel;
    public static SelectHandler sh;
    public static SelectHandler sh1;
    public static SelectHandler sh2;
    public static SelectHandler sh3;
    public static SelectHandler sh4;
    public static SelectHandler sh5;
    public static SelectHandler sh6;public static SelectHandler sh7;public static SelectHandler sh8;public static SelectHandler sh9;public static SelectHandler sh10;
    // MANAGES LOADING OF LEVELS AND THE PLAYER RECORDS FILES
    private FileManager fileManager;public static SelectHandler sh99;
    
    // THE SCREEN CURRENTLY BEING PLAYED
    public static String currentScreenState;
    
    public Record getPlayerRecord() 
    { 
        return record; 
    }
    
        public ErrorHandler getErrorHandler()
    {
        return errorHandler;
    }

    public FileManager getFileManager()
    {
        return fileManager;
    }
    
    public boolean isCurrentScreenState(String testScreenState)
    {
        return testScreenState.equals(currentScreenState);
    }
    
        public void displayStats()
    {
        // MAKE SURE ONLY THE PROPER DIALOG IS VISIBLE
//        guiDialogs.get(WIN_DIALOG_TYPE).setState(INVISIBLE_STATE);
//        guiDialogs.get(LOSS_DIALOG_TYPE).setState(INVISIBLE_STATE);
//        guiDialogs.get(STATS_DIALOG_TYPE).setState(VISIBLE_STATE);
    }
    
    /**
     * This method forces the file manager to save the current player record.
     */
    public void savePlayerRecord()
    {
        //fileManager.saveRecord(record);
    }

        public void switchToSplashScreen()
    {
        // CHANGE THE BACKGROUND
        
        guiDecor.get(BACKGROUND_TYPE).setState(SPLASH_SCREEN_STATE);
        guiButtons.get(SHROOM_STAR_BUTTON_TYPE).setState(INVISIBLE_STATE);
        guiButtons.get(SHROOM_STAR_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(PLAY_AGAIN_TYPE).setEnabled(false);
        guiButtons.get(RETURN_SAGA_TYPE).setEnabled(false);
        guiButtons.get(QUIT_BUTTON_TYPE).setEnabled(true);
      
        // MAKE THE CURRENT SCREEN THE SPLASH SCREEN
        currentScreenState = SPLASH_SCREEN_STATE;
    }
        public void switchToSagaScreen()
                
    {
        ((DataModel)data).enableSagaMap(true);
        guiButtons.get(RETURN_SAGA_TYPE).setEnabled(false);
        guiButtons.get(PLAY_AGAIN_TYPE).setEnabled(false);
        guiButtons.put("LevelShroom",shroom);
        guiButtons.get("LevelShroom").setActionListener(sh);
        guiButtons.get("LevelShroom").setEnabled(true);
        
        guiButtons.put("LevelShroom2",shroom2);
        guiButtons.get("LevelShroom2").setActionListener(sh2);
        if(model.completedLevels[0] == false)
        guiButtons.get("LevelShroom2").setEnabled(false);
    
        
        for (int i =0;i < 8; i ++) {
        guiButtons.put("LevelShroom2" + i,shrooms[i]);
        if (model.completedLevels[i+1] == false)
        guiButtons.get("LevelShroom2" + i).setEnabled(false);
        }
                guiButtons.get("LevelShroom20").setActionListener(sh3);

                guiButtons.get("LevelShroom21").setActionListener(sh4);
                guiButtons.get("LevelShroom22").setActionListener(sh5);
                guiButtons.get("LevelShroom23").setActionListener(sh6);
                guiButtons.get("LevelShroom24").setActionListener(sh7);
                guiButtons.get("LevelShroom25").setActionListener(sh8);
                guiButtons.get("LevelShroom26").setActionListener(sh99);
                guiButtons.get("LevelShroom27").setActionListener(sh10);

        // CHANGE THE BACKGROUND
//        guiDecor.get(SAGA_MAP_TYPE).setState(SAGA_SCREEN_STATE);
            guiButtons.get(SHROOM_STAR_BUTTON_TYPE).setState(INVISIBLE_STATE);
        guiButtons.get(SHROOM_STAR_BUTTON_TYPE).setEnabled(false);
        guiDecor.get(BACKGROUND_TYPE).setState(SAGA_SCREEN_STATE);
        guiButtons.get(PLAY_BUTTON_TYPE).setState(INVISIBLE_STATE);
        guiButtons.get(PLAY_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(QUIT_BUTTON_TYPE).setState(INVISIBLE_STATE);
        guiButtons.get(QUIT_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(RESET_BUTTON_TYPE).setState(INVISIBLE_STATE);
        guiButtons.get(RESET_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SCROLL_UP_BUTTON_TYPE).setState(VISIBLE_STATE);
        guiButtons.get(SCROLL_UP_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(SCROLL_DOWN_BUTTON_TYPE).setState(VISIBLE_STATE);
        guiButtons.get(SCROLL_DOWN_BUTTON_TYPE).setEnabled(true);
        guiButtons.get(SAGA_QUIT_BUTTON_TYPE).setState(VISIBLE_STATE);
        guiButtons.get(SAGA_QUIT_BUTTON_TYPE).setEnabled(true);

        data.reset(this);
       currentScreenState = SAGA_SCREEN_STATE; 
               guiButtons.get(SHROOM_STAR_BUTTON_TYPE).setEnabled(false);
       guiButtons.get(SHROOM_STAR_BUTTON_TYPE).setState(INVISIBLE_STATE);
              guiButtons.get(PLAY_LEVEL_TYPE).setEnabled(false);
       guiButtons.get(PLAY_LEVEL_TYPE).setState(INVISIBLE_STATE);
                     guiButtons.get(RETURN_LEVEL_TYPE).setEnabled(false);
       guiButtons.get(RETURN_LEVEL_TYPE).setState(INVISIBLE_STATE);
                          guiButtons.get(QUIT_LEVEL_TYPE).setEnabled(false);
       guiButtons.get(QUIT_LEVEL_TYPE).setState(INVISIBLE_STATE);
        
          
    }
    public void switchToSelectScreen() {
       
        ((DataModel)data).enableSagaMap(false);
        currentScreenState = SELECT_SCREEN_STATE;
        guiButtons.get(SHROOM_STAR_BUTTON_TYPE).setState(VISIBLE_STATE);
          guiButtons.get(SCROLL_UP_BUTTON_TYPE).setState(INVISIBLE_STATE);
        guiButtons.get(SCROLL_UP_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SCROLL_DOWN_BUTTON_TYPE).setState(INVISIBLE_STATE);
        guiButtons.get(SCROLL_DOWN_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SAGA_QUIT_BUTTON_TYPE).setState(INVISIBLE_STATE);
        guiButtons.get(SAGA_QUIT_BUTTON_TYPE).setEnabled(false);
        guiDecor.get(BACKGROUND_TYPE).setState(SELECT_SCREEN_STATE);
//                guiButtons.get(SHROOM_BUTTON_TYPE).setState(INVISIBLE_STATE);
//        guiButtons.get(SHROOM_BUTTON_TYPE).setEnabled(false);
      //  guiButtons.get(SHROOM_STAR_BUTTON_TYPE).setState(VISIBLE_STATE);
              guiButtons.get(PLAY_LEVEL_TYPE).setEnabled(true);
       guiButtons.get(PLAY_LEVEL_TYPE).setState(VISIBLE_STATE);
       guiButtons.get(RETURN_LEVEL_TYPE).setEnabled(true);
       guiButtons.get(RETURN_LEVEL_TYPE).setState(VISIBLE_STATE);
       guiButtons.get(QUIT_LEVEL_TYPE).setEnabled(false);
       guiButtons.get(QUIT_LEVEL_TYPE).setState(INVISIBLE_STATE);
        
    }
        public void switchToGameScreen()
    {
        ((DataModel)data).enableSagaMap(false);
                guiButtons.get(SHROOM_STAR_BUTTON_TYPE).setState(INVISIBLE_STATE);

        // CHANGE THE BACKGROUND
        guiDecor.get(BACKGROUND_TYPE).setState(GAME_SCREEN_STATE);
        data.reset(this);

        guiButtons.get(SCROLL_UP_BUTTON_TYPE).setState(INVISIBLE_STATE);
        guiButtons.get(SCROLL_UP_BUTTON_TYPE).setEnabled(false);
        guiButtons.get(SCROLL_DOWN_BUTTON_TYPE).setState(INVISIBLE_STATE);
        guiButtons.get(SCROLL_DOWN_BUTTON_TYPE).setEnabled(false);
                
//                guiButtons.get(SHROOM_BUTTON_TYPE).setState(INVISIBLE_STATE);
//        guiButtons.get(SHROOM_BUTTON_TYPE).setEnabled(false);
       guiButtons.get(SHROOM_STAR_BUTTON_TYPE).setEnabled(false);

       guiButtons.get(PLAY_LEVEL_TYPE).setState(INVISIBLE_STATE);
       guiButtons.get(RETURN_LEVEL_TYPE).setEnabled(false);
       guiButtons.get(RETURN_LEVEL_TYPE).setState(INVISIBLE_STATE);
              guiButtons.get(QUIT_LEVEL_TYPE).setEnabled(true);
       guiButtons.get(QUIT_LEVEL_TYPE).setState(VISIBLE_STATE);
        // MAKE THE CURRENT SCREEN THE saga SCREEN
        
        currentScreenState = GAME_SCREEN_STATE;
    }
    public void switchToLossScreen() {
        
        
    }    
        
        
        
            public void updateBoundaries()
    {
        
    }
            
    @Override
    public void initData()
    {        
        errorHandler = new ErrorHandler(window);
        data = new DataModel(this);
                // INIT OUR FILE MANAGER
        //fileManager = new FileManager(this);
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        int gameWidth = Integer.parseInt(props.getProperty(TeemoCrushPropertyType.GAME_WIDTH.toString()));
        int gameHeight = Integer.parseInt(props.getProperty(TeemoCrushPropertyType.GAME_HEIGHT.toString()));
        data.setGameDimensions(gameWidth, gameHeight);
                // THIS WILL CHANGE WHEN WE LOAD A LEVEL
        boundaryLeft = Integer.parseInt(props.getProperty(TeemoCrushPropertyType.GAME_LEFT_OFFSET.toString()));
        boundaryTop = Integer.parseInt(props.getProperty(TeemoCrushPropertyType.GAME_TOP_OFFSET.toString()));
        boundaryRight = gameWidth - boundaryLeft;
        boundaryBottom = gameHeight;
        
    } 

    @Override
    public void updateGUI()
    {
        // GO THROUGH THE VISIBLE BUTTONS TO TRIGGER MOUSE OVERS
        Iterator<Sprite> buttonsIt = guiButtons.values().iterator();
        while (buttonsIt.hasNext())
        {
            Sprite button = buttonsIt.next();
            
            // ARE WE ENTERING A BUTTON?
            if (button.getState().equals(VISIBLE_STATE))
            {
                if (button.containsPoint(data.getLastMouseX(), data.getLastMouseY()))
                {
                    button.setState(MOUSE_OVER_STATE);
                }
            }
            // ARE WE EXITING A BUTTON?
            else if (button.getState().equals(MOUSE_OVER_STATE))
            {
                 if (!button.containsPoint(data.getLastMouseX(), data.getLastMouseY()))
                {
                    button.setState(VISIBLE_STATE);
                }
            }
        }
    }
        
    @Override
    public void reset()
    {
        data.reset(this);
    }
    
        @Override
    public void initGUIHandlers()
    {
        switchToSplashScreen();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String dataPath = props.getProperty(TeemoCrushPropertyType.DATA_PATH);
        
        // WE'LL HAVE A CUSTOM RESPONSE FOR WHEN THE USER CLOSES THE WINDOW
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ExitHandler eh = new ExitHandler(this);
        window.addWindowListener(eh);
        
        KeyHandler mkh = new KeyHandler(this);
        this.setKeyListener(mkh);
                // PLAY GAME EVENT HANDLER
        PlayHandler ngh = new PlayHandler(this);
        guiButtons.get(PLAY_BUTTON_TYPE).setActionListener(ngh);
        
        QuitHandler qh = new QuitHandler(this);
        guiButtons.get(SAGA_QUIT_BUTTON_TYPE).setActionListener(qh);
        
        ScrollUpHandler suh = new ScrollUpHandler(this);
        guiButtons.get(SCROLL_UP_BUTTON_TYPE).setActionListener(suh);
        
        ScrollDownHandler sdh = new ScrollDownHandler(this);
        guiButtons.get(SCROLL_DOWN_BUTTON_TYPE).setActionListener(sdh);
        
        QuitHandler qh2 = new QuitHandler(this);
        guiButtons.get(QUIT_BUTTON_TYPE).setActionListener(qh2);
        sh = new SelectHandler(this,1);
        sh2 = new SelectHandler(this,2);
        sh3 = new SelectHandler(this,3);
        sh4 = new SelectHandler(this,4);
        sh5 = new SelectHandler(this,5);
        sh6 = new SelectHandler(this,6);
        sh7 = new SelectHandler(this,7);
        sh8 = new SelectHandler(this,8);
        sh10 = new SelectHandler(this,10);
        sh9 = new SelectHandler(this,9);
        sh99 = new SelectHandler(this,9);
        
       
        
        ReturnHandler rh = new ReturnHandler(this);
        guiButtons.get(RETURN_LEVEL_TYPE).setActionListener(rh);
        ReturnHandler rh2 = new ReturnHandler(this);
        guiButtons.get(RETURN_SAGA_TYPE).setActionListener(rh2);
                PlayLevelHandler slh = new PlayLevelHandler(this, 8, 5,4 , 5);
        guiButtons.get(PLAY_LEVEL_TYPE).setActionListener(slh);
         PlayLevelHandler slh2 = new PlayLevelHandler(this, 8 , 5, 4,5);

        guiButtons.get(PLAY_AGAIN_TYPE).setActionListener(slh2);
        
        
        
       QuitLevelHandler qlh = new QuitLevelHandler(this);
        guiButtons.get(QUIT_LEVEL_TYPE).setActionListener(qlh);
    }
    
            @Override
    public void initGUIControls()
    {

        // WE'LL USE AND REUSE THESE FOR LOADING STUFF
        BufferedImage img;
        float x, y;
        SpriteType sT;
        Sprite s;
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imgPath = props.getProperty(TeemoCrushPropertyType.IMG_PATH);        
        String windowIconFile = props.getProperty(TeemoCrushPropertyType.WINDOW_ICON);
        img = loadImage(imgPath + windowIconFile);
        window.setIconImage(img);
        
        canvas = new Panel(this, (DataModel)data);
                

        
        
        
        // LOAD THE BACKGROUNDS, WHICH ARE GUI DECOR
        currentScreenState = SPLASH_SCREEN_STATE;
        img = loadImage(imgPath + props.getProperty(TeemoCrushPropertyType.SPLASH_SCREEN_IMAGE_NAME));
        sT = new SpriteType(BACKGROUND_TYPE);
        sT.addState(SPLASH_SCREEN_STATE, img);
        s = new Sprite(sT, 0, 0, 0, 0, SPLASH_SCREEN_STATE);
        guiDecor.put(BACKGROUND_TYPE,s);
        //gamescreen
        
        img = loadImage(imgPath + props.getProperty(TeemoCrushPropertyType.GAME_BACKGROUND_IMAGE_NAME));
        sT.addState(GAME_SCREEN_STATE, img);
        s = new Sprite(sT, 0, 0, 0, 0, GAME_SCREEN_STATE);
        guiDecor.put(BACKGROUND_TYPE, s);
        
        //select screen
        img = loadImage(imgPath + props.getProperty(TeemoCrushPropertyType.SELECT_SCREEN_IMAGE_NAME));
        sT.addState(SELECT_SCREEN_STATE, img);
        s = new Sprite(sT, 0, 0, 0, 0, SELECT_SCREEN_STATE);
        guiDecor.put(BACKGROUND_TYPE, s);
        
              

        ((DataModel) data).initSagaMap();

        
        //buttons
        String playButton = props.getProperty(TeemoCrushPropertyType.PLAY_BUTTON_IMAGE_NAME);
        sT = new SpriteType(PLAY_BUTTON_TYPE);
        img = loadImage(imgPath + playButton);
        sT.addState(VISIBLE_STATE, img);
        
        String PlayMouseOverButton = props.getProperty(TeemoCrushPropertyType.PLAY_BUTTON_MOUSE_OVER_IMAGE_NAME);
        img = loadImage(imgPath + PlayMouseOverButton);
        sT.addState(MOUSE_OVER_STATE, img);
        s = new Sprite(sT, PLAY_BUTTON_X, PLAY_BUTTON_Y, 0, 0, VISIBLE_STATE);
        guiButtons.put(PLAY_BUTTON_TYPE, s);
        
        
        String ResetButton = props.getProperty(TeemoCrushPropertyType.RESET_BUTTON_IMAGE_NAME);
        sT = new SpriteType(RESET_BUTTON_TYPE);
        img = loadImage(imgPath + ResetButton);
        sT.addState(VISIBLE_STATE, img);
        
        String ResetMouseOverButton = props.getProperty(TeemoCrushPropertyType.RESET_BUTTON_MOUSE_OVER_IMAGE_NAME);
        img = loadImage(imgPath + ResetMouseOverButton);
        sT.addState(MOUSE_OVER_STATE, img);
        s = new Sprite(sT, PLAY_BUTTON_X+250, PLAY_BUTTON_Y+5, 0, 0, VISIBLE_STATE);
        guiButtons.put(RESET_BUTTON_TYPE, s);
        

        
        String ScrollUpButton = props.getProperty(TeemoCrushPropertyType.SCROLL_UP_BUTTON_IMAGE_NAME);
        sT = new SpriteType(SCROLL_UP_BUTTON_TYPE);
        img = loadImage(imgPath + ScrollUpButton);
        sT.addState(VISIBLE_STATE, img);
        String ScrollUpMouseOverButton = props.getProperty(TeemoCrushPropertyType.SCROLL_UP_BUTTON_MOUSE_OVER_IMAGE_NAME);
        img = loadImage(imgPath + ScrollUpMouseOverButton);
        sT.addState(MOUSE_OVER_STATE, img);
        
        s = new Sprite(sT, 25, 250, 0, 0, INVISIBLE_STATE);
        guiButtons.put(SCROLL_UP_BUTTON_TYPE, s);
        
        String ScrollDownButton = props.getProperty(TeemoCrushPropertyType.SCROLL_DOWN_BUTTON_IMAGE_NAME);
        sT = new SpriteType(SCROLL_DOWN_BUTTON_TYPE);
        img = loadImage(imgPath + ScrollDownButton);
        sT.addState(VISIBLE_STATE, img);
        String ScrollDownMouseOverButton = props.getProperty(TeemoCrushPropertyType.SCROLL_DOWN_BUTTON_MOUSE_OVER_IMAGE_NAME);
        img = loadImage(imgPath + ScrollDownMouseOverButton);
        sT.addState(MOUSE_OVER_STATE, img);
        
        s = new Sprite(sT, 25, 400, 0, 0, INVISIBLE_STATE);
        guiButtons.put(SCROLL_DOWN_BUTTON_TYPE, s);
        
        
        String QuitButton = props.getProperty(TeemoCrushPropertyType.QUIT_BUTTON_IMAGE_NAME);
        sT = new SpriteType(QUIT_BUTTON_TYPE);
        img = loadImage(imgPath + QuitButton);
        sT.addState(VISIBLE_STATE, img);
        
        String QuitMouseOverButton = props.getProperty(TeemoCrushPropertyType.QUIT_BUTTON_MOUSE_OVER_IMAGE_NAME);
        img = loadImage(imgPath + QuitMouseOverButton);
        sT.addState(MOUSE_OVER_STATE, img);
        s = new Sprite(sT, PLAY_BUTTON_X+500, PLAY_BUTTON_Y-5, 0, 0, VISIBLE_STATE);
        guiButtons.put(QUIT_BUTTON_TYPE, s);
        
        String sagaQuitButton = props.getProperty(TeemoCrushPropertyType.EXIT_IMAGE_NAME);
        sT = new SpriteType(SAGA_QUIT_BUTTON_TYPE);
        img = loadImage(imgPath + sagaQuitButton);
        sT.addState(VISIBLE_STATE, img);
        
        String sagaQuitMouseOverButton = props.getProperty(TeemoCrushPropertyType.EXIT_MOUSE_OVER_IMAGE_NAME);
        img = loadImage(imgPath + sagaQuitMouseOverButton);
        sT.addState(MOUSE_OVER_STATE, img);
        s = new Sprite(sT, 25, 50, 0, 0, INVISIBLE_STATE);
        guiButtons.put(SAGA_QUIT_BUTTON_TYPE, s);
       
                
        String ShroomStar = props.getProperty(TeemoCrushPropertyType.SHROOM_STAR_IMAGE_NAME);
        sT = new SpriteType(SHROOM_STAR_BUTTON_TYPE);
        img = loadImage(imgPath + ShroomStar);
        sT.addState(VISIBLE_STATE, img);
        
        String ShroomStarMouseOverButton = props.getProperty(TeemoCrushPropertyType.SHROOM_STAR_MOUSE_OVER_IMAGE_NAME);
        img = loadImage(imgPath + ShroomStarMouseOverButton);
        sT.addState(MOUSE_OVER_STATE, img);
        s = new Sprite(sT, 50, 145, 0, 0, INVISIBLE_STATE);
        guiButtons.put(SHROOM_STAR_BUTTON_TYPE, s);
        
        String PlayLevel = props.getProperty(TeemoCrushPropertyType.PLAY_IMAGE_NAME);
        sT = new SpriteType(PLAY_LEVEL_TYPE);
        img = loadImage(imgPath + PlayLevel);
        sT.addState(VISIBLE_STATE, img);
        
        String PlayLevelMouseOver = props.getProperty(TeemoCrushPropertyType.PLAY_MOUSE_OVER_IMAGE_NAME);
        img = loadImage(imgPath + PlayLevelMouseOver);
        sT.addState(MOUSE_OVER_STATE, img);
        s = new Sprite(sT, 520, 60, 0, 0, INVISIBLE_STATE);
        guiButtons.put(PLAY_LEVEL_TYPE, s);
        
        String Return = props.getProperty(TeemoCrushPropertyType.RETURN_IMAGE_NAME);
        sT = new SpriteType(RETURN_LEVEL_TYPE);
        img = loadImage(imgPath + Return);
        sT.addState(VISIBLE_STATE, img);
        
        String ReturnMouseOver = props.getProperty(TeemoCrushPropertyType.RETURN_MOUSE_OVER_IMAGE_NAME);
        img = loadImage(imgPath + ReturnMouseOver);
        sT.addState(MOUSE_OVER_STATE, img);
        s = new Sprite(sT, 520, 140, 0, 0, INVISIBLE_STATE);
        guiButtons.put(RETURN_LEVEL_TYPE, s);
        
        String QuitLevel = props.getProperty(TeemoCrushPropertyType.QUIT_LEVEL_IMAGE_NAME);
        sT = new SpriteType(QUIT_LEVEL_TYPE);
        img = loadImage(imgPath + QuitLevel);
        sT.addState(VISIBLE_STATE, img);
        
        String QuitLevelMouseOver = props.getProperty(TeemoCrushPropertyType.QUIT_LEVEL_MOUSE_OVER_IMAGE_NAME);
        img = loadImage(imgPath + QuitLevelMouseOver);
        sT.addState(MOUSE_OVER_STATE, img);
        s = new Sprite(sT, 530, 580, 0, 0, INVISIBLE_STATE);
        guiButtons.put(QUIT_LEVEL_TYPE, s);
        
        String lossDisplay = props.getProperty(TeemoCrushPropertyType.LOSS_DIALOG_IMAGE_NAME);
        sT = new SpriteType(LOSS_DIALOG_TYPE);
        img = loadImageWithColorKey(imgPath + lossDisplay, new Color(255, 174, 201));
        sT.addState(VISIBLE_STATE, img);
        x = (data.getGameWidth()/2) - (img.getWidth(null)/2);
        y = (data.getGameHeight()/2) - (img.getHeight(null)/2);
        s = new Sprite(sT, x, y, 0, 0, INVISIBLE_STATE);
        guiDialogs.put(LOSS_DIALOG_TYPE, s);
        
        String winDisplay = props.getProperty(TeemoCrushPropertyType.WIN_DIALOG_IMAGE_NAME);
        sT = new SpriteType(WIN_DIALOG_TYPE);
        img = loadImageWithColorKey(imgPath + winDisplay, new Color(255, 174, 201));
        sT.addState(VISIBLE_STATE, img);
        x = (data.getGameWidth()/2) - (img.getWidth(null)/2);
        y = (data.getGameHeight()/2) - (img.getHeight(null)/2);
        s = new Sprite(sT, x, y, 0, 0, INVISIBLE_STATE);
        guiDialogs.put(WIN_DIALOG_TYPE, s);
        
        String playAgain = props.getProperty(TeemoCrushPropertyType.PLAY_AGAIN_IMAGE_NAME);
        sT = new SpriteType(PLAY_AGAIN_TYPE);
        img = loadImage(imgPath + playAgain);
        sT.addState(VISIBLE_STATE, img);
        
        String playAgainMouseOver = props.getProperty(TeemoCrushPropertyType.PLAY_AGAIN_MOUSE_OVER_IMAGE_NAME);
        img = loadImage(imgPath + playAgainMouseOver);
        sT.addState(MOUSE_OVER_STATE, img);
        s = new Sprite(sT, 80, 550, 0, 0, INVISIBLE_STATE);
        guiButtons.put(PLAY_AGAIN_TYPE, s);
        
        String returnSaga = props.getProperty(TeemoCrushPropertyType.RETURN_SAGA_IMAGE_NAME);
        sT = new SpriteType(RETURN_SAGA_TYPE);
        img = loadImage(imgPath + returnSaga);
        sT.addState(VISIBLE_STATE, img);
        
        String returnSagaMouseOver = props.getProperty(TeemoCrushPropertyType.RETURN_SAGA_MOUSE_OVER_IMAGE_NAME);
        img = loadImage(imgPath + returnSagaMouseOver);
        sT.addState(MOUSE_OVER_STATE, img);
        s = new Sprite(sT, 460, 550, 0, 0, INVISIBLE_STATE);
        guiButtons.put(RETURN_SAGA_TYPE, s);

    }
            
                @Override
    /**
     * Initializes the sound and music to be used by the application.
     */
    public void initAudioContent()
    {
                try
        {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String audioPath = props.getProperty(TeemoCrushPropertyType.AUDIO_PATH);

            // LOAD ALL THE AUDIO
            loadAudioCue(TeemoCrushPropertyType.SELECT_AUDIO_CUE);
            loadAudioCue(TeemoCrushPropertyType.MATCH_AUDIO_CUE);
            loadAudioCue(TeemoCrushPropertyType.NO_MATCH_AUDIO_CUE);
            loadAudioCue(TeemoCrushPropertyType.BLOCKED_TILE_AUDIO_CUE);
            loadAudioCue(TeemoCrushPropertyType.UNDO_AUDIO_CUE);
            loadAudioCue(TeemoCrushPropertyType.WIN_AUDIO_CUE);
            loadAudioCue(TeemoCrushPropertyType.LOSS_AUDIO_CUE);
            loadAudioCue(TeemoCrushPropertyType.SPLASH_SCREEN_SONG_CUE);
            loadAudioCue(TeemoCrushPropertyType.GAMEPLAY_SONG_CUE);

            // PLAY THE WELCOME SCREEN SONG
           // audio.play(TeemoCrushPropertyType.SPLASH_SCREEN_SONG_CUE.toString(), true);
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException | InvalidMidiDataException | MidiUnavailableException e)
        {
            errorHandler.processError(TeemoCrushPropertyType.AUDIO_FILE_ERROR);
        }        
    }
    private void loadAudioCue(TeemoCrushPropertyType audioCueType) 
            throws  UnsupportedAudioFileException, IOException, LineUnavailableException, 
                    InvalidMidiDataException, MidiUnavailableException
    {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String audioPath = props.getProperty(TeemoCrushPropertyType.AUDIO_PATH);
        String cue = props.getProperty(audioCueType.toString());
        //audio.loadAudio(audioCueType.toString(), audioPath + cue);        
    }

 
}
