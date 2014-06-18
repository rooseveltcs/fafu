package fafuPackage;

/**
 * Background class that sets the background on the applet, xy pos and its scrolling speed
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Background{

    public int bgX, bgY;
    public int speedX;
    
    /**
     * constructor for a background
     */
    public Background(int x, int y){
        bgX = x;
        bgY = y;
        speedX = 0;
    }

    /**
     * updates the backgrounds scrolling speed
     */
    public void update() {
        bgX += speedX;
        
        if (bgX <= -2160){
            bgX += 4320;
        }
    }

    public int getBgX() {
        return bgX;
    }

    public int getBgY() {
        return bgY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setBgX(int bgX) {
        this.bgX = bgX;
    }

    public void setBgY(int bgY) {
        this.bgY = bgY;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }
}
