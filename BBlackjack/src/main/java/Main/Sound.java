package Main;

import java.applet.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.*;

public class Sound {

    private InputStream in;
    private AudioStream as;
    private String fileName;
    
    public static final Sound bgsound = new Sound("BBlackjack/target/classes/sounds/casinosound.wav");
    public static final Sound hit = new Sound("BBlackjack/target/classes/sounds/cardSlide.wav");
    public static final Sound split = new Sound("BBlackjack/target/classes/sounds/split.wav");
    public static final Sound result = new Sound("BBlackjack/target/classes/sounds/final.wav");
    public static final Sound deal = new Sound("BBlackjack/target/classes/sounds/deal.wav");




    public Sound(String fileName) {
        this.fileName = fileName;
        try {
            this.in = new FileInputStream(fileName);
            this.as = new AudioStream(this.in);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public Sound() {

    }

    public void playLooped() {
        AudioPlayer.player.start(this.as);

    }

    public void play() {
        try {
            in = new FileInputStream(fileName);
            as = new AudioStream(this.in);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AudioPlayer.player.start(this.as);
    }

}
