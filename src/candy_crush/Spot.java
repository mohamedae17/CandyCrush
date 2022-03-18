
package candy_crush;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Spot {
    
    private Circle candy;
    private Boolean pressed;
    private static int pressedspots = 0;
    public boolean match = false;
    public int id;
  
    public Spot (int radius,int x,int y,int id){
        pressed=false;
        candy = new Circle(radius);
        this.id =id;
       
        candy.setLayoutX(x);
        candy.setLayoutY(y);
        candy.setFill(Color.TRANSPARENT);
        candy.setStroke(Color.TRANSPARENT);
    }
  
    public Circle getspot(){
        return candy;
    }
    
    public Boolean getispressed(){
        return pressed;
    }
    
    public int getposx(){
        return (int)((candy.getLayoutX()-382)/50)+1;
    }
    
    public int getposy(){
        return (int)((candy.getCenterY()-105)/50)+1;
    }
  
    public void press(){
        if(candy.isPressed()&& pressed == false  && pressedspots<2)
        {
            pressedspots++;
            candy.setStroke(Color.PINK);
            pressed=true;
        }
        else if(candy.isPressed()&& pressed == true)
        {
            pressedspots--;
            this.unpress();
        }
    }
    
    public void unpress(){
        pressed=false;
        pressedspots=0;
        candy.setStroke(Color.TRANSPARENT);
    }
}
