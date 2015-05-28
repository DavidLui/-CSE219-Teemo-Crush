/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TeemoCrush.events;

import static TeemoCrush.TeemoCrushConstants.SPLASH_SCREEN_STATE;
import static TeemoCrush.TeemoCrushConstants.SAGA_SCREEN_STATE;
import static TeemoCrush.TeemoCrushConstants.SELECT_SCREEN_STATE;
import TeemoCrush.data.DataModel;
import TeemoCrush.file.FileManager;
import TeemoCrush.ui.TeemoCrushMiniGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author david
 */
public class ReturnHandler implements ActionListener {
        // HERE'S THE GAME WE'LL UPDATE
    private TeemoCrushMiniGame game;
    
        public ReturnHandler(TeemoCrushMiniGame initGame)
    {
        // WE'LL NEED THIS WHEN WE RESPOND, SO KEEP A REFERERNCE
        game = initGame;
    }
        @Override
    public void actionPerformed(ActionEvent ae)
    {
        // WE ONLY LET THIS HAPPEN IF THE SPLASH SCREEN IS VISIBLE
        if (game.isCurrentScreenState(SELECT_SCREEN_STATE)||(game.isCurrentScreenState("GAME_SCREEN_STATE")))
        {
            // GET THE GAME'S DATA MODEL, WHICH IS ALREADY LOCKED FOR US
            DataModel data = (DataModel)game.getDataModel();
        
            // UPDATE THE DATA
            FileManager fileManager = game.getFileManager();
            
            //game.switchToGameScreen();
            game.switchToSagaScreen();
            
        }
    }
}
