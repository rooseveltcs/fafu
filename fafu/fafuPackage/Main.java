package fafuPackage;

import fafuPackage.framework.Animation;
import java.util.*;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Main extends Applet implements Runnable, KeyListener{
    private Sprite fafu;
    private Crate crate1, crate2;
    private static Background bg1, bg2;
    private Image image, currentSprite, characterDown, characterJumped, background, character0, character1, character2, character3, character4, character5, character6, character7, character8, character9, character10, character11, crate;
    private Graphics second;
    private URL base;
    private Animation anim;
    private Animation animTop;
    private int characterSpeed;
    private int frames = 0;
    public static int temp =0;
    private boolean alive = true;

    @Override
    public void init(){
        setSize(800,480);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("Test");

        try {
            base = getDocumentBase();
        } catch (Exception e) {
            // TODO: handle exception
        }
        // Image Setups
        //Sprite running
        character0 = getImage(base, "character0.png");
        character1 = getImage(base, "character1.png");
        character2 = getImage(base, "character2.png");
        character3 = getImage(base, "character3.png");
        character4 = getImage(base, "character4.png");
        character5 = getImage(base, "character5.png");
        character6 = getImage(base, "character6.png");
//         character7 = getImage(base, "character07.png");
//         character8 = getImage(base, "character08.png");
//         character9 = getImage(base, "character09.png");
//         character10 = getImage(base, "character10.png");
//         character11 = getImage(base, "character11.png");

        characterDown = getImage(base, "down.png");
        characterJumped = getImage(base, "jumped.png");

        characterSpeed = 120;

        crate = getImage(base, "crate.png");

        anim = new Animation();
        while(characterSpeed > 17){
            anim.addFrame(character0, characterSpeed);
            anim.addFrame(character1, characterSpeed);
            anim.addFrame(character2, characterSpeed);
            anim.addFrame(character3, characterSpeed);
            anim.addFrame(character4, characterSpeed);
            anim.addFrame(character5, characterSpeed);
            anim.addFrame(character6, characterSpeed);
//             anim.addFrame(character7, characterSpeed);
//             anim.addFrame(character8, characterSpeed);
//             anim.addFrame(character9, characterSpeed);
//             anim.addFrame(character10, characterSpeed);
//             anim.addFrame(character11, characterSpeed);
            characterSpeed -= 1;
            frames+= 7;
        }
        animTop = new Animation();
        animTop.addFrame(character0, characterSpeed);
        animTop.addFrame(character1, characterSpeed);
        animTop.addFrame(character2, characterSpeed);
        animTop.addFrame(character3, characterSpeed);
        animTop.addFrame(character4, characterSpeed);
        animTop.addFrame(character5, characterSpeed);
        animTop.addFrame(character6, characterSpeed);
//         animTop.addFrame(character7, characterSpeed);
//         animTop.addFrame(character8, characterSpeed);
//         animTop.addFrame(character9, characterSpeed);
//         animTop.addFrame(character10, characterSpeed);
//         animTop.addFrame(character11, characterSpeed);

        currentSprite = animTop.getImage();
        background = getImage(base, "background.png");
    }

    @Override
    public void start(){
        bg1 = new Background(0, 0);
        bg2 = new Background(2160, 0);
        fafu = new Sprite();
        crate1 = new Crate(800+(int)Math.random()*500);
        crate2 = new Crate(1400+(int)Math.random()*500);
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void stop(){
    }

    @Override
    public void destroy(){
    }

    @Override
    public void run(){
        while(alive == true){
            fafu.update();
            crate1.update();
            crate2.update();
            this.temp = anim.temp;
            if (fafu.isJumped()){
                currentSprite = characterJumped;
            }
            else if (fafu.isJumped() == false && fafu.isDucked() == true){
                currentSprite = characterDown;
            }
            else if (fafu.isJumped() == false && fafu.isDucked() == false){
                if(anim.temp < frames){
                    currentSprite = anim.getImage();
                }
                else{
                    currentSprite = animTop.getImage();
                }
            }
            bg1.update();
            bg2.update();

            animate();
            //repaints objects
            repaint();
            //try sleep, if catches error, prints to console
            try{
                //sleep for 17 miliseconds everytime after painting object
                //makes approx 60fps
                Thread.sleep(17);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            isAlive();
        }
    }

    @Override
    public void update(Graphics g){
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            second = image.getGraphics();
        }
        second.setColor(getBackground());
        second.fillRect(0, 0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);

        g.drawImage(image, 0, 0, this);
    }

    public void animate(){
        anim.update(10);
        animTop.update(10);
    }

    @Override
    public void paint(Graphics g){
        g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
        g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
        g.drawImage(currentSprite, (int)fafu.getCenterX()-300, (int)fafu.getCenterY()-200, this);
        //-200 for boxes to make them disappear off screen
        g.drawImage(crate, (int)crate1.getCenterX()-300, (int)crate1.getCenterY() - 200, this);
        g.drawImage(crate, (int)crate2.getCenterX()-300, (int)crate2.getCenterY() - 200, this);
        g.drawRect((int)fafu.rect.getX(), (int)fafu.rect.getY(), (int)fafu.rect.getWidth(), (int)fafu.rect.getHeight());
    }

    @Override
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
            currentSprite = characterDown;
            if (fafu.isJumped() == false){
                fafu.setDucked(true);
            }
            break;

            case KeyEvent.VK_LEFT:
            break;

            case KeyEvent.VK_RIGHT:
            break;

            case KeyEvent.VK_UP:
            fafu.jump();
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
            if(anim.temp < frames){
                currentSprite = anim.getImage();
            }
            else{
                currentSprite = animTop.getImage();
            }
            fafu.setDucked(false);
            break;

            case KeyEvent.VK_LEFT:
            break;

            case KeyEvent.VK_RIGHT:
            break;

            case KeyEvent.VK_UP:
            if(fafu.isJumped() == true){
            }
            else{

            }
            break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e){
    }

    public void isAlive(){
        if(Math.abs(fafu.getCenterX() - crate1.getCenterX())<= 90){
            this.alive = false;
        }
    }

    public static Background getBg1() {
        return bg1;
    }

    public static Background getBg2() {
        return bg2;
    }
}
