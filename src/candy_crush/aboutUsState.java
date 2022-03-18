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

/**
 *
 * @author DELL
 */
public class aboutUsState extends States {
    Group Gamewindow = new Group();
    Scene scene;
    Canvas myGame =new Canvas(WIDTH,HEIGHT);
    Buttons back_button;
    private Image backGround;
    private GameMedia media;
    private GraphicsContext drawer = myGame.getGraphicsContext2D();
    
    public aboutUsState(Stack<States> states) throws IOException {
        super(states);
        this.initbackGround();
        this.initMedia();
        this.initDrawer();
        this.initButtons();
        this.initScene();
    }

    @Override
    public void initScene() {
            this.scene = new Scene(this.Gamewindow,WIDTH,HEIGHT);
            this.Primaryscene = scene;
            }
    private void initbackGround() throws IOException{
                this.backGround = new Image(Files.newInputStream(Paths.get("Candy Crush things\\team.jpg")));
            }
    private void initButtons() throws IOException{
            
                this.back_button = new Buttons("Candy Crush things/back_button.png",50,450,90,40,this.Gamewindow);
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
    @Override
    public void update(double dt) {
       this.updateButtons();
        this.updateStage();
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
    public void updateStage() {
            stage.setTitle("Candy Crush");
            stage.getIcons().add(GameMedia.Iconimage);      
            stage.setResizable(false);
            stage.setScene(this.Primaryscene);
            stage.sizeToScene();
            stage.show();
            }

    @Override
    public void render() {
       drawer.drawImage(this.backGround,0, 0, 800, 500);
       back_button.renderButton();
    }
    
}
