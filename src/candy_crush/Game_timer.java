
package candy_crush;

import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.canvas.GraphicsContext;


public class Game_timer {
    //Fields
        public Timer myTimer = new Timer();
        public  int SecCounter = 0;
        public  int MinCounter = 0;
        private String Timer = new String();
        
        TimerTask seconds = new TimerTask(){
            @Override
            public void run()
            {
                updateTimer(SecCounter+1);
            }
        };
        
   //Methods
        //Constructor
            public Game_timer(){
                myTimer.schedule(seconds,0,1000);
            }
        
        //update Functions 
            public void updateTimer(int counter){
                if(counter !=60)
                {
                     SecCounter=counter;
                }

                else
                {
                    SecCounter = 0;
                    MinCounter++;
                }
            }
            
        //Render Functions
            public void renderTimer(GraphicsContext gc){
                if(SecCounter<10 && MinCounter<10)
                {
                    this.Timer =  "0"+MinCounter+":0"+SecCounter;
                }
                else if(SecCounter>=10 && MinCounter<10)
                {
                    this.Timer = "0"+MinCounter+":"+SecCounter;
                }
                else if(SecCounter>=10 && MinCounter>10)
                {
                    this.Timer =  MinCounter+":"+SecCounter;
                }

                else if(SecCounter<10 && MinCounter>=10)
                {
                    this.Timer =  MinCounter+":0"+SecCounter;
                }
                else
                {
                    this.Timer =  " ";
                }
                gc.fillText(this.Timer, 50, 50);      
            }

   
    
}
