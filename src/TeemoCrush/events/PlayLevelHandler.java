/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TeemoCrush.events;

import static TeemoCrush.TeemoCrushConstants.SPLASH_SCREEN_STATE;
import static TeemoCrush.TeemoCrushConstants.SAGA_SCREEN_STATE;
import static TeemoCrush.TeemoCrushConstants.SELECT_SCREEN_STATE;
import static TeemoCrush.TeemoCrushConstants.GAME_SCREEN_STATE;
import static TeemoCrush.TeemoCrushConstants.INVISIBLE_STATE;
import static TeemoCrush.TeemoCrushConstants.LOSS_DIALOG_TYPE;
import static TeemoCrush.TeemoCrushConstants.PLAY_AGAIN_TYPE;
import TeemoCrush.data.DataModel;
import TeemoCrush.file.FileManager;
import TeemoCrush.ui.TeemoCrushMiniGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author david
 */
public class PlayLevelHandler implements ActionListener {
        // HERE'S THE GAME WE'LL UPDATE
    private TeemoCrushMiniGame game;
    int cols; int rows;
    
        public PlayLevelHandler(TeemoCrushMiniGame initGame, int col, int row, int moves, int points)
    {
        // WE'LL NEED THIS WHEN WE RESPOND, SO KEEP A REFERERNCE
        game = initGame;
        cols = col; rows = row;
    }
        @Override
    public void actionPerformed(ActionEvent ae)
    {
        // WE ONLY LET THIS HAPPEN IF THE SPLASH SCREEN IS VISIBLE
        
        if (game.isCurrentScreenState(SELECT_SCREEN_STATE) || game.isCurrentScreenState(GAME_SCREEN_STATE))
        {
            // GET THE GAME'S DATA MODEL, WHICH IS ALREADY LOCKED FOR US
            DataModel data = (DataModel)game.getDataModel();
        
            // UPDATE THE DATA
            FileManager fileManager = game.getFileManager();
 
            // GO TO THE GAME
            //game.switchToGameScreen();
                        // FIRST READ THE GRID DIMENSIONS
            int initGridColumns = cols;
            int initGridRows = rows;
            int[][] newGrid = new int[initGridColumns][initGridRows];
            boolean match = false;
            // AND NOW ALL THE CELL VALUES
            do {
                
                for (int i = 0; i < initGridColumns; i++)
                {                        
                    for (int j = 0; j < initGridRows; j++)
                    {
                        newGrid[i][j] = ((int) (Math.random() *6)) + 1;
                    }
                }
                match = false;
               
                for (int i = 0; i < initGridColumns; i++)
                {                        
                    for (int j = 0; j < initGridRows; j++)
                    {
                        if (((i >= 0 && i < initGridColumns - 2 && newGrid[i][j] == newGrid[i+1][j] &&
                            newGrid[i+1][j] == newGrid[i+2][j])
                            || (j >= 0 && j < initGridRows - 2 && newGrid[i][j] == newGrid[i][j+1] &&
                        newGrid[i][j+1] == newGrid[i][j+2]))) {
                        match = true;
                           
                        }
                        
                    }
                }
            }
            while (
                    match == true
                    );
            
            
            
           data.initLevelGrid(newGrid, initGridColumns, initGridRows);
            
            //data.initWrapperGrid();
           //data.initVerticalGrid();
            game.switchToGameScreen();
            
        }
    }
}
