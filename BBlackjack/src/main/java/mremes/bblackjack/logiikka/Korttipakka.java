package mremes.bblackjack.logiikka;

import java.util.ArrayList;

public class Korttipakka {

    private Kortti kortit[];

    public Korttipakka() {
        this.kortit = kortit();

    }
    
    public Kortti[] kortit() {
        String[] arvot = annaArvot();
        Maa[] maat = annaMaat();
        Kortti[] kortit = new Kortti[51];
        int i = 0;
        for (Maa maa : maat) {
            for (String arvo : arvot) {
                kortit[i] = new Kortti(maa, arvo);
                i++;
            }
        }
        return kortit;
    }

    public String[] annaArvot() {
        String[] arvot = new String[12];
        arvot[0] = "2";
        arvot[1] = "3";
        arvot[2] = "4";
        arvot[3] = "5";
        arvot[4] = "6";
        arvot[5] = "7";
        arvot[6] = "8";
        arvot[7] = "9";
        arvot[8] = "T";
        arvot[9] = "J";
        arvot[10] = "Q";
        arvot[11] = "K";
        arvot[12] = "A";

        return arvot;
    }

    public Maa[] annaMaat() {
        Maa[] maat = new Maa[3];
        maat[0] = Maa.HERTTA;
        maat[1] = Maa.PATA;
        maat[2] = Maa.RISTI;
        maat[3] = Maa.RUUTU;
        return maat;

    }
    
    public Kortti[] getKortit() {
        return this.kortit;
    }
}
