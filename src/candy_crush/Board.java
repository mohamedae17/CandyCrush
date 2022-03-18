
package candy_crush;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class Board {
    //Fields
        private Random random = new Random();
        int index1x=-1,index1y=-1,index2x=-1,index2y=-1;
        
        private static DropShadow Effect;
        private static Rectangle Background;  
        public static int[][] gameBoard = new int[10][10];
        
        public static Spot Spots[][] = new Spot[10][10]; 
        Timeline fall; 
        KeyValue [][] keysy;
        KeyFrame [][] Frames;
        
    //Methods
        //Constructor
            public Board(Group Gamewindow){
                this.initEffect();
                this.initBackground(Gamewindow);
                this.initCandy(Gamewindow);
                this.initFallAnimation();
             
            }
            
        //Initialization methods
            private void initBackground(Group Gamewindow){
                 Background = new Rectangle(415,400);
                 Background.setX(350);
                 Background.setY(80);
                 Background.setOpacity(0.5);
                 Background.setArcHeight(45);  
                 Background.setArcWidth(45); 
                 Background.setFill(Color.HOTPINK);
                 Background.setStroke(Color.DEEPPINK); 
                 Background.setEffect(Effect);
                 Gamewindow.getChildren().add(Background);
            }
            private void initEffect(){
            
                 Effect = new DropShadow();
                 Effect.setWidth(40);
                 Effect.setHeight(40);
                 Effect.setOffsetX(20);
                 Effect.setOffsetY(20);
                 Effect.setRadius(45);
            }
            private  void initCandy(Group Gamewindow){
     
       //filling the arraies by -1 value to not be null and avoid null Exceptions
        for(int i=0;i<10;i++)
         {
            for(int j=0;j<10;j++)
            {
                gameBoard[i][j]=-1;
                Spots[i][j]=new Spot(-1,-1,-1,-1);
            }
         }   
       
       // intialize the values o the arrays of board and spots
       Random rand=new Random();
       for(int i=1;i<=8;i++)
       {
           for(int j=1;j<=8;j++)
           {
               int random=rand.nextInt(6);
               gameBoard[i][j]=random;
               Spots[i][j]=new Spot(25,382+(50*(i-1)),0,random);
               switch (random) {
                case 0:
                    Spots[i][j].getspot().setFill(GameMedia.getcandy(GameMedia.Sausage));
                    break;
                case 1:
                    Spots[i][j].getspot().setFill(GameMedia.getcandy(GameMedia.Planet));
                    break;
                case 2:
                    Spots[i][j].getspot().setFill(GameMedia.getcandy(GameMedia.Teardrop));
                    break;
                case 3:
                    Spots[i][j].getspot().setFill(GameMedia.getcandy(GameMedia.Flower));
                    break;
                case 4:
                    Spots[i][j].getspot().setFill(GameMedia.getcandy(GameMedia.Lozenge));
                    break;
                case 5:
                    Spots[i][j].getspot().setFill(GameMedia.getcandy(GameMedia.Pillow));
                }
                Gamewindow.getChildren().add(Spots[i][j].getspot()); 
           }
       }  
       if(matchs())
        {
            for(int i=1;i<=8;i++)
            {
                for(int j=1;j<=8;j++)
                {
                    Gamewindow.getChildren().remove(Spots[i][j].getspot()); 
                }
            
            }
            this.initCandy(Gamewindow);
        }
   }
            private void initFallAnimation(){
                  fall = new Timeline(); 
                  this.keysy = new KeyValue[9][9];
                  Frames = new KeyFrame[9][9];
                
                  
                  for(int i=1;i<=8;i++)                 
                    for(int j=1;j<=8;j++)
                    {         
                        this.keysy[i][j] = new KeyValue(Spots[i][j].getspot().centerYProperty(),105+((j-1)*50));
                        Frames[i][j] = new KeyFrame(Duration.seconds(1.6),keysy[i][j]);
                        fall.getKeyFrames().addAll(Frames[i][j]);
                    }
                  fall.play();
                    
            }
                    
        //update methods
            private void Activate(){
                int spotcounter=0;
                for(int i=1;i<=8;i++)
                {
                    for(int j=1;j<=8;j++)
                    {
                        if(Spots[i][j].getispressed())
                        {
                            spotcounter++;
                            if(spotcounter==1)
                            {
                                index1x=i;
                                index1y=j;
                            }
                            else
                            {
                                index2x=i;
                                index2y=j;
                                break;
                            }
                        }         
                    }
                }
            }
            private void Swap(){
                 
                if(index1x != -1 && index1y != -1 && index2x != -1 && index2y != -1)
                { 
                    int posx1=Spots[index1x][index1y].getposx(),posx2=Spots[index2x][index2y].getposx();
                    int posy1=Spots[index1x][index1y].getposy(),posy2=Spots[index2x][index2y].getposy();
                    if((posx1==posx2 &&Math.abs(posy1-posy2)==1)||(posy1==posy2 && Math.abs(posx1-posx2)==1)){
                        Timeline Switch =new Timeline(); 
                        double x=Spots[index1x][index1y].getspot().getLayoutX(),y=Spots[index1x][index1y].getspot().getCenterY();
                        double x1=Spots[index2x][index2y].getspot().getLayoutX(),y1=Spots[index2x][index2y].getspot().getCenterY();
                        KeyValue S1keyx=new KeyValue(Spots[index1x][index1y].getspot().layoutXProperty(),x1);
                        KeyValue S1keyy=new KeyValue(Spots[index1x][index1y].getspot().centerYProperty(),y1); 
                        KeyValue S2keyx=new KeyValue(Spots[index2x][index2y].getspot().layoutXProperty(),x);
                        KeyValue S2keyy=new KeyValue(Spots[index2x][index2y].getspot().centerYProperty(),y);
                        KeyFrame SFrames=new KeyFrame(Duration.seconds(0.3),S1keyy,S2keyy,S1keyx,S2keyx);
                        Switch.getKeyFrames().addAll(SFrames);
                        Switch.play(); 
                        
                        //switch
                        Switchspots(Board.Spots, index1x, index1y, index2x, index2y);
                        Switch(posy1,posx1,posy2,posx2);
                            
                        matchs();
                        delete();
                        
                        for(int i=1;i<=8;i++)
                        {for(int j=1;j<=8;j++)
                            {
                                System.out.print(Spots[i][j].match);
                            }
                            System.out.println("");
                        }
                        
                    }
                    
                     for(int i=1;i<=8;i++)
                     {
                        for(int j=1;j<=8;j++)
                        {
                            Spots[i][j].unpress();            
                        }
                     }
                }
                
                  for(int i=1;i<=8;i++)
                    {
                        for(int j=1;j<=8;j++)
                        {
                           Spots[i][j].press();            
                        }
                    } 
                  index1x=-1;
                  index1y=-1;
                  index2x=-1;
                  index2y=-1;
            }
            private boolean matchs(){
      boolean match=false;
       for(int i=1;i<=8;i++)
      {
          for(int j=1;j<=8;j++)
          {
            if(Spots[j][i].id == Spots[j+1][i].id)
                if(Spots[j][i].id == Spots[j-1][i].id)
                {
                    match=true;
                    for(int k=-1;k<=1;k++)
                    {
                        Spots[j+k][i].match=true;
                    }
                }
            if(Spots[j][i].id == Spots[j][i+1].id )
                if(Spots[j][i].id == Spots[j][i-1].id)
                {   match=true;
                    for(int k=-1;k<=1;k++)
                    {
                        Spots[j][i+k].match=true;
                    }
                    
                }
            
          }
      }
       return match;
   }
            private void delete(){
       
        for(int i=1;i<=8;i++)
            {
                for(int j=1;j<=8;j++)
                {
                    if(Spots[i][j].match)
                    {
                        Spots[i][j].getspot().setFill(Color.TRANSPARENT);
                        Board.gameBoard[j][i]= 8;
                        Spots[i][j].match=false;
                    }
                }
            }
         
   }
            private void Switch(int x,int y,int x1,int y1){
               int tempvalue=gameBoard[x][y];
               gameBoard[x][y]=gameBoard[x1][y1];
               gameBoard[x1][y1]=tempvalue;
            }
            private void Switchspots(Spot[][] s,int x1,int y1,int x2,int y2){
               Spot tempspot=s[x1][y1];
               s[x1][y1]=s[x2][y2];
               s[x2][y2]=tempspot;
            }
            private void movedown(Spot[][] spots){

                for(int i=8;i>=1;i--)
                {

                    for(int j=1;j<=8;j++)
                    {

                        if(gameBoard[i][j]==8)
                        {

                            for(int k=i;k>0;k--)
                            {
                                if(gameBoard[k][j]!=8)
                                {
                                    double y=spots[j][i].getspot().getCenterY();
                                    spots[j][i].getspot().setCenterY(spots[j][k].getspot().getCenterY());
                                    spots[j][k].getspot().setCenterY(y);

                                    break;

                                }

                            }
                        }


                    }


                }

            }
            
            public void update(){
                this.Activate();
                this.Swap();
            }
            
   
   public static void show(){
       
        for(int i=1;i<=8;i++)
       {
           for(int j=1;j<=8;j++)
           {
               System.out.print(gameBoard[i][j]);
           }
           System.out.println("");
       }
   }
     
}