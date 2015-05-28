/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TeemoCrush.ui;

import java.util.ArrayList;
import mini_game.MiniGame;
import mini_game.Sprite;
import mini_game.SpriteType;
import static TeemoCrush.TeemoCrushConstants.*;
/**
 *
 * @author david
 */
public class Tile extends Sprite {
    private String tileType;
    private boolean movingToTarget;
    private float targetX;
    private float targetY;
    private String state1;
    private String state2;

    public Tile(SpriteType initSpriteType,
                                    float initX, 	float initY,
                                    float initVx, 	float initVy,
                                    String state2)
    {
         // SEND ALL THE Sprite DATA TO A Sprite CONSTRUCTOR
        super(initSpriteType, initX, initY, initVx, initVy, state2);
        state1 = state2;
        // INIT THE TILE TYPE
 
    }


    @Override
    public String getState() {
        return state1;
    }
    public void setState(String inputState) {
        state1 = inputState;
    }
    public String getTileType()   
    { 
        return tileType;  
    }
        public float getTargetX() 
    { 
        return targetX; 
    }
        public float getTargetY() 
    { 
        return targetY; 
    }
    public boolean isMovingToTarget() 
    { 
        return movingToTarget; 
    }
    public void setTarget(float initTargetX, float initTargetY) 
    {
        targetX = initTargetX; 
        targetY = initTargetY;
    }  
        public float calculateDistanceToTarget()
    {
        // GET THE X-AXIS DISTANCE TO GO
        float diffX = targetX - x;
        
        // AND THE Y-AXIS DISTANCE TO GO
        float diffY = targetY - y;
        
        // AND EMPLOY THE PYTHAGOREAN THEOREM TO CALCULATE THE DISTANCE
        float distance = (float)Math.sqrt((diffX * diffX) + (diffY * diffY));
        
        // AND RETURN THE DISTANCE
        return distance;
    }
    public void startMovingToTarget(int maxVelocity)
    {
        // LET ITS POSITIONG GET UPDATED
        movingToTarget = true;
        
        // CALCULATE THE ANGLE OF THE TRAJECTORY TO THE TARGET
        float diffX = targetX - x;
        float diffY = targetY - y;
        float tanResult = diffY/diffX;
        float angleInRadians = (float)Math.atan(tanResult);
        
        // COMPUTE THE X VELOCITY COMPONENT
        vX = (float)(maxVelocity * Math.cos(angleInRadians));
        
        // CLAMP THE VELOCTY IN CASE OF NEGATIVE ANGLES
        if ((diffX < 0) && (vX > 0)) vX *= -1;
        if ((diffX > 0) && (vX < 0)) vX *= -1;
        
        // COMPUTE THE Y VELOCITY COMPONENT
        vY = (float)(maxVelocity * Math.sin(angleInRadians));        
        
        // CLAMP THE VELOCITY IN CASE OF NEGATIVE ANGLES
        if ((diffY < 0) && (vY > 0)) vY *= -1;
        if ((diffY > 0) && (vY < 0)) vY *= -1;
    }
        @Override
    public void update(MiniGame game)
    {

        // IF NOT, IF THIS TILE IS ALMOST AT ITS TARGET DESTINATION,
        // JUST GO TO THE TARGET AND THEN STOP MOVING
        if (calculateDistanceToTarget() < MAX_TILE_VELOCITY)
        {
            vX = 0;
            vY = 0;
            x = targetX;
            y = targetY;
            movingToTarget = false;
        }
        // OTHERWISE, JUST DO A NORMAL UPDATE, WHICH WILL CHANGE ITS POSITION
        // USING ITS CURRENT VELOCITY.
        else
        {
            super.update(game);
        }
    }
    
}
