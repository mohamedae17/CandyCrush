/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candy_crush;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class MenuState extends States {
    //Fields
        Scene scene;

         private Buttons play_button;
         private Buttons aboutUs_button;
         private Buttons exit_button;

         private Image backGround;
         
         private Group Gamewindow = new Group();

         Canvas myGame =new Canvas(WIDTH,HEIGHT);
         private GraphicsContext drawer = myGame.getGraphicsContext2D();
         
    //Methods
         //Constructor
            public MenuState(Stack<States>states) throws IOException{
        super(states);
        this.initDrawer();
        this.initbackGround();
        this.initButtons();
        this.initScene();
    }
            
         //Initialisation methods
            @Override
            public void initScene() {
            this.scene = new Scene(this.Gamewindow,WIDTH,HEIGHT);
            this.Primaryscene = scene;
            }
            private void initDrawer(){
             this.drawer.setFont(GameMedia.Gamefont);
              this.drawer.setFill(Color.BLACK);
              this.drawer.setStroke(Color.PINK);
              Gamewindow.getChildren().add(myGame);
          }
            private void initbackGround() throws IOException{
                this.backGround = new Image(Files.newInputStream(Paths.get("Candy Crush things\\backgroung.jpg")));
                
            
            }
            private void initButtons() throws IOException{
                this.play_button = new Buttons("Candy Crush things/bplay.png",370,230,90,40,this.Gamewindow);
                this.aboutUs_button = new Buttons("Candy Crush things/about.png",370,330,90,40,this.Gamewindow);
                this.exit_button = new Buttons("Candy Crush things/exit.png",373,425,90,40,this.Gamewindow);
            }
         
         //Update methods
            @Override
            public void updateStage() {
                stage.setTitle("Candy Crush");
                stage.getIcons().add(GameMedia.Iconimage);      
                stage.setResizable(false);
                stage.setScene(this.Primaryscene);
                stage.sizeToScene();
                stage.show();
            }
            @Override
            public void update(double dt) {
            play_button.update();
            aboutUs_button.update();
            exit_button.update();
            if(play_button.isClicked())
                try {
                    this.states.push(new GameState(this.states));
                } catch (IOException ex) {
                    Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
                }
            if(aboutUs_button.isClicked())
                try {
                    this.states.push(new aboutUsState(this.states));
            } catch (IOException ex) {
                Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(exit_button.isClicked())
                System.exit(0);
        
        this.updateStage();
    }

         //Rendring methods
            @Override
            public void render() {
                drawer.drawImage(this.backGround,0 , 0);
                play_button.renderButton();
                aboutUs_button.renderButton();
                exit_button.renderButton();
    }
          

   

    

    
   
}
