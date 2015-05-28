/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TeemoCrush.events;
import java.awt.event.WindowAdapter;
import TeemoCrush.ui.TeemoCrushMiniGame;
import java.awt.event.WindowEvent;

/**
 *
 * @author david
 */
public class ExitHandler extends WindowAdapter {
        private TeemoCrushMiniGame miniGame;
    
    public ExitHandler(TeemoCrushMiniGame initMiniGame)
    {
        miniGame = initMiniGame;
    }
        @Override
    public void windowClosing(WindowEvent we)
    {
        // IF THE GAME IS STILL GOING ON, END IT AS A LOSS
        if (miniGame.getDataModel().inProgress())
        {
            miniGame.getDataModel().endGameAsLoss();
        }
        // AND CLOSE THE ALL
        System.exit(0);
    }
}
