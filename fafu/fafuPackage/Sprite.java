package fafuPackage;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Write a description of class Sprite here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sprite
{
    final double JUMPSPEED = -31;
    final int MOVESPEED = 5;
    public int GROUND = 382;

    private int centerX = 100;
    private int centerY = GROUND;
    private boolean jumped = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean ducked = false;

    private int initialSpeed = 5;
    private int speedX = initialSpeed;
    private double speedY = 0;
    
    //box 290,88 to 230,278
    public static Rectangle rect = new Rectangle(0,0,0,0);
    
    private Background bg1 = Main.getBg1();
    private Background bg2 = Main.getBg2();

    public void update() {
        // Moves Character or Scrolls Background accordingly.
        if(speedX<35){
            speedX = initialSpeed + Main.temp/30;
        }//728
        if (speedX < 0) {
            centerX += speedX;
        }
        if (speedX == 0 || speedX < 0) {
            bg1.setSpeedX(0);
            bg2.setSpeedX(0);

        }
        if (centerX <= 200 && speedX > 0) {
            centerX += speedX;
        }
        if (speedX > 0 && centerX > 200){
            bg1.setSpeedX(-speedX);
            bg2.setSpeedX(-speedX);
        }

        // Updates Y Position
        centerY += speedY;
        if (centerY + speedY >= GROUND) {
            centerY = GROUND;
        }

        // Handles Jumping
        if (jumped == true) {
            speedY += 1.0;
            if (centerY + speedY == GROUND) {
                jumped = false;
                centerY = GROUND;
                speedY = 0;
            }
        }
        
        // Prevents going beyond X coordinate of 0
        if (centerX + speedX <= 60) {
            centerX = 61;
        }
        rect.setRect(centerX-70, centerY-112, 60, 200);
    }

    public void moveRight() {
    }

    public void moveLeft() {
    }

    public void stopRight() {
    }

    public void stopLeft() {
    }

    private void stop() {
    }

    public void jump() {
        if (jumped == false) {
            speedY = JUMPSPEED;
            jumped = true;
        }
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public boolean isJumped() {
        return jumped;
    }

    public int getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setJumped(boolean jumped) {
        this.jumped = jumped;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isDucked() {
        return ducked;
    }

    public void setDucked(boolean ducked) {
        this.ducked = ducked;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }
}