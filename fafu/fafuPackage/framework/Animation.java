package fafuPackage.framework;

import java.awt.Image;
import java.util.ArrayList;

/**
 * Animation class
 * Handles the animation of the sprite's running animation
 * Basically an arrayList that stores all the pictures as well as the speed of each picture frame
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Animation
{
    private ArrayList frames;
    private int currentFrame;
    private long animTime; //long takes up more memory than int but can hold more accurate numbers.
    private long totalDuration;
    public int temp =0;
    
    /**
     * constructor for an Animation
     */
    public Animation(){
        frames = new ArrayList();
        totalDuration = 0;

        synchronized(this){
            animTime = 0;
            currentFrame = 0;
        }
    }

    /**
     * adds a frame of picture and its duration on screen
     * similar to the add method of ArrayList, but adds a duration that makes the picture stay on the applet for a duration of time
     */
    public synchronized void addFrame(Image image, long duration){
        totalDuration += duration;
        frames.add(new AnimFrame(image, totalDuration));
    }

    /**
     * updates the picture of the animation
     * sets the picture to show depending on its elapsed time/frame
     */
    public synchronized void update(long elapsedTime) {
        if (frames.size() > 1) {
            animTime += elapsedTime;
            if (animTime >= totalDuration) {
                animTime = animTime % totalDuration;
                currentFrame=0;
            }

            while (animTime > getFrame(currentFrame).endTime) {
                currentFrame++;
                temp++;
            }
        }
    }
    
    /**
     * gets the image of a picture in the Animation/ArrayList to be drawn in Main
     */
    public synchronized Image getImage() {
        if (frames.size() == 0) {
            return null;
        }
        else {
            return getFrame(currentFrame).image;
        }
    }

    /**
     * same as get(int something), returns a picture at that position of the Animation
     */
    private AnimFrame getFrame(int i) {
        return (AnimFrame) frames.get(i);
    }

    /**
     * class within the Animation that constructs an AnimFrame that takes in a picture and duration
     */
    private class AnimFrame {

        Image image;
        long endTime;

        /**
         * constructor for the AnimFrame, takes in a picture and a duration, used in addFrame method
         */
        public AnimFrame(Image image, long endTime) {
            this.image = image;
            this.endTime = endTime;
        }
    }
}
