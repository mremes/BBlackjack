package sounds;

import java.applet.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;
import sun.audio.*;

/**
 * Luokka soittaa ääniä.
 *
 * @author mrremes
 */
public class Aani {

    private AudioInputStream as;
    private String tiedostonNimi;
    private Clip clip;

    public static final Aani bgsound = new Aani("casinosound.wav");
    public static final Aani hit = new Aani("cardSlide.wav");
    public static final Aani split = new Aani("split.wav");
    public static final Aani result = new Aani("final.wav");
    public static final Aani deal = new Aani("deal.wav");

    public Aani(String tiedostonNimi) {
        this.tiedostonNimi = tiedostonNimi;

        try {
            this.as = AudioSystem.getAudioInputStream(this.getClass().getResource(tiedostonNimi));
            this.clip = AudioSystem.getClip();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Aani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Aani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Aani.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Aani() {

    }
    /**
     * Soittaa ääntä tauotta (loopattuna)
     */
    public void soitaLoopattuna() {
        try {
            clip.open(as);
            clip.loop(clip.LOOP_CONTINUOUSLY);
        } catch (IOException ex) {
            Logger.getLogger(Aani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Aani.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void soita() {
        try {
            AudioInputStream as = AudioSystem.getAudioInputStream(this.getClass().getResource(tiedostonNimi));
            Clip clip = AudioSystem.getClip();
            clip.open(as);
            clip.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Aani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Aani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Aani.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * Lopettaa äänentoiston.
     */
    public void pysayta() {
        clip.stop();
    }
    /**
     * Vaimentaa (mute) äänen
     */
    public void vaimenna() {
        Mixer.Info[] infos = AudioSystem.getMixerInfo();
        for (Mixer.Info info : infos) {
            Mixer mixer = AudioSystem.getMixer(info);
            for (Line line : mixer.getSourceLines()) {
                BooleanControl bc = (BooleanControl) line.getControl(BooleanControl.Type.MUTE);
                if (bc != null) {
                    bc.setValue(true);
                }
            }
        }
    }
    /**
     * Ottaa äänen vaimennuksen (mute) pois
     */
    public void vaimennaPois() {

        Mixer.Info[] infos = AudioSystem.getMixerInfo();
        for (Mixer.Info info : infos) {
            Mixer mixer = AudioSystem.getMixer(info);
            for (Line line : mixer.getSourceLines()) {
                BooleanControl bc = (BooleanControl) line.getControl(BooleanControl.Type.MUTE);
                if (bc != null) {
                    bc.setValue(false);
                }
            }
        }
    }
}
