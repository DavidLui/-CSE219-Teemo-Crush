/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TeemoCrush.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import TeemoCrush.data.DataModel;
import TeemoCrush.file.FileManager;
import TeemoCrush.ui.TeemoCrushMiniGame;
import static TeemoCrush.TeemoCrushConstants.*;
/**
 *
 * @author david
 */
public class PlayHandler implements ActionListener {
        // HERE'S THE GAME WE'LL UPDATE
    private TeemoCrushMiniGame game;
    private DataModel data;
        public PlayHandler(TeemoCrushMiniGame initGame)
    {
        // WE'LL NEED THIS WHEN WE RESPOND, SO KEEP A REFERERNCE
        game = initGame;
    }
        @Override
    public void actionPerformed(ActionEvent ae)
    {
        // WE ONLY LET THIS HAPPEN IF THE SPLASH SCREEN IS VISIBLE
        if (game.isCurrentScreenState(SPLASH_SCREEN_STATE))
        {
            // GET THE GAME'S DATA MODEL, WHICH IS ALREADY LOCKED FOR US
            DataModel data = (DataModel)game.getDataModel();
        
            // UPDATE THE DATA
            FileManager fileManager = game.getFileManager();
            data.completedLevels = new boolean[10];
            for (int i = 0; i < 10; i++) {
                data.completedLevels[i] = false;
            }
            data.topScore = new int[10];
            // GO TO THE GAME
            game.switchToSagaScreen();
        }
    }
}
