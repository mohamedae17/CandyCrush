package candy_crush;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Candy_Crush extends Application{
    
    //Fields
        
        private Stack<States>states;
        private double dt;
        private final long startNanoTime = System.nanoTime();
        
    //Functions
        //Constructor
            public Candy_Crush() throws IOException{
               this.initStack();
            }
        
        //Initiallization Functions
            private void initStack() throws IOException{
                this.states = new <States>Stack();
                this.states.push(new MenuState(this.states));
            }

            @Override
            public void start(Stage myStage) throws FileNotFoundException, IOException {
               
                //gameloop
                    new AnimationTimer() 
                    {
                         @Override
                        public void handle(long currentNanoTime) 
                        {       
                            dt = (currentNanoTime - startNanoTime) / 1000000000.0; 
                            
                             try {
                                 update();
                             } catch (IOException ex) {
                                 Logger.getLogger(Candy_Crush.class.getName()).log(Level.SEVERE, null, ex);
                             }
                            render();
                        }

                    }.start();
            }
            public void update() throws IOException{

                this.states.peek().update(dt);
            }
            public void render(){
                this.states.peek().render();
            }
            
            public static void main(String[] args) {
               launch(args);
            }

}