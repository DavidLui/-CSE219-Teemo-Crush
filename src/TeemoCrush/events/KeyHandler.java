/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TeemoCrush.events;

/**
 *
 * @author David
 */
import static TeemoCrush.TeemoCrushConstants.SPLASH_SCREEN_STATE;
import static TeemoCrush.TeemoCrushConstants.SAGA_SCREEN_STATE;
import TeemoCrush.data.DataModel;
import TeemoCrush.file.FileManager;
import TeemoCrush.ui.TeemoCrushMiniGame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * This event handler lets us provide additional custom responses
 * to key presses while Mahjong is running.
 * 
 * @author Richard McKenna
 */
public class KeyHandler extends KeyAdapter
{
    // THE MAHJONG GAME ON WHICH WE'LL RESPOND
    private TeemoCrushMiniGame game;

    /**
     * This constructor simply inits the object by 
     * keeping the game for later.
     * 
     * @param initGame The Mahjong game that contains
     * the back button.
     */    
    public KeyHandler(TeemoCrushMiniGame initGame)
    {
        game = initGame;
    }
    
    /**
     * This method provides a custom game response to when the user
     * presses a keyboard key.
     * 
     * @param ke Event object containing information about the event,
     * like which key was pressed.
     */
    @Override
    public void keyPressed(KeyEvent ke)
    {
        // CHEAT BY ONE MOVE. NOTE THAT IF WE HOLD THE C
        // KEY DOWN IT WILL CONTINUALLY CHEAT
        if (game.currentScreenState.matches("GAME_SCREEN_STATE"))
        if (ke.getKeyCode() == KeyEvent.VK_C)
        {
            DataModel data = (DataModel)game.getDataModel();
            data.initWrapperGrid();
           
        }
                if (ke.getKeyCode() == KeyEvent.VK_V)
        {
            DataModel data = (DataModel)game.getDataModel();
data.initVerticalGrid();
        }

        
    }
}
