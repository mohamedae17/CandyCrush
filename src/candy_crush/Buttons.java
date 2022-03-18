/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candy_crush;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class Buttons {
    //Fields
        private  Button button;
        
        
        
        private  int x_pos;
        private  int y_pos;
        private  int width;
        private  int height;
        private  String path;
        private  Image image;
        private  ImageView imageView;
        
        
        
        private boolean clicked = false;
    
    public Buttons(String path,int x_pos, int y_pos, int width, int height, Group gameWindow) throws IOException
    {
       this.path = path;
       this.width = width;
       this.height = height;
       this.x_pos = x_pos;
       this.y_pos = y_pos;
      
       this.initButton(gameWindow);
    }
    private void initButton(Group gameWindow) throws IOException
    {
        this.image = new Image(Files.newInputStream(Paths.get(this.path)));
        this.imageView = new ImageView(this.image);
        this.imageView.setFitWidth(this.width);
        this.imageView.setFitHeight(this.height);
        this.imageView.setScaleX(2);
        this.imageView.setScaleY(2);
        this.imageView.setX(x_pos);
        this.imageView.setY(y_pos);
        gameWindow.getChildren().add(this.imageView);
    }
    
    public boolean isClicked()
    {
        if (this.clicked)
        {
            this.clicked = false;
            return true;
        }
        else
        {
            return false;
        }
    }
    public void update(){
        imageView.setOnMouseEntered((MouseEvent event) -> {
                imageView.setScaleX(2.1);
                imageView.setScaleY(2.1);
                imageView.setCursor(Cursor.HAND);
        });
            imageView.setOnMouseExited((MouseEvent event) -> {
                imageView.setScaleX(2);
                imageView.setScaleY(2);
        });
        
        this.imageView.setOnMouseClicked(event -> this.clicked = true);
    }
    public void renderButton()
    {
         this.imageView.setVisible(true);
    }

    
    
}
