package fafuPackage.framework;

import java.awt.Image;
import java.util.ArrayList;

/**
 * Write a description of class Animation here.
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
    
    public Animation(){
        frames = new ArrayList();
        totalDuration = 0;

        synchronized(this){
            animTime = 0;
            currentFrame = 0;
        }
    }

    public synchronized void addFrame(Image image, long duration){
        totalDuration += duration;
        frames.add(new AnimFrame(image, totalDuration));
    }

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

    public synchronized Image getImage() {
        if (frames.size() == 0) {
            return null;
        }
        else {
            return getFrame(currentFrame).image;
        }
    }

    private AnimFrame getFrame(int i) {
        return (AnimFrame) frames.get(i);
    }

    private class AnimFrame {

        Image image;
        long endTime;

        public AnimFrame(Image image, long endTime) {
            this.image = image;
            this.endTime = endTime;
        }
    }
}
