package Main;

import java.applet.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import sun.audio.*;

public class Sound {

    private InputStream in;
    private AudioStream as;
    private String fileName;

    public static final Sound bgsound = new Sound("src/main/resources/sounds/casinosound.wav");
    public static final Sound hit = new Sound("src/main/resources/sounds/cardSlide.wav");
    public static final Sound split = new Sound("src/main/resources/sounds/split.wav");
    public static final Sound result = new Sound("src/main/resources/sounds/final.wav");
    public static final Sound deal = new Sound("src/main/resources/sounds/deal.wav");

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
        Timer t = new Timer(95000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileInputStream fis = null;
                AudioStream as = null;
                try {
                    fis = new FileInputStream("src/main/resources/sounds/casinosound.wav");
                    as = new AudioStream(fis);

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
                }

                AudioPlayer.player.start(as);
            }
        });
        AudioPlayer.player.start(as);
        t.start();
        
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
