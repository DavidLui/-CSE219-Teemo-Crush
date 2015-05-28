/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TeemoCrush.events;

import java.awt.event.ActionListener;
import TeemoCrush.data.DataModel;
import TeemoCrush.ui.TeemoCrushMiniGame;
import java.awt.event.ActionEvent;

/**
 *
 * @author david
 */
public class ScrollDownHandler implements ActionListener{
         private TeemoCrushMiniGame miniGame;
    private DataModel data;
    public ScrollDownHandler(TeemoCrushMiniGame initMiniGame)
    {
        miniGame = initMiniGame;
      
    }
    
        @Override
    public void actionPerformed(ActionEvent ae)
    {
//        // IF THE GAME IS STILL GOING ON, END IT AS A LOSS
//        if (miniGame.getDataModel().inProgress())
//        {
//            miniGame.getDataModel().endGameAsLoss();
//        }
        
//        // AND CLOSE THE ALL
        
           
  
       DataModel.scrollDown();
    }
}
