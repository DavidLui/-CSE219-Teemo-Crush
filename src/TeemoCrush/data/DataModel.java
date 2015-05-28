/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TeemoCrush.data;
import TeemoCrush.ui.Tile;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
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
import static TeemoCrush.data.DataModel.shroom2;
import TeemoCrush.ui.Panel;
import TeemoCrush.TeemoCrush.TeemoCrushPropertyType;
import mini_game.MiniGame;
import mini_game.MiniGameDataModel;
import mini_game.SpriteType;
import properties_manager.PropertiesManager;
import static TeemoCrush.TeemoCrushConstants.*;
import TeemoCrush.ui.TeemoCrushMiniGame;
import TeemoCrush.ui.Panel;
import TeemoCrush.events.SelectHandler;
import TeemoCrush.ui.TeemoCrushMiniGame;
import mini_game.Sprite;
import mini_game.MiniGame;
import java.awt.image.BufferedImage;
/**
 * 
 *
 * @author david
 */
public class DataModel extends MiniGameDataModel {
        // THIS CLASS HAS A REFERERENCE TO THE MINI GAME SO THAT IT
    // CAN NOTIFY IT TO UPDATE THE DISPLAY WHEN THE DATA MODEL CHANGES
    private MiniGame miniGame;
    public static Tile[][] tileGrid;
private Tile selectedTile;
public int secondCol;
public int secondRow;
public static double points= 0;
public static double goal;
public int waitingDelay;
private Tile secondSelectedTile;
private int selectRow;
int initDelay = 0;
public static boolean[] completedLevels;
public static int[] topScore;
public static int currentLevel = 0;
private int selectCol;
public Tile previousTile;
 boolean combo = false;
 int combomultiplier = 1;
private float[][] coordGridX;
private float[][] coordGridY;
public static String State = "waiting"; 
private int delay = 0;
    public static Tile sagaMap;
    public static Tile shroom;
    public static Tile shroom2;
    public static Tile shrooms[] = new Tile[8];
int swapDelay=0;
    public static int movesLeft;
    // THE LEVEL GRID REFERS TO THE LAYOUT FOR A GIVEN LEVEL, MEANING
    // HOW MANY TILES FIT INTO EACH CELL WHEN FIRST STARTING A LEVEL
    public static int[][] levelGrid;
    public static boolean comboing = false;
public int[] emptySpots;
    // LEVEL GRID DIMENSIONS
    public static int gridColumns;
    public static int gridRows;
    public TeemoCrushMiniGame game;
    public float[][] coords;
    public DataModel(MiniGame initMiniGame)
    {
        // KEEP THE GAME FOR LATER
        miniGame = initMiniGame;
        

    }
    
    @Override
    public void updateDebugText(MiniGame game)
    {
    }  
    
        @Override
    public void updateAll(MiniGame game)
    {
        try {
            game.beginUsingData();
            if (TeemoCrushMiniGame.currentScreenState == SAGA_SCREEN_STATE) {
            sagaMap.update(game);
            shroom.update(game);
            shroom2.update(game);
            
            for (int i = 0; i < 8; i++) {
                shrooms[i].update(game);
            }
            }
            if (TeemoCrushMiniGame.currentScreenState == GAME_SCREEN_STATE) {
                 if (State == "waiting" ) {
                
                if (points<goal && movesLeft <= 0) {
                    System.out.println("wut");
                endGameAsLoss();
            
                }
            
                 if (points>=goal) 
                endGameAsWin();
            }
                if (State == "swap") {
                delay = 0;
                initDelay += 1;
                if (initDelay>=10) {
                combo = check();
                initDelay = 0;
                if (combo == false && comboing == false) {
                     swap(previousTile, secondSelectedTile);
                    State = "waiting";
                    
                   
                    int holder = levelGrid[selectCol][selectRow];
                    levelGrid[selectCol][selectRow] = levelGrid[secondCol][secondRow];
                    levelGrid[secondCol][secondRow] = holder;
                    tileGrid[secondCol][secondRow] = secondSelectedTile;
                    tileGrid[selectCol][selectRow] = previousTile;
                }
                else {
                    if (comboing == false)
                    movesLeft -= 1;
                    State = "check";
                }
                
                }
            }
            else if (State == "check") {
                
                setInvis();  
            }
            else if (State == "invis") {
               delay = delay+1;
               if (delay >= 20) {
               setNull();
              
               delay = 0;
            
               }
            }
            else if (State == "null") {
                 delay = delay +1;
                if (delay >=5) {
                    pop();
                    delay = 0;
                     
                        State = "pop";
                        waitingDelay=0;
                }
                
            }
            else if (State == "pop") {
                delay += 1;
                if (delay >= 10) {
                    makeMoreTiles();
                    moveAllTiles();
                    
                    if (combo == true) {
                        State = "swap";
                        comboing = true;
                        combomultiplier+=1;
                    }
                    else {
                        State = "waiting";
                        comboing  = false;
                        combomultiplier = 1;
                    }
                }
                
            }

            
            if (tileGrid != null) {
            for (int i = 0; i <  gridRows; i++) {
                for (int j = 0; j < gridColumns; j++) {
                    if (tileGrid[j][i] != null)
                     tileGrid[j][i].update(game);
                }
            }
            }
            }
            
        }
        finally
        {
            game.endUsingData();
        }
        
    }
    @Override
    public void endGameAsLoss() {
        super.endGameAsLoss();
        miniGame.getGUIDialogs().get(LOSS_DIALOG_TYPE).setState(VISIBLE_STATE);
        miniGame.getGUIButtons().get(PLAY_AGAIN_TYPE).setState(VISIBLE_STATE);
        miniGame.getGUIButtons().get(PLAY_AGAIN_TYPE).setEnabled(true);
        miniGame.getGUIButtons().get(RETURN_SAGA_TYPE).setState(VISIBLE_STATE);
        miniGame.getGUIButtons().get(RETURN_SAGA_TYPE).setEnabled(true);
       miniGame.getGUIButtons().get(QUIT_LEVEL_TYPE).setEnabled(false);
    }
        @Override
    public void endGameAsWin() {
        super.endGameAsWin();
        points+=movesLeft*20;
        if (topScore[currentLevel-1] < (int)points)
        topScore[currentLevel-1] = (int)points;
               completedLevels[currentLevel-1] = true;

        miniGame.getGUIDialogs().get(WIN_DIALOG_TYPE).setState(VISIBLE_STATE);

        miniGame.getGUIButtons().get(RETURN_SAGA_TYPE).setState(VISIBLE_STATE);
        miniGame.getGUIButtons().get(RETURN_SAGA_TYPE).setEnabled(true);
       miniGame.getGUIButtons().get(QUIT_LEVEL_TYPE).setEnabled(false);
       
    }
    public void initSagaMap() {
        BufferedImage img;
        SpriteType sT;
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imgPath = props.getProperty(TeemoCrushPropertyType.IMG_PATH);        

        img = miniGame.loadImage(imgPath + props.getProperty(TeemoCrushPropertyType.SAGA_SCREEN_IMAGE_NAME));        
        sT = new SpriteType(SAGA_MAP_TYPE);
        sT.addState(INVISIBLE_STATE,img);
        ((Panel)(miniGame.getCanvas())).setMapImage(img);
        

        sagaMap = new Tile(sT, 0, 650-6000, 0, 0, INVISIBLE_STATE);
        
        
        img = miniGame.loadImage(imgPath + props.getProperty(TeemoCrushPropertyType.SHROOM_IMAGE_NAME));        
        sT = new SpriteType(SHROOM_BUTTON_TYPE);
        sT.addState(INVISIBLE_STATE,img);
        ((Panel)(miniGame.getCanvas())).setMapImage2(img);
        

        img = miniGame.loadImage(imgPath + props.getProperty(TeemoCrushPropertyType.SHROOM_MOUSE_OVER_IMAGE_NAME));        
                ((Panel)(miniGame.getCanvas())).setMapImage2mouseover(img);



//        sT.addState(MOUSE_OVER_STATE, img);
//        s= new Sprite(sT, 50, 50, 0, 0,VISIBLE_STATE);
//        
       
        shroom = new Tile(sT, 360, 460, 0, 0, INVISIBLE_STATE);
        shroom2 = new Tile(sT, 360, 190, 0, 0, INVISIBLE_STATE);
        shrooms[0] =new Tile(sT, 620, 15, 0, 0, INVISIBLE_STATE);
        shrooms[1] =new Tile(sT, 620, -190, 0, 0, INVISIBLE_STATE);
        shrooms[2] =new Tile(sT, 425, -350, 0, 0, INVISIBLE_STATE);
        shrooms[3] =new Tile(sT, 600, -620, 0, 0, INVISIBLE_STATE);
        shrooms[4] =new Tile(sT, 600, -800, 0, 0, INVISIBLE_STATE);
        shrooms[5] =new Tile(sT, 520, -990, 0, 0, INVISIBLE_STATE);
        shrooms[6] =new Tile(sT, 290, -990, 0, 0, INVISIBLE_STATE);
        shrooms[7] =new Tile(sT, 375, -1250, 0, 0, INVISIBLE_STATE);
        
        
    
        
    }
    @Override
    public void checkMouseReleaseOnSprites(MiniGame game, int x, int y){
                int col = calculateGridCellColumn(x);
        int row = calculateGridCellRow(y);
  
       if (TeemoCrushMiniGame.currentScreenState.matches(GAME_SCREEN_STATE) 
               && (row>=0) && row <= tileGrid[0].length-1 && (col <= tileGrid.length-1) && State == "waiting") {
             //            System.out.println(col);
           //System.out.println(row);
           //  GET AND TRY TO SELECT THE TOP TILE IN THAT CELL, IF THERE IS ONE
            Tile testTile = tileGrid[col][row];
          //  if (testTile.containsPoint(x, y))
                selectTile(testTile,col , row);
       }
        
    }
     @Override
    public void checkMousePressOnSprites(MiniGame game, int x, int y)
    {

        int col = calculateGridCellColumn(x);
        int row = calculateGridCellRow(y);
        
       if (TeemoCrushMiniGame.currentScreenState.matches(GAME_SCREEN_STATE) 
               && (row>=0) && row <= tileGrid[0].length-1 && (col <= tileGrid.length-1) && State == "waiting") {
  
            Tile testTile = tileGrid[col][row];
   
                selectTile(testTile,col , row);
       }
    }
        public void selectTile(Tile selectTile, int col, int row)
    {
        
        //if first tile highlight it
        if (selectedTile == null) {
            selectRow = row;
            selectCol = col;
           
            selectedTile = selectTile;
            selectedTile.setState("SELECTED");
            selectTile = null;
        }
        //if same tile
        else if (col == selectCol && row == selectRow) {
            selectedTile.setState(VISIBLE_STATE);
            selectedTile = null;
            selectTile = null;
        }
        //if adjacent tile then swap it and then start checking for matches
        else if (((((selectRow + 1 == row || selectRow - 1 == row) 
                && selectCol == col)) || ((selectCol + 1 == col || 
                selectCol -1 == col) && selectRow == row)) ) {
            secondSelectedTile = selectTile;
            secondCol = col;
            secondRow = row;
                    selectedTile.setState(VISIBLE_STATE);
                    swap(selectedTile, selectTile);
                    
                    swapDelay = 0;
//                  swap colors
                    int holder = levelGrid[col][row];
                    levelGrid[col][row] = levelGrid[selectCol][selectRow];
                    levelGrid[selectCol][selectRow] = holder;
                    tileGrid[selectCol][selectRow] = selectTile;
                    tileGrid[col][row] = selectedTile;
                    
                    State = "swap";
                    previousTile = selectedTile;
                    selectedTile = null;
                    selectTile = null;
                    
                }
        // = the new tile
            else {
                selectRow = row;
                selectCol = col;
                selectedTile.setState(VISIBLE_STATE);
                selectedTile = selectTile;
                selectedTile.setState("SELECTED");
                selectTile = null;
            }
    }
        //checks to see whether there are any combos. no combos will = false 
        public boolean check() {
            
            boolean jasper = false;
            boolean hooked = false;
            if (comboing == false) {
               
            if ((levelGrid[selectCol][selectRow] == 25 || levelGrid[secondCol][secondRow] == 25) ) {
                //delete all colors of the same color
                hooked = true; jasper = true;
                        if (levelGrid[selectCol][selectRow] == 25) {
                            int temp = levelGrid[secondCol][secondRow];
                            for (int i = 0; i < gridColumns; i++) {
                                for (int j = 0; j < gridRows; j++) {
                                if (levelGrid[i][j] == temp) {
                                    levelGrid[i][j] = 0;
                                }
                                }
                            }
                            levelGrid[selectCol][selectRow] = 0;
                        }
                        else if (levelGrid[secondCol][secondRow] == 25){
                          
                            int temp = levelGrid[selectCol][selectRow];
                       
                            for (int i = 0; i < gridColumns; i++) {
                                 for (int j = 0; j < gridRows; j++) {   
                                    if (levelGrid[i][j] == temp) {
                                        levelGrid[i][j] = 0; 
                                    }
                                 } 
                            }
                            levelGrid[secondCol][secondRow] = 0;
                        
                        }
                }
            
            
            }
            for (int i = 0; i < gridColumns; i++) {
                for (int j = 0; j < gridRows; j++) {
          
                //veritical explosion 3 in a row top down
                if (j >= 0 && j<gridRows-2 && ((levelGrid[i][j]-6 == levelGrid[i][j+1] 
                        && levelGrid[i][j+1] == levelGrid[i][j+2]) ||
                        (levelGrid[i][j] == levelGrid[i][j+1] -6
                        && levelGrid[i][j+1] -6 == levelGrid[i][j+2]) || 
                        (levelGrid[i][j] == levelGrid[i][j+1]
                        && levelGrid[i][j+1]  == levelGrid[i][j+2] -6))
                        && ((levelGrid[i][j] > 6 && levelGrid[i][j]<13) ||
                        (levelGrid[i][j+1] > 6 && levelGrid[i][j+1]<13) || 
                        (levelGrid[i][j+2] > 6 && levelGrid[i][j+2]<13))) {
                    hooked = true;
                    for (int m = 0; m <gridRows; m++) {
                        levelGrid[i][m] = 0;
                    }
                }
                //horizontal explosion 3 in a row top down
                if (j >= 0 && j<gridRows-2 && ((levelGrid[i][j]-12 == levelGrid[i][j+1] 
                        && levelGrid[i][j+1] == levelGrid[i][j+2]) ||
                        (levelGrid[i][j] == levelGrid[i][j+1] -12
                        && levelGrid[i][j+1] -12 == levelGrid[i][j+2]) || 
                        (levelGrid[i][j] == levelGrid[i][j+1]
                        && levelGrid[i][j+1]  == levelGrid[i][j+2] -12))
                        && ((levelGrid[i][j] > 12 && levelGrid[i][j]<19) ||
                        (levelGrid[i][j+1] > 12 && levelGrid[i][j+1]<19) || 
                        (levelGrid[i][j+2] > 12 && levelGrid[i][j+2]<19))) {
                    hooked = true;
                    if (levelGrid[i][j] > 12)
                    for (int m = 0; m <gridColumns; m++) {
                        levelGrid[m][j] = 0;
                    }
                    if (levelGrid[i][j+1] > 12)
                    for (int m = 0; m <gridColumns; m++) {
                        levelGrid[m][j+1] = 0;
                    }
                    if (levelGrid[i][j+2] > 12)
                    for (int m = 0; m <gridColumns; m++) {
                        levelGrid[m][j+2] = 0;
                    }
                    levelGrid[i][j] = 0;
                    levelGrid[i][j+1] = 0;
                    levelGrid[i][j+2] = 0;
                }
                 //vertical explosion 3 in a row left right
                if (i >= 0 && i<gridColumns-2 && ((levelGrid[i][j]-6 == levelGrid[i+1][j] 
                        && levelGrid[i+1][j] == levelGrid[i+2][j]) ||
                        (levelGrid[i][j] == levelGrid[i+1][j] -6
                        && levelGrid[i+1][j] -6 == levelGrid[i+2][j]) || 
                        (levelGrid[i][j] == levelGrid[i+1][j]
                        && levelGrid[i+1][j]  == levelGrid[i+2][j] -6))
                        && ((levelGrid[i][j] > 6 && levelGrid[i][j]<13) ||
                        (levelGrid[i+1][j] > 6 && levelGrid[i+1][j]<13) || 
                        (levelGrid[i+2][j] > 6 && levelGrid[i+2][j]<13))) {
                    hooked = true;
                    if (levelGrid[i][j]>6 && levelGrid[i][j]<13)
                    for (int m = 0; m <gridRows; m++) {
                        levelGrid[i][m] = 0;
                    }
                    if (levelGrid[i+1][j]>6&& levelGrid[i+1][j]<13)
                    for (int m = 0; m <gridRows; m++) {
                        levelGrid[i+1][m] = 0;
                    }
                    if (levelGrid[i+2][j]>6&& levelGrid[i+2][j]<13)
                    for (int m = 0; m <gridRows; m++) {
                        levelGrid[i+2][m] = 0;
                    }
                    levelGrid[i][j] = 0;
                                        levelGrid[i+1][j] = 0;
                                                            levelGrid[i+2][j] = 0;

                }
                                 //horizontal explosion 3 in a row left right
                if (i >= 0 && i<gridColumns-2 && ((levelGrid[i][j]-12 == levelGrid[i+1][j] 
                        && levelGrid[i+1][j] == levelGrid[i+2][j]) ||
                        (levelGrid[i][j] == levelGrid[i+1][j] -12
                        && levelGrid[i+1][j] -12 == levelGrid[i+2][j]) || 
                        (levelGrid[i][j] == levelGrid[i+1][j]
                        && levelGrid[i+1][j]  == levelGrid[i+2][j] -12))
                        && ((levelGrid[i][j] > 12 && levelGrid[i][j]<19) ||
                        (levelGrid[i+1][j] > 12 && levelGrid[i+1][j]<19) || 
                        (levelGrid[i+2][j] > 12 && levelGrid[i+2][j]<19))) {
                    hooked = true;
                  
                    for (int m = 0; m <gridColumns; m++) {
                        levelGrid[i][m] = 0;
                    }
                    
                }
                
                        
                        
                        
                //wrapper explosion
                if (levelGrid[i][j]>=19 && levelGrid[i][j]<25) {
                  
                    if (i == 0) {
                        if (j == 0 && ((levelGrid[i][j]-18 == levelGrid[i+1][j] && levelGrid[i+1][j]== levelGrid[i+2][j]) ||
                                (levelGrid[i][j]-18 == levelGrid[i][j+1] && levelGrid[i][j+1]== levelGrid[i][j+2]))) {
                                                  levelGrid[i+1][j] = 0; 
                            levelGrid[i][j+1] = 0;levelGrid[i+1][j+1] = 0;
                        }
                        else if (j == gridRows-1 && ((levelGrid[i][j]-18 == levelGrid[i][j-1] && levelGrid[i][j-1] == levelGrid[i][j-2]) 
                                || (levelGrid[i][j]-18 == levelGrid[i+1][j] && levelGrid[i+1][j] == levelGrid[i+2][j]) )) {
                            levelGrid[i][j-1] = 0; levelGrid[i+1][j-1] =0;
                                                    levelGrid[i+1][j]=0;
                        }
                        else {
                            if (levelGrid[i][j]-18 == levelGrid[i][j-1] && levelGrid[i][j-1] == levelGrid[i][j+1]){
                            levelGrid[i][j-1]=0;levelGrid[i+1][j-1] =0;
                                                levelGrid[i+1][j]=0;
                            levelGrid[i][j+1] =0;levelGrid[i+1][j+1]=0;}
                         
                        }
                    }
                    else if (i == gridColumns-1) {
                        if (j ==0 && ((levelGrid[i][j]-18 == levelGrid[i-1][j] && levelGrid[i-1][j] == levelGrid[i-2][j])||
                               (levelGrid[i][j]-18 == levelGrid[i][j+1] && levelGrid[i][j+1] == levelGrid[i][j+2])) ) {
                            levelGrid[i-1][j] = 0; 
                            levelGrid[i-1][j+1] = 0;levelGrid[i][j+1] =0;
                        }
                        else if (j == gridRows-1) {
                             if ((levelGrid[i][j]-18 == levelGrid[i][j-1] && levelGrid[i][j-1] == levelGrid[i][j-2]) ||
                                     (levelGrid[i][j]-18 == levelGrid[i-1][j] && levelGrid[i-1][j]== levelGrid[i-2][j]))
                            levelGrid[i-1][j-1] =0;levelGrid[i][j-1] =0;
                            levelGrid[i-1][j] =0;
                        }
                        else {
                            if (levelGrid[i][j]-18 == levelGrid[i][j+1] && levelGrid[i][j+1] == levelGrid[i][j-1]) {
                            levelGrid[i-1][j-1]=0;levelGrid[i][j-1]= 0; 
                            levelGrid[i-1][j]=0;
                            levelGrid[i-1][j+1]=0;levelGrid[i][j+1] =0;}
                            
                        }
                    }
                    else if (j == 0 && ((levelGrid[i][j]-18 == levelGrid[i-1][j] 
                            && levelGrid[i-1][j] == levelGrid[i+1][j]) || 
                            (levelGrid[i][j]-18 == levelGrid[i][j+1] && levelGrid[i][j+1]==levelGrid[i][j+2]))) {
                        levelGrid[i-1][j] = 0;                    levelGrid[i+1][j]=0;
                        levelGrid[i-1][j+1]=0;levelGrid[i][j+1]=0;levelGrid[i+1][j+1]=0;
                    }
                    else if (j == gridRows - 1 &&
                            ((levelGrid[i][j]-18 == levelGrid[i][j-1] && levelGrid[i][j-1] == levelGrid[i][j-2]) ||
                            (levelGrid[i][j]-18 == levelGrid[i-1][j] && levelGrid[i-1][j]== levelGrid[i+1][j]))) {
                        levelGrid[i-1][j-1]=0;levelGrid[i][j-1]=0;levelGrid[i+1][j-1]=0;
                        levelGrid[i-1][j]=0;                      levelGrid[i+1][j]=0;
                        
                    }
                    else {
                        if ((levelGrid[i][j]-18 == levelGrid[i][j-1] && levelGrid[i][j-1] == levelGrid[i][j+1]) ||
                             (levelGrid[i][j]-18 == levelGrid[i-1][j] && levelGrid[i-1][j] == levelGrid[i+1][j])   ){
                        levelGrid[i-1][j-1]=0;levelGrid[i][j-1]=0;levelGrid[i+1][j-1]=0;
                        levelGrid[i-1][j]=0;                       levelGrid[i+1][j]=0;
                        levelGrid[i-1][j+1]=0;levelGrid[i][j+1]=0;levelGrid[i+1][j+1]=0;
                        }
                        }
                    
                }
               //case where 5 right starting from col 0 to 3 on each row
                if (levelGrid[i][j] != 0 && i >= 0 && i < gridColumns-4 && levelGrid[i][j] == levelGrid[i+1][j] &&
                levelGrid[i+1][j] == levelGrid[i+2][j] && levelGrid[i+2][j] == levelGrid[i+3][j]
                  &&  levelGrid[i+3][j] == levelGrid[i+4][j]) {
                    
                    
                    for (int t = 0; t<5; t++) {
                        if (tileGrid[i+t][j] == previousTile ||tileGrid[i+t][j] == secondSelectedTile ) {
                            levelGrid[i+t][j] = 25;
                        }
                        else {
                            levelGrid[i+t][j] = 0;
                        }
                        
                    }
                    hooked = true;
                    
                    jasper = true;
                    points+=80*combomultiplier;
                }
                // 5 in a row 5 down from row 0
                else if (levelGrid[i][j] != 0 && j >= 0 && j < gridRows - 4  && levelGrid[i][j] == levelGrid[i][j+1] &&
                    levelGrid[i][j+1] == levelGrid[i][j+2] && levelGrid[i][j+2] == levelGrid[i][j+3]
                        && levelGrid[i][j+3] == levelGrid[i][j+4]) {

                    for (int t = 0; t<5; t++) {
                        if (tileGrid[i][j+t] == previousTile ||tileGrid[i][j+t] == secondSelectedTile ) {
                            levelGrid[i][j+t] = 25;
                        }
                        else {
                            levelGrid[i][j+t] = 0;
                        }
                       
                    }
          
                    jasper = true;
                    points += 80 * combomultiplier;
                    hooked = true;
                }
                //case where 4 right from col 0 to 4
                else if (levelGrid[i][j] != 0 && hooked == false && i >= 0 && i < gridColumns-3 && levelGrid[i][j] == levelGrid[i+1][j] &&
                    levelGrid[i+1][j] == levelGrid[i+2][j] && levelGrid[i+2][j] == levelGrid[i+3][j]) {

//                    levelGrid[i][j] = 0;
//                    levelGrid[i+1][j] = 0;
//                    levelGrid[i+2][j] = 0;
//                    levelGrid[i+3][j] = 0;
                    //special tile vertical
                    for (int t = 0; t<4; t++) {
                        if (tileGrid[i+t][j] == previousTile ||tileGrid[i+t][j] == secondSelectedTile ) {
                            levelGrid[i+t][j] = levelGrid[i+t][j] + 6;
                        }
                        else {
                            levelGrid[i+t][j] = 0;
                        }
                        
                    }
                    jasper = true;
                    hooked = true;
                                    points += 60* combomultiplier;

                }   
               // 4 in a row 4 down from row 0 to 1
                else if (levelGrid[i][j] != 0 && hooked == false && j >= 0 && j < gridRows-3 && levelGrid[i][j] == levelGrid[i][j+1] &&
                levelGrid[i][j+1] == levelGrid[i][j+2] && levelGrid[i][j+2] == levelGrid[i][j+3]) {
                  hooked = true;
//                levelGrid[i][j] = 0;
//                levelGrid[i][j+1] = 0;
//                levelGrid[i][j+2] = 0;
//                levelGrid[i][j+3] = 0;
                 for (int t = 0; t<4; t++) {
                     //case where special tile is created horizontal
                        if (tileGrid[i][j+t] == previousTile || tileGrid[i][j+t] == secondSelectedTile ) {
                            levelGrid[i][j+t] = levelGrid[i][j+t] + 12;
                        }
                        else {
                            levelGrid[i][j+t] = 0;
                        }
                        
                    }
                jasper = true;
                points += 60* combomultiplier;
            }
           
            //finding triples
            //case where 3 down from row 0 to 2
                else if (j >= 0 && j < gridRows - 2 && levelGrid[i][j] == levelGrid[i][j+1] &&
                levelGrid[i][j+1] == levelGrid[i][j+2]) {
                    //wrappers

                if (i < gridColumns-2) {//L's
                    
                        if (levelGrid[i][j] == levelGrid[i+1][j] && levelGrid[i+1][j] == levelGrid[i+2][j]) {
                            levelGrid[i][j] += 18;
                            
                            levelGrid[i][j+1] = 0; levelGrid[i+1][j] = 0; levelGrid[i+2][j]=0;
                            levelGrid[i][j+2] = 0;
                             jasper = true;                            hooked = true;

                        }
                     if (levelGrid[i][j+1] == levelGrid[i+1][j+1] && levelGrid[i+1][j+1] == levelGrid[i+2][j+1]) {
                            levelGrid[i][j+1] += 18;
                           
                            levelGrid[i][j+1] = 0; levelGrid[i+1][j] = 0; levelGrid[i+2][j]=0;
                            levelGrid[i][j+2] = 0;
                             jasper = true;                            hooked = true;

                        }
                    if (levelGrid[i][j+2] == levelGrid[i+1][j+2] && levelGrid[i+1][j+2] == levelGrid[i+2][j+2]) {
                    
                            levelGrid[i][j+2] += 18;
                               
                            levelGrid[i][j] = 0; levelGrid[i+1][j+2] = 0; levelGrid[i+2][j+2]=0;
                            levelGrid[i][j+1] = 0;
                             jasper = true;                            hooked = true;

                        }   
                 
                    

                }// T and cross
                else if (i < gridColumns - 1 && i > 0) {
                    
                    if (levelGrid[i][j] == levelGrid[i-1][j] && levelGrid[i][j] == levelGrid[i+1][j]) {
                            levelGrid[i][j] += 18;
                            hooked = true;
                             jasper = true;
                        }
                    if (levelGrid[i][j+1] == levelGrid[i-1][j+1] && levelGrid[i][j+1] == levelGrid[i+1][j+1]) {
                            levelGrid[i][j+1] += 18;
                            hooked = true;
                             jasper = true;
                        }
                    if (levelGrid[i][j+2] == levelGrid[i-1][j+2] && levelGrid[i][j+2] == levelGrid[i+1][j+2]
                            ) {
                            levelGrid[i][j+2] += 18; 
                            hooked = true;
                            jasper = true;
                        }   
                   

                }
                    
                if (hooked == false)  {
                    
                levelGrid[i][j] = 0;
                levelGrid[i][j+1] = 0;
                levelGrid[i][j+2] = 0;
                points += 60* combomultiplier;
                   hooked = true;
                jasper = true;
                }
            }

            
            //case where 3 right from col 0 to 5
                    
                else if (hooked == false && i >= 0 && i < gridColumns-2 && levelGrid[i][j] == levelGrid[i+1][j] &&
                levelGrid[i+1][j] == levelGrid[i+2][j]) {
                
                    if (j<gridRows-1 && levelGrid[i+2][j] == levelGrid[i+2][j+1] && levelGrid[i+2][j]== levelGrid[i+2][j+2]){
                        levelGrid[i+2][j] +=12;
                        levelGrid[i+2][j+1] =0; levelGrid[i+2][j+2]= 0;
                                                hooked = true;
               points += 60* combomultiplier;
                levelGrid[i][j] = 0;
                levelGrid[i+1][j] = 0;
                   
                jasper = true;    
                    }
                        
                    else {
                    
                    hooked = true;
               points += 60* combomultiplier;
                levelGrid[i][j] = 0;
                levelGrid[i+1][j] = 0;
                levelGrid[i+2][j] = 0;
                   
                jasper = true;
                    }
            }
            
                
                hooked = false;
            }
        }
            
            
            
            State = "check";

            return jasper;
            
            
        }

        
        // This is where we find all the 0's in levelgrid, destroy their image,
        // then replace the values with the tile above it and move the tile image to the
//        position. If there are no tiles, we leave it and generate new tiles to fill in the
//        gap
        public void setInvis() {
            boolean anything = false;
                emptySpots = new int[gridColumns];
                for (int i = 0; i < gridColumns; i++){
                    emptySpots[i] = 0;
                for (int j = gridRows - 1; j >= 0; j--){
                    
//                    coordGridX[i][j] = tileGrid[i][j].getX();
//                    coordGridY[i][j] = tileGrid[i][j].getY();
                    if (levelGrid[i][j] == 0 ) {
                      //  tileGrid[i][j].setState(INVISIBLE_STATE);
//                        tileGrid[i][j] = null;
                        //tileGrid[i][j].update(game);
                        emptySpots[i] +=1;
                        tileGrid[i][j].setTarget(0, 0);
                        tileGrid[i][j].startMovingToTarget(35);
                        
                        anything = true;
                       
                    }
                }
                
            }

                        State = "invis";
     
        }
        public void setNull(){
        BufferedImage img;
        float x, y;
        SpriteType sT;
        Sprite s;
       
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imgPath = props.getProperty(TeemoCrushPropertyType.IMG_PATH);  
                for (int i = 0; i < gridColumns; i++){
                for (int j = gridRows - 1; j >= 0; j--){
                    
//                    coordGridX[i][j] = tileGrid[i][j].getX();
//                    coordGridY[i][j] = tileGrid[i][j].getY();
                    if (levelGrid[i][j] == 0) {
                      
                        tileGrid[i][j].setX(0);
                        tileGrid[i][j].setY(0);
                        tileGrid[i][j].setState(INVISIBLE_STATE);
                    }
                    if (levelGrid[i][j] == 7 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String ShroomYellowVertical = props.getProperty(TeemoCrushPropertyType.YELLOW_VERTICAL_IMAGE_NAME);
                            sT = new SpriteType(YELLOW_VERTICAL_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomYellowVertical);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomYellowVerticalMouseOver = props.getProperty(TeemoCrushPropertyType.YELLOW_VERTICAL_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomYellowVerticalMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                    }
                    else if (levelGrid[i][j] == 8 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String ShroomRedVertical = props.getProperty(TeemoCrushPropertyType.RED_VERTICAL_IMAGE_NAME);
                            sT = new SpriteType(RED_VERTICAL_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomRedVertical);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomRedVerticalMouseOver = props.getProperty(TeemoCrushPropertyType.RED_VERTICAL_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomRedVerticalMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                    }
                            else if (levelGrid[i][j] == 9 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String ShroomOrangeVertical = props.getProperty(TeemoCrushPropertyType.ORANGE_VERTICAL_IMAGE_NAME);
                            sT = new SpriteType(ORANGE_VERTICAL_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomOrangeVertical);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomOrangeVerticalMouseOver = props.getProperty(TeemoCrushPropertyType.ORANGE_VERTICAL_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomOrangeVerticalMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                    }
                            else if (levelGrid[i][j] == 10 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String ShroomGreenVertical = props.getProperty(TeemoCrushPropertyType.GREEN_VERTICAL_IMAGE_NAME);
                            sT = new SpriteType(GREEN_VERTICAL_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomGreenVertical);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomGreenVerticalMouseOver = props.getProperty(TeemoCrushPropertyType.GREEN_VERTICAL_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomGreenVerticalMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                    }
                            else if (levelGrid[i][j] == 11 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String ShroomPurpleVertical = props.getProperty(TeemoCrushPropertyType.PURPLE_VERTICAL_IMAGE_NAME);
                            sT = new SpriteType(PURPLE_VERTICAL_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomPurpleVertical);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomPurpleVerticalMouseOver = props.getProperty(TeemoCrushPropertyType.PURPLE_VERTICAL_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomPurpleVerticalMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                    }
                            else if (levelGrid[i][j] == 12 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String ShroomBlueVertical = props.getProperty(TeemoCrushPropertyType.BLUE_VERTICAL_IMAGE_NAME);
                            sT = new SpriteType(BLUE_VERTICAL_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomBlueVertical);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomBlueVerticalMouseOver = props.getProperty(TeemoCrushPropertyType.BLUE_VERTICAL_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomBlueVerticalMouseOver);
                             sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                    }
                            else if (levelGrid[i][j] == 13 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String ShroomYellowHorizontal = props.getProperty(TeemoCrushPropertyType.YELLOW_HORIZONTAL_IMAGE_NAME);
                            sT = new SpriteType(YELLOW_HORIZONTAL_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomYellowHorizontal);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomYellowHorizontalMouseOver = props.getProperty(TeemoCrushPropertyType.YELLOW_HORIZONTAL_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomYellowHorizontalMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                    }
                           else if (levelGrid[i][j] == 14 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String ShroomRedHorizontal = props.getProperty(TeemoCrushPropertyType.RED_HORIZONTAL_IMAGE_NAME);
                            sT = new SpriteType(RED_HORIZONTAL_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomRedHorizontal);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomRedHorizontalMouseOver = props.getProperty(TeemoCrushPropertyType.RED_HORIZONTAL_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomRedHorizontalMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");




                }
                           else if (levelGrid[i][j] == 15 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String ShroomOrangeHorizontal = props.getProperty(TeemoCrushPropertyType.ORANGE_HORIZONTAL_IMAGE_NAME);
                            sT = new SpriteType(ORANGE_HORIZONTAL_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomOrangeHorizontal);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomOrangeHorizontalMouseOver = props.getProperty(TeemoCrushPropertyType.ORANGE_HORIZONTAL_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomOrangeHorizontalMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");

                
            }
                           else if (levelGrid[i][j] == 16 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String ShroomGreenHorizontal = props.getProperty(TeemoCrushPropertyType.GREEN_HORIZONTAL_IMAGE_NAME);
                            sT = new SpriteType(GREEN_HORIZONTAL_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomGreenHorizontal);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomGreenHorizontalMouseOver = props.getProperty(TeemoCrushPropertyType.GREEN_HORIZONTAL_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomGreenHorizontalMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");

                    
                }
                           else if (levelGrid[i][j] == 17 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String ShroomPurpleHorizontal = props.getProperty(TeemoCrushPropertyType.PURPLE_HORIZONTAL_IMAGE_NAME);
                            sT = new SpriteType(PURPLE_HORIZONTAL_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomPurpleHorizontal);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomPurpleHorizontalMouseOver = props.getProperty(TeemoCrushPropertyType.PURPLE_HORIZONTAL_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomPurpleHorizontalMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");

                }
                           else if (levelGrid[i][j] == 18 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String ShroomBlueHorizontal = props.getProperty(TeemoCrushPropertyType.BLUE_HORIZONTAL_IMAGE_NAME);
                            sT = new SpriteType(BLUE_HORIZONTAL_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomBlueHorizontal);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomBlueHorizontalMouseOver = props.getProperty(TeemoCrushPropertyType.BLUE_HORIZONTAL_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomBlueHorizontalMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                           }
                           else if (levelGrid[i][j] == 19 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String YellowWrapper = props.getProperty(TeemoCrushPropertyType.YELLOW_WRAPPER_IMAGE_NAME);
                            sT = new SpriteType(YELLOW_WRAPPER_TYPE);
                            img = miniGame.loadImage(imgPath + YellowWrapper);
                            sT.addState(VISIBLE_STATE, img);

                            String YellowWrapperMouseOver = props.getProperty(TeemoCrushPropertyType.YELLOW_WRAPPER_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + YellowWrapperMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                           }
                          else if (levelGrid[i][j] == 20 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String RedWrapper = props.getProperty(TeemoCrushPropertyType.RED_WRAPPER_IMAGE_NAME);
                            sT = new SpriteType(RED_WRAPPER_TYPE);
                            img = miniGame.loadImage(imgPath + RedWrapper);
                            sT.addState(VISIBLE_STATE, img);

                            String RedWrapperMouseOver = props.getProperty(TeemoCrushPropertyType.RED_WRAPPER_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + RedWrapperMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                           }
                          else if (levelGrid[i][j] == 21 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String OrangeWrapper = props.getProperty(TeemoCrushPropertyType.ORANGE_WRAPPER_IMAGE_NAME);
                            sT = new SpriteType(ORANGE_WRAPPER_TYPE);
                            img = miniGame.loadImage(imgPath + OrangeWrapper);
                            sT.addState(VISIBLE_STATE, img);

                            String OrangeWrapperMouseOver = props.getProperty(TeemoCrushPropertyType.ORANGE_WRAPPER_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + OrangeWrapperMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                           }
                         else if (levelGrid[i][j] == 22 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String GreenWrapper = props.getProperty(TeemoCrushPropertyType.GREEN_WRAPPER_IMAGE_NAME);
                            sT = new SpriteType(GREEN_WRAPPER_TYPE);
                            img = miniGame.loadImage(imgPath + GreenWrapper);
                            sT.addState(VISIBLE_STATE, img);

                            String GreenWrapperMouseOver = props.getProperty(TeemoCrushPropertyType.GREEN_WRAPPER_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + GreenWrapperMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                           }
                          else if (levelGrid[i][j] == 23 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String PurpleWrapper = props.getProperty(TeemoCrushPropertyType.PURPLE_WRAPPER_IMAGE_NAME);
                            sT = new SpriteType(PURPLE_WRAPPER_TYPE);
                            img = miniGame.loadImage(imgPath + PurpleWrapper);
                            sT.addState(VISIBLE_STATE, img);

                            String PurpleWrapperMouseOver = props.getProperty(TeemoCrushPropertyType.PURPLE_WRAPPER_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + PurpleWrapperMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                           }
                          else if (levelGrid[i][j] == 24 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String BlueWrapper = props.getProperty(TeemoCrushPropertyType.BLUE_WRAPPER_IMAGE_NAME);
                            sT = new SpriteType(BLUE_WRAPPER_TYPE);
                            img = miniGame.loadImage(imgPath + BlueWrapper);
                            sT.addState(VISIBLE_STATE, img);

                            String BlueWrapperMouseOver = props.getProperty(TeemoCrushPropertyType.BLUE_WRAPPER_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + BlueWrapperMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                           }
                            else if (levelGrid[i][j] == 25 ) {
                            tileGrid[i][j].setState(INVISIBLE_STATE);
                         
                            String blackball = props.getProperty(TeemoCrushPropertyType.BLACK_BALL_IMAGE_NAME);
                            sT = new SpriteType(BLACK_BALL_TYPE);
                            img = miniGame.loadImage(imgPath + blackball);
                            sT.addState(VISIBLE_STATE, img);

                            String BlackBallMouseOver = props.getProperty(TeemoCrushPropertyType.BLACK_BALL_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + BlackBallMouseOver);
                            sT.addState("SELECTED", img);
                           
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                           }


                }
                }
                        State = "null";
        }
        public void pop() {
            //loop to find all the 0's and set them invisible
            //loop to find all NOT 0's on each column starting from the lowest row
            //move them to the tileGrid[i][4] location and decrement up
            //set tileGrid location = to the NOT 0 tile grid location
            //set old tileGrid to null
            //set levelGrid to new levelGrid
            //set old levelGrid to -1 (representing empty cell)
            
            
            //new loop to find all = -1 and generate random integer from 1-6 for levelGrid cells
            //
            int counta = 0;
            for (int i = 0; i < gridColumns; i++){
               int k = gridRows - 1; // marker to start at bottom of the row
               counta = 0;
                for (int j = gridRows - 1; j >= 0; j--){
                    if ((levelGrid[i][j] > 0 ) && tileGrid[i][j].getState() != INVISIBLE_STATE) {
                     
                        tileGrid[i][j].setTarget(coordGridX[i][k], coordGridY[i][k]);
                        tileGrid[i][j].startMovingToTarget(2);
                        levelGrid[i][k] = levelGrid[i][j];
                        
                        tileGrid[i][k] = tileGrid[i][j];
//                      
                        k = k-1;
                    }
                }

                for (int w =0; w < emptySpots[i]; w++) {
                    levelGrid[i][w] = -1;
                }
            }
             State = "pop";   
        

            
        }
        public void makeMoreTiles() {
        BufferedImage img;
        float x, y;
        SpriteType sT;
        Sprite s;
       
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imgPath = props.getProperty(TeemoCrushPropertyType.IMG_PATH);     
          for (int i = 0; i < gridColumns; i++){
               
              
                for (int j = gridRows - 1; j >= 0; j--){
                        if (levelGrid[i][j] == -1) {
                            levelGrid[i][j] = ((int) (Math.random() *6)) + 1;
                        
                        if (levelGrid[i][j] == 1) {
                            
                            String ShroomYellow = props.getProperty(TeemoCrushPropertyType.SHROOM_YELLOW_IMAGE_NAME);
                            sT = new SpriteType(YELLOW_SHROOM_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomYellow);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomYellowMouseOver = props.getProperty(TeemoCrushPropertyType.SHROOM_YELLOW_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomYellowMouseOver);
                            sT.addState("SELECTED", img);
                           // s = new Sprite(sT, i*85, j*80, 0, 0, VISIBLE_STATE);
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                           
                }
                        if (levelGrid[i][j] == 2) {
                    String ShroomRed = props.getProperty(TeemoCrushPropertyType.SHROOM_RED_IMAGE_NAME);
                            sT = new SpriteType(RED_SHROOM_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomRed);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomRedMouseOver = props.getProperty(TeemoCrushPropertyType.SHROOM_RED_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomRedMouseOver);
                            sT.addState("SELECTED", img);
                            s = new Sprite(sT, i*85, j*80, 0, 0, VISIBLE_STATE);
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                }
                                if (levelGrid[i][j] == 3) {
                    String ShroomOrange = props.getProperty(TeemoCrushPropertyType.SHROOM_ORANGE_IMAGE_NAME);
                            sT = new SpriteType(ORANGE_SHROOM_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomOrange);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomOrangeMouseOver = props.getProperty(TeemoCrushPropertyType.SHROOM_ORANGE_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomOrangeMouseOver);
                            sT.addState("SELECTED", img);
                            s = new Sprite(sT, i*85, j*80, 0, 0, VISIBLE_STATE);
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                            
                }
                
                                 if (levelGrid[i][j] == 4) {
                    String ShroomGreen = props.getProperty(TeemoCrushPropertyType.SHROOM_GREEN_IMAGE_NAME);
                            sT = new SpriteType(GREEN_SHROOM_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomGreen);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomGreenMouseOver = props.getProperty(TeemoCrushPropertyType.SHROOM_GREEN_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomGreenMouseOver);
                            sT.addState("SELECTED", img);
                            s = new Sprite(sT, i*85, j*80, 0, 0, VISIBLE_STATE);
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                
            }
                                  if (levelGrid[i][j] == 5) {
                    String ShroomPurple = props.getProperty(TeemoCrushPropertyType.SHROOM_PURPLE_IMAGE_NAME);
                            sT = new SpriteType(PURPLE_SHROOM_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomPurple);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomPurpleMouseOver = props.getProperty(TeemoCrushPropertyType.SHROOM_PURPLE_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomPurpleMouseOver);
                            sT.addState("SELECTED", img);
                            s = new Sprite(sT, i*85, j*80, 0, 0, VISIBLE_STATE);
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                    }
                                   if (levelGrid[i][j] == 6) {
                                    
                            String ShroomBlue = props.getProperty(TeemoCrushPropertyType.SHROOM_BLUE_IMAGE_NAME);
                            sT = new SpriteType(BLUE_SHROOM_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomBlue);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomBlueMouseOver = props.getProperty(TeemoCrushPropertyType.SHROOM_BLUE_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomBlueMouseOver);
                            sT.addState("SELECTED", img);
                            s = new Sprite(sT, i*85, j*80, 0, 0, VISIBLE_STATE);
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                    }
                        }
                    
                }  
                
          }
         
        }
        
        public void moveAllTiles(){
            for (int i = 0; i< gridColumns; i++) {
                for (int j = 0; j < gridRows; j++) 
                {
                    tileGrid[i][j].setTarget(coordGridX[i][j], coordGridY[i][j]);
                    tileGrid[i][j].startMovingToTarget(10);
                }
                
                
            }
                   
        }
        
    public void initWrapperGrid()
    {
        points = 0;
        goal = 30000;
        // KEEP ALL THE GRID INFO

        int [][]levelGridFake = { { 1, 2, 1, 2, 2 },
                                  { 1, 2, 2, 3, 2 }, 
                                  { 4, 1, 2, 2, 1 }, 
                                  { 2, 2, 3, 4, 3 },
                                  { 3, 2, 3, 4, 3 } };
        levelGrid = levelGridFake;
        
        gridColumns = 5;
        gridRows = 5;
        movesLeft = 100;
        
        // AND BUILD THE TILE GRID FOR STORING THE TILES
        // SINCE WE NOW KNOW ITS DIMENSIONS
      
        generateGrid();
  
    }   
    public void generateGrid(){
        tileGrid = new Tile[gridColumns][gridRows];
                BufferedImage img;
        float x, y;
        SpriteType sT;
        Sprite s;
         coordGridX = new float[gridColumns][gridRows];
         coordGridY = new float[gridColumns][gridRows];
       
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String imgPath = props.getProperty(TeemoCrushPropertyType.IMG_PATH);       
        for (int i = 0; i < gridColumns; i++)
        {
            for (int j = 0; j < gridRows; j++)
            {
 
        
                // EACH CELL HAS A STACK OF TILES, WE'LL USE
                // AN ARRAY LIST FOR THE STACK
                if (levelGrid[i][j] == 1) {
                            
                            String ShroomYellow = props.getProperty(TeemoCrushPropertyType.SHROOM_YELLOW_IMAGE_NAME);
                            sT = new SpriteType(YELLOW_SHROOM_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomYellow);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomYellowMouseOver = props.getProperty(TeemoCrushPropertyType.SHROOM_YELLOW_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomYellowMouseOver);
                            sT.addState("SELECTED", img);
                            s = new Sprite(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                           
                }
                if (levelGrid[i][j] == 2) {
                    String ShroomRed = props.getProperty(TeemoCrushPropertyType.SHROOM_RED_IMAGE_NAME);
                            sT = new SpriteType(RED_SHROOM_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomRed);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomRedMouseOver = props.getProperty(TeemoCrushPropertyType.SHROOM_RED_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomRedMouseOver);
                            sT.addState("SELECTED", img);
                            s = new Sprite(sT, i*85, j*80, 0, 0, VISIBLE_STATE);
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                }
                                if (levelGrid[i][j] == 3) {
                    String ShroomOrange = props.getProperty(TeemoCrushPropertyType.SHROOM_ORANGE_IMAGE_NAME);
                            sT = new SpriteType(ORANGE_SHROOM_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomOrange);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomOrangeMouseOver = props.getProperty(TeemoCrushPropertyType.SHROOM_ORANGE_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomOrangeMouseOver);
                            sT.addState("SELECTED", img);
                            s = new Sprite(sT, i*85, j*80, 0, 0, VISIBLE_STATE);
                             tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                            
                }
                
                                 if (levelGrid[i][j] == 4) {
                    String ShroomGreen = props.getProperty(TeemoCrushPropertyType.SHROOM_GREEN_IMAGE_NAME);
                            sT = new SpriteType(GREEN_SHROOM_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomGreen);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomGreenMouseOver = props.getProperty(TeemoCrushPropertyType.SHROOM_GREEN_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomGreenMouseOver);
                            sT.addState("SELECTED", img);
                            s = new Sprite(sT, i*85, j*80, 0, 0, VISIBLE_STATE);
                            tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                
            }
                                  if (levelGrid[i][j] == 5) {
                    String ShroomPurple = props.getProperty(TeemoCrushPropertyType.SHROOM_PURPLE_IMAGE_NAME);
                            sT = new SpriteType(PURPLE_SHROOM_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomPurple);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomPurpleMouseOver = props.getProperty(TeemoCrushPropertyType.SHROOM_PURPLE_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomPurpleMouseOver);
                            sT.addState("SELECTED", img);
                            s = new Sprite(sT, i*85, j*80, 0, 0, VISIBLE_STATE);
                             tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                    }
                                   if (levelGrid[i][j] == 6) {
                                    
                            String ShroomBlue = props.getProperty(TeemoCrushPropertyType.SHROOM_BLUE_IMAGE_NAME);
                            sT = new SpriteType(BLUE_SHROOM_TYPE);
                            img = miniGame.loadImage(imgPath + ShroomBlue);
                            sT.addState(VISIBLE_STATE, img);

                            String ShroomBlueMouseOver = props.getProperty(TeemoCrushPropertyType.SHROOM_BLUE_MOUSE_OVER_IMAGE_NAME);
                            img = miniGame.loadImage(imgPath + ShroomBlueMouseOver);
                            sT.addState("SELECTED", img);
                            s = new Sprite(sT, 100 + i*61, j*80, 0, 0, VISIBLE_STATE);
                             tileGrid[i][j] = new Tile(sT, 100 + i*61, j*61 + 10,0 ,0, "VISIBLE_STATE");
                    }
                            
                            coordGridX[i][j] = (float) (i*61 + 100);
                            coordGridY[i][j] = (float) (j*61+10);
                                   tileGrid[i][j].setState("VISIBLE_STATE");
                                  
            }
        }
    }
    public void initVerticalGrid()
    {
        points = 0;
        goal = 30000;
        // KEEP ALL THE GRID INFO

        int [][]levelGridFake = { { 2, 1, 2, 4, 1 },
                                  { 2, 4, 2, 3, 3 }, 
                                  { 4, 1, 1, 2, 2 }, 
                                  { 3, 2, 2, 4, 2 },
                                  { 3, 2, 3, 2, 1 } };
        levelGrid = levelGridFake;
        
        gridColumns = 5;
        gridRows = 5;
        movesLeft = 100;
       
        // AND BUILD THE TILE GRID FOR STORING THE TILES
        // SINCE WE NOW KNOW ITS DIMENSIONS
      generateGrid();
        
        
  
    }   
    public void initLevelGrid(int[][] initGrid, int initGridColumns, int initGridRows)
    {
        points = 0;
        goal = 300;
        // KEEP ALL THE GRID INFO
        levelGrid = initGrid;
        gridColumns = initGridColumns;
        gridRows = initGridRows;
        movesLeft = 6;
        
        // AND BUILD THE TILE GRID FOR STORING THE TILES
        // SINCE WE NOW KNOW ITS DIMENSIONS
      
        generateGrid();
  
    }   
    
    
    
        public int calculateGridCellRow(int y)
    {
        float topEdge = miniGame.getBoundaryTop();
        y = (int)(y - 10);
        return y / TILE_IMAGE_HEIGHT;
    }
        
        public int calculateGridCellColumn(int x)
    {
        float leftEdge = miniGame.getBoundaryLeft();
        x = (int)(x - 100);
        return x / TILE_IMAGE_WIDTH;
    }
    
   
    public void swap(Tile selectedTile, Tile selectTile){

        selectedTile.setTarget(selectTile.getX(), selectTile.getY());
        
        
        selectTile.setTarget(selectedTile.getX(), selectedTile.getY());
        selectTile.startMovingToTarget(1);
        selectedTile.startMovingToTarget(1);
     }
     public static void scrollUp() {
         
        if (sagaMap.getY()<-250) {
            
            float y = sagaMap.getY() + 200;
            
            sagaMap.setTarget(0,y);
            sagaMap.startMovingToTarget(70);
            
            shroom.setTarget(360,shroom.getY() +200);
            shroom2.setTarget(360,shroom2.getY() +200);
            shroom.startMovingToTarget(70);
            shroom2.startMovingToTarget(70);
            
            for (int i = 0; i< 8 ; i++) {
                shrooms[i].setTarget(shrooms[i].getX(), shrooms[i].getY() + 200);
                shrooms[i].startMovingToTarget(70);
            }
        }
        else {
            sagaMap.setTarget(0,-250);
            sagaMap.startMovingToTarget(70);
                 
        }
    }
        public static void scrollDown() {

        if (sagaMap.getY()>-5150) {
          
            
            sagaMap.setTarget(0,sagaMap.getY() - 200);
            sagaMap.startMovingToTarget(70);
            
            shroom.setTarget(360,shroom.getY() - 200);
            shroom.startMovingToTarget(70);
            shroom2.setTarget(shroom2.getX(), shroom2.getY() - 200);
            shroom2.startMovingToTarget(70);
             
            for (int i = 0; i< 8 ; i++) {
                shrooms[i].setTarget(shrooms[i].getX(), shrooms[i].getY() - 200);
                shrooms[i].startMovingToTarget(70);
            }
        }
        else {
            sagaMap.setTarget(0,-5350);
            sagaMap.startMovingToTarget(70);
            shroom.setTarget(360, 460);
            shroom.startMovingToTarget(70);
            shroom2.setTarget(360, 190);
            shroom2.startMovingToTarget(70);
             shrooms[0].setTarget(620, 10);
             shrooms[1].setTarget(620, -190);
             shrooms[2].setTarget(425, -350);
             shrooms[3].setTarget(600, -620);
             shrooms[4].setTarget(600, -800);
             shrooms[5].setTarget(520, -990);
             shrooms[6].setTarget(290, -990);
             shrooms[7].setTarget(375, -1250);
             
            for (int i = 0; i< 8 ; i++) {
                
                shrooms[i].startMovingToTarget(70);
            }
        }
    }
        public void enableSagaMap(boolean enable)
    {
            // AND SET THEM PROPERLY
            if (enable) {
                sagaMap.setState(VISIBLE_STATE);
            shroom.setState(VISIBLE_STATE);
            shroom2.setState(VISIBLE_STATE);
            for (int i = 0; i< 8; i++) {
                shrooms[i].setState(VISIBLE_STATE);
            }
            }
            else{
                sagaMap.setState(INVISIBLE_STATE);
                shroom.setState(INVISIBLE_STATE);
                shroom.setEnabled(false);
                shroom2.setState(INVISIBLE_STATE);
                shroom2.setEnabled(false);
                for (int i = 0; i< 8; i++) {
                    shrooms[i].setState(INVISIBLE_STATE);
                    shrooms[i].setEnabled(false);
                }
            }
    }

            @Override
    public void reset(MiniGame game)
    {
        miniGame.getGUIDialogs().get(LOSS_DIALOG_TYPE).setState(INVISIBLE_STATE);
        miniGame.getGUIDialogs().get(WIN_DIALOG_TYPE).setState(INVISIBLE_STATE);
        miniGame.getGUIButtons().get(PLAY_AGAIN_TYPE).setState(INVISIBLE_STATE);
        miniGame.getGUIButtons().get(PLAY_AGAIN_TYPE).setEnabled(false);

        miniGame.getGUIButtons().get(RETURN_SAGA_TYPE).setState(INVISIBLE_STATE);
        miniGame.getGUIButtons().get(RETURN_SAGA_TYPE).setEnabled(false);

        beginGame();
            
        
         //miniGame.getGUIDialogs().get(WIN_DIALOG_TYPE).setState(INVISIBLE_STATE);

}
           
  
}
