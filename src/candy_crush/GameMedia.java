/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candy_crush;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class GameMedia {
    //Fields
        public static Image Gamebackground,Timeboard,Iconimage; 
        public static Image Sausage,Lozenge,Teardrop,Pillow,Planet,Flower;
        public static Image[] Animatedcandy = new Image[2];
        
        public static MediaPlayer Backgroundsong ; 
        
        public static Font Gamefont;
    
    //Methods
        //Constructor
            public GameMedia() throws IOException
            {
                this.initBackGround();
                this.initTimerBoard();
                this.initCandies();
                this.initAnimation();
                this.initSounds();
                this.initFont();
            }
            
        //Initialization Methods
            private void initBackGround() throws IOException
            {
                Gamebackground = new Image(Files.newInputStream(Paths.get("Candy Crush things\\Game.png")));
            }
            private void initTimerBoard() throws IOException
            {
                Timeboard = new Image(Files.newInputStream(Paths.get("Candy Crush things\\Timeboard.png")),100,50,false,true);
                Iconimage = new Image(Files.newInputStream(Paths.get("Candy Crush things\\Icon.png")));
            }
            private void initCandies() throws IOException
            {
                Sausage = new Image(Files.newInputStream(Paths.get("Candy Crush things\\a1.png")));
                Planet= new Image(Files.newInputStream(Paths.get("Candy Crush things\\b1.png")));
                Teardrop = new Image(Files.newInputStream(Paths.get("Candy Crush things\\j1.png")));
                Lozenge = new Image(Files.newInputStream(Paths.get("Candy Crush things\\o1.png")));
                Pillow = new Image(Files.newInputStream(Paths.get("Candy Crush things\\p1.png")));
                Flower = new Image(Files.newInputStream(Paths.get("Candy Crush things\\g1.png")));
            }
            private void initAnimation() throws IOException
            {
                 for(int i=1;i<=2;i++)
                {
                   Animatedcandy[i-1]=new Image(Files.newInputStream(Paths.get("Candy Crush things\\"+"a"+i+".png")));
                }
            }
            private void initSounds()
            {
                 Backgroundsong = new MediaPlayer(new Media(Paths.get("Candy Crush things\\Song.mp3").toUri().toString()));
            }
            private void initFont()
            {
                 Gamefont =  Font.font("Times New Roman", FontWeight.NORMAL, 25);
            }
        //Update Methods
            public void updateMusic(String s)
            {
                if(s.equals("play")) Backgroundsong.play();
                else if(s.equals("stop")) Backgroundsong.pause();
            }
    
    public static ImagePattern getcandy(Image image)
    {
        return new ImagePattern(image);
    }
    
   
    
    
}
