/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candy_crush;

import java.util.Stack;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public abstract class States {
    //Fields
        Stack<States>states;
        final int WIDTH = 800;
        final int HEIGHT = 500;
        Stage stage = new Stage();
        Scene Primaryscene;
    
    //Methods
        //Constructor
            public States(Stack<States>states){
                this.states = states;
            }
        
        //Initialization methods
             public abstract void initScene();
            
        //Update methods
            public abstract void update(double dt);
            public abstract void updateStage();
        
        //Rendring mehtods
            public abstract void render();
    
  
    
}
