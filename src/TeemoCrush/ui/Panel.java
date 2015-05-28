/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TeemoCrush.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.JPanel;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;
import TeemoCrush.data.DataModel;
import static TeemoCrush.TeemoCrushConstants.*;
import TeemoCrush.data.Record;
import java.awt.Font;
/**
 *
 * @author david
 */
public class Panel extends JPanel {
        // THIS IS ACTUALLY OUR Mahjong Solitaire APP, WE NEED THIS
    // BECAUSE IT HAS THE GUI STUFF THAT WE NEED TO RENDER
    private MiniGame game;
    
    // AND HERE IS ALL THE GAME DATA THAT WE NEED TO RENDER
    private DataModel data;
    
    // WE'LL USE THIS TO FORMAT SOME TEXT FOR DISPLAY PURPOSES
    private NumberFormat numberFormatter;
 
    // WE'LL USE THIS AS THE BASE IMAGE FOR RENDERING UNSELECTED TILES
    private BufferedImage blankTileImage;
    static int i = 0;
    // WE'LL USE THIS AS THE BASE IMAGE FOR RENDERING SELECTED TILES
    private BufferedImage mapImage;
    private BufferedImage mapImageShroom;
    private BufferedImage mapImageShroommouseover;
    
        public Panel(MiniGame initGame, DataModel initData)
    {
        game = initGame;
        data = initData;
        numberFormatter = NumberFormat.getNumberInstance();
        numberFormatter.setMinimumFractionDigits(3);
        numberFormatter.setMaximumFractionDigits(3);
    }
    public void renderSprite(Graphics g, Sprite s)
    {
                // ONLY RENDER THE VISIBLE ONES
        if (!s.getState().equals(INVISIBLE_STATE))
        {
            SpriteType bgST = s.getSpriteType();
            Image img = bgST.getStateImage(s.getState());
            g.drawImage(img, (int)s.getX(), (int)s.getY(), bgST.getWidth(), bgST.getHeight(), null); 
        }
        
    }
    
            @Override
    public void paintComponent(Graphics g)
    {
        try
        {
            // MAKE SURE WE HAVE EXCLUSIVE ACCESS TO THE GAME DATA
            game.beginUsingData();
        
            // CLEAR THE PANEL
            
            super.paintComponent(g);
            // RENDER THE BACKGROUND, WHICHEVER SCREEN WE'RE ON
            
            renderBackground(g);
                        renderSagaMap(g);

            renderGUIControls(g);
            
            renderGameTiles(g);
            
            if (TeemoCrushMiniGame.currentScreenState.matches(GAME_SCREEN_STATE)) {
            renderDialogs(g);
            }
            else {
                game.getGUIButtons().get(RETURN_SAGA_TYPE).setEnabled(false);
                game.getGUIButtons().get(PLAY_AGAIN_TYPE).setEnabled(false);
                
            }
            if (TeemoCrushMiniGame.currentScreenState.matches(SELECT_SCREEN_STATE)) {
                if (g.getColor() !=  (Color.WHITE)) {

                g.setColor(Color.WHITE);

                g.setFont(new Font("TimesRoman", Font.BOLD, 30));
                }
                if (data.currentLevel == 1) {
                    g.drawString("1",150, 35);g.drawString("6",150, 90);
                    g.drawString(" = 300", 350, 80);g.drawString(" = 400", 350, 135);g.drawString(" = 500", 350, 185);
                    String topscore = "" + data.topScore[0];
                    g.drawString(topscore, 100, 350);
                    if(data.topScore[0] >= 300) {
                          g.drawString("1 Earned", 110, 190);
                    }
                     if(data.topScore[0] >= 400) {
                          g.drawString("2 Earned", 110, 190);
                    }
                      if(data.topScore[0] >= 500) {
                          g.drawString("3 Earned", 110, 190);
                    }
                      else  g.drawString("0 Earned", 110, 190);
                }
                if (data.currentLevel == 2) {
                    g.drawString("2", 150,35);g.drawString("15",150, 90);
                    g.drawString(" = 1900", 350, 80);g.drawString(" = 2100", 350, 135);g.drawString(" = 2400", 350, 185);
                    String topscore = "" + data.topScore[1];
                    g.drawString(topscore, 100, 350);
                    if(data.topScore[1] >= 1900) {
                          g.drawString("1 Earned", 110, 190);
                    }
                     if(data.topScore[1] >= 2100) {
                          g.drawString("2 Earned", 110, 190);
                    }
                      if(data.topScore[1] >= 2400) {
                          g.drawString("3 Earned", 110, 190);
                    }
                      else  g.drawString("0 Earned", 110, 190);
                }
                                
                if (data.currentLevel == 3) {
                    g.drawString("3",150, 35); g.drawString("18",150, 90);

                    g.drawString(" = 4000", 350, 80);g.drawString(" = 6000", 350, 135);g.drawString(" = 8000", 350, 185);
  String topscore = "" + data.topScore[2];
                    g.drawString(topscore, 100, 350);
                }
                if (data.currentLevel == 4) {
                    g.drawString("4", 150,35);g.drawString("15",150, 90);
                    g.drawString(" = 4500", 350, 80);g.drawString(" = 6000", 350, 135);g.drawString(" = 8000", 350, 185);
String topscore = "" + data.topScore[3];
                    g.drawString(topscore, 100, 350);
                }
                               
                if (data.currentLevel == 5) {
                    g.drawString("5",150, 35);g.drawString("20",150, 90);
                    g.drawString(" = 5000", 350, 80);g.drawString(" = 8000", 350, 135);g.drawString(" = 12000", 350, 185);
                String topscore = "" + data.topScore[4];
                    g.drawString(topscore, 100, 350);
                }
                if (data.currentLevel == 6) {
                    g.drawString("6", 150,35);g.drawString("25",150, 90);
                    g.drawString(" = 9000", 350, 80);g.drawString(" = 11000", 350, 135);g.drawString(" = 13000", 350, 185);
String topscore = "" + data.topScore[5];
                    g.drawString(topscore, 100, 350);
                }                
                if (data.currentLevel == 7) {
                    g.drawString("7",150, 35);g.drawString("50",150, 90);
                    g.drawString(" = 60000", 350, 80);g.drawString(" = 75000", 350, 135);g.drawString(" = 85000", 350, 185);
                String topscore = "" + data.topScore[6];
                    g.drawString(topscore, 100, 350);
                }
                if (data.currentLevel == 8) {
                    g.drawString("8", 150,35);g.drawString("20",150, 90);
                    g.drawString(" = 20000", 350, 80);g.drawString(" = 30000", 350, 135);g.drawString(" = 45000", 350, 185);
String topscore = "" + data.topScore[7];
                    g.drawString(topscore, 100, 350);
                }                
                if (data.currentLevel == 9) {
                g.drawString("9",150, 35);g.drawString("25",150, 90);
                    g.drawString(" = 22000", 350, 80);g.drawString(" = 44000", 350, 135);g.drawString(" = 66000", 350, 185);
                String topscore = "" + data.topScore[8];
                    g.drawString(topscore, 100, 350);
                }
                if (data.currentLevel == 10) {
                    g.drawString("10", 150,35);g.drawString("40",150, 90);
                    g.drawString(" = 40000", 350, 80);g.drawString(" = 70000", 350, 135);g.drawString(" = 100000", 350, 185);
String topscore = "" + data.topScore[9];
                    g.drawString(topscore, 100, 350);
                }
                
            }
            
            
            
        }
                finally
        {
            // RELEASE THE LOCK
            game.endUsingData();    
        }
    }
    public void renderGameTiles(Graphics g) {
        
        if(TeemoCrushMiniGame.currentScreenState == "GAME_SCREEN_STATE") {
            
                g.setFont(new Font("TimesRoman", Font.BOLD, 30));
             
            
            g.fillRect(220, 570, 300, 50);
            g.setColor(Color.YELLOW);
            
            double percentage = data.points/data.goal;
            if (percentage >= 1.0)
                percentage = 1.0;
            g.fillRect(220, 570, (int) (percentage * 300), 50);
            
            int moves = data.movesLeft;
            String stringMoves = moves + "";
            
            g.drawString("Moves Left: " + stringMoves, 20, 630);
            double point = data.points;
            String stringPoint = point + "";
            
            g.drawString("Points: " + stringPoint.substring(0,stringPoint.length()-2), 20, 580);
       
            
            for (int j = 0; j < data.gridRows; j++) {
            for (int i = 0; i < data.gridColumns; i++){
              
               if (data.tileGrid[i][j] != null)
                  g.drawImage(data.tileGrid[i][j].getSpriteType().getStateImage(data.tileGrid[i][j].getState()),
                        (int)data.tileGrid[i][j].getX(), (int)data.tileGrid[i][j].getY(), null);
                
            }
        }
    }
    }
    public void renderSagaMap(Graphics g){
              

        
       if (!data.sagaMap.getState().equals(INVISIBLE_STATE)){
           if (data.completedLevels[0] == true) {
               game.getGUIButtons().get("LevelShroom2").setEnabled(true);
           }
           
           
           g.drawImage(mapImage, (int)data.sagaMap.getX(), (int) data.sagaMap.getY(), null);
           
            if (data.shroom2.getState().equals(MOUSE_OVER_STATE)){
          g.drawImage(mapImageShroommouseover, (int)data.shroom2.getX(), (int)data.shroom2.getY(), null);
       }
        else {
          g.drawImage(mapImageShroom, (int)data.shroom2.getX(), (int)data.shroom2.getY(), null);
        }
           
           
        if (data.shroom.getState().equals(MOUSE_OVER_STATE)){
          g.drawImage(mapImageShroommouseover, (int)data.shroom.getX(), (int)data.shroom.getY(), null);
       }
        else {
          g.drawImage(mapImageShroom, (int)data.shroom.getX(), (int)data.shroom.getY(), null);
        }
           
        
        for (int i =0 ;i < 8; i++) {
                if (data.shrooms[i].getState().equals(MOUSE_OVER_STATE)){
          g.drawImage(mapImageShroommouseover, (int)data.shrooms[i].getX(), (int)data.shrooms[i].getY(), null);
         }
        else {
          g.drawImage(mapImageShroom, (int)data.shrooms[i].getX(), (int)data.shrooms[i].getY(), null);
        }
            
            
        }
        
       }
        
    }
    public void setMapImage(BufferedImage mapImage2) {
        
        mapImage = mapImage2;
    }
        public void setMapImage2(BufferedImage mapImage2) {
        
        mapImageShroom = mapImage2;
    }
        public void setMapImage2mouseover(BufferedImage mapImage2) {
        
        mapImageShroommouseover = mapImage2;
    }
    public void renderDialogs(Graphics g)
    {
        // GET EACH DECOR IMAGE ONE AT A TIME
        Collection<Sprite> dialogSprites = game.getGUIDialogs().values();
        for (Sprite s : dialogSprites)
        {
            // RENDER THE DIALOG, NOTE IT WILL ONLY DO IT IF IT'S VISIBLE
            renderSprite(g, s);
        }
         Sprite s = game.getGUIButtons().get(PLAY_AGAIN_TYPE);
        renderSprite(g,s);
          Sprite t = game.getGUIButtons().get(RETURN_SAGA_TYPE);
        renderSprite(g,t);
    }
    public void renderBackground(Graphics g)
    {
        // THERE IS ONLY ONE CURRENTLY SET
        Sprite bg = game.getGUIDecor().get(BACKGROUND_TYPE);
        renderSprite(g, bg);
    }
      
        
    public void renderGUIControls(Graphics g)
    {
        // GET EACH DECOR IMAGE ONE AT A TIME
        Collection<Sprite> decorSprites = game.getGUIDecor().values();
        for (Sprite s : decorSprites)
        {
           
           renderSprite(g, s);
        }
        
        // AND NOW RENDER THE BUTTONS
        Collection<Sprite> buttonSprites = game.getGUIButtons().values();
        for (Sprite s : buttonSprites)
        {
             if (s != game.getGUIButtons().get(PLAY_AGAIN_TYPE) &&
                 s != game.getGUIButtons().get(RETURN_SAGA_TYPE))
            renderSprite(g, s);
        }
       
    }
    
        
 

}
