/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TeemoCrush.events;

import java.awt.event.ActionListener;
import TeemoCrush.ui.TeemoCrushMiniGame;
import java.awt.event.ActionEvent;
import TeemoCrush.data.DataModel;
/**
 *
 * @author david
 */
public class ScrollUpHandler implements ActionListener{
     private TeemoCrushMiniGame miniGame;
    private DataModel data;
    public ScrollUpHandler(TeemoCrushMiniGame initMiniGame)
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
        
           
  
       DataModel.scrollUp();
    }
}
