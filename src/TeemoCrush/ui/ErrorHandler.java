/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TeemoCrush.ui;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import TeemoCrush.TeemoCrush.TeemoCrushPropertyType;
import properties_manager.PropertiesManager;
/**
 *
 * @author david
 */
public class ErrorHandler {
        // WE'LL CENTER DIALOG BOXES OVER THE WINDOW, SO WE NEED THIS
    private JFrame window;
    
        public ErrorHandler(JFrame initWindow)
    {
        // KEEP THE WINDOW FOR LATER
        window = initWindow;
    }
    public void processError(TeemoCrushPropertyType errorType)
    {
        // GET THE FEEDBACK TEXT
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        String errorFeedbackText = props.getProperty(errorType);
        
        // NOTE THAT WE'LL USE THE SAME DIALOG TITLE FOR ALL ERROR TYPES
        String errorTitle = props.getProperty(TeemoCrushPropertyType.ERROR_DIALOG_TITLE_TEXT);
        
        // POP OPEN A DIALOG TO DISPLAY TO THE USER
        JOptionPane.showMessageDialog(window, errorFeedbackText, errorTitle, JOptionPane.ERROR_MESSAGE);
    }    
        
}
