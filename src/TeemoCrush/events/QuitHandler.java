/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TeemoCrush.events;

import static TeemoCrush.TeemoCrushConstants.SPLASH_SCREEN_STATE;
import static TeemoCrush.TeemoCrushConstants.SAGA_SCREEN_STATE;
import static TeemoCrush.TeemoCrushConstants.SELECT_SCREEN_STATE;
import static TeemoCrush.TeemoCrushConstants.GAME_SCREEN_STATE;
import TeemoCrush.data.DataModel;
import TeemoCrush.file.FileManager;
import TeemoCrush.ui.TeemoCrushMiniGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author david
 */
public class QuitHandler implements ActionListener {
        // HERE'S THE GAME WE'LL UPDATE
    private TeemoCrushMiniGame game;
    
        public QuitHandler(TeemoCrushMiniGame initGame)
    {
        // WE'LL NEED THIS WHEN WE RESPOND, SO KEEP A REFERERNCE
        game = initGame;
    }
        @Override
    public void actionPerformed(ActionEvent ae)
    {
        System.exit(0);
    }
}
