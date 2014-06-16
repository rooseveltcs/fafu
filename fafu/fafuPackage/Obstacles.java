package fafuPackage;


/**
 * Write a description of class Obstacles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Obstacles{
    private int GROUND = 355+150;
    private int centerX = 100;
    private int centerY = GROUND;
    private int speedX;
    private Background bg = Main.getBg1();
    
    public void update(){
        centerX += speedX;
        speedX = bg.getSpeedX();
        if(centerX<0){
            centerX = (int)(Math.random()*5000)+800;
        }
    }
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
