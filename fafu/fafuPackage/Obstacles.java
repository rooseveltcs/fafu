package fafuPackage;

import java.awt.Rectangle;

/**
 * Obstacle class as superclasses of obstacles(was supposed to, but didn't add new obstacles, so only superclass for one)
 * constructs an obstacle of a subclass to be drawn in applet
 * 
 * @author Eric
 */
public class Obstacles{
    private int GROUND = 355+150;
    private int centerX = 100;
    private int centerY = GROUND;
    private int speedX;
    private Background bg = Main.getBg1();
    public static Rectangle rect = new Rectangle(0,0,0,0);
    
    /**
     * updates the position of the obstacle, speed is the same as the background
     */
    public void update(){
        centerX += speedX;
        speedX = bg.getSpeedX();
        if(centerX<0){
            centerX = (int)(Math.random()*5000)+800;
        }
        rect.setRect(centerX-50, centerY-50, 100, 100);
    }
    
    /**
     * code below method name explains its usage
     */
    public int getSpeedX(){
        return speedX;
    }
    
    public int getCenterX(){
        return centerX;
    }
    
    public int getCenterY(){
        return centerY;
    }
    
    public Background getBg(){
        return bg;
    }
    
    public void setSpeedX(int speedX){
        this.speedX = speedX;
    }
    
    public void setCenterX(int centerX){
        this.centerX = centerX;
    }
    
    public void setCenterY(int centerY){
        this.centerY = centerY;
    }
    
    public void setBg(Background bg){
        this.bg = bg;
    }
    
}
