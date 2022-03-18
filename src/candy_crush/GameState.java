
package candy_crush;

import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameState extends States {
    //Fields
        private Board gameBoard;
        
        Group Gamewindow = new Group();
        Canvas myGame =new Canvas(WIDTH,HEIGHT);
        private GraphicsContext drawer = myGame.getGraphicsContext2D();
          
        private Game_timer GameTimer = new Game_timer();
        
        private GameMedia media;
        Buttons back_button;
       
        int counter=0;
        
        Scene scene;
        
    //Functions
        //Constructor
            public GameState(Stack<States> states) throws IOException{
                super(states);
                this.initMedia();
                this.initDrawer();
                this.initButtons();
                this.initBoard();
                this.initScene();
            }
        
        //Initiallization methods
            @Override
            public void initScene() {
            this.scene = new Scene(this.Gamewindow,WIDTH,HEIGHT);
            this.Primaryscene = scene;
            }
            private void initMedia() throws IOException{
                media = new GameMedia();
                media.updateMusic("stop");
            }
            private void initDrawer(){
            
                this.drawer.setFont(GameMedia.Gamefont);
                this.drawer.setFill(Color.BLACK);
                this.drawer.setStroke(Color.PINK);
                Gamewindow.getChildren().add(myGame);
            }
            private void initButtons() throws IOException{
            
                this.back_button = new Buttons("Candy Crush things/back_button.png",50,450,90,40,this.Gamewindow);
            }
            private void initBoard(){
                 gameBoard = new Board(this.Gamewindow);
            }
            
        //update methods
            @Override
            public void updateStage() {
            stage.setTitle("Candy Crush");
            stage.getIcons().add(GameMedia.Iconimage);      
            stage.setResizable(false);
            stage.setScene(this.Primaryscene);
            stage.sizeToScene();
            stage.show();
            }
            private void updateTimer(double dt){
                if(counter!=2){counter=(int)((dt % (2 * 0.8f)) / 0.8f);}
                else{counter=0;}
            }
            private void updateButtons(){
                 if(this.back_button.isClicked())
               {
                   try {                       
                       this.states.push(new MenuState(this.states));
                       
                   } catch (IOException ex) {
                       Logger.getLogger(GameState.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
               this.back_button.update();
            }
            @Override
            public void update(double dt){
                this.updateTimer(dt);
                this.updateButtons();
                this.gameBoard.update();
                this.updateStage();
           }
            
        //render methods
            @Override
            public void render(){
               drawer.drawImage(GameMedia.Gamebackground,0 , 0);
               drawer.drawImage(GameMedia.Timeboard, 30, 15);
               drawer.drawImage(GameMedia.Animatedcandy[counter], 120, 15);
               back_button.renderButton();
               GameTimer.renderTimer(drawer);
            }

    
    
}
