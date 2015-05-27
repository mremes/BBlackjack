package mremes.bblackjack.deckofcards;

import mremes.bblackjack.deckofcards.Maa;
import mremes.bblackjack.deckofcards.Kortti;
import mremes.bblackjack.deckofcards.Arvo;

public class Korttipakka {

    private Kortti kortit[];
    // KORTTIPAKKA KONSTRUOIDAAN KONSTRUKTORISSA
    public Korttipakka() {
        this.kortit = new Kortti[52];
        int i = 0;
        for (Arvo arvo : annaArvot()) {
            kortit[i] = new Kortti(Maa.HERTTA, arvo);
            kortit[i+1] = new Kortti(Maa.RUUTU, arvo);
            kortit[i+2] = new Kortti(Maa.PATA, arvo);
            kortit[i+3] = new Kortti(Maa.RISTI, arvo);
            i = i + 4;
        }

    }
    
    // PALAUTTAA KORTIT
    public Kortti[] getKortit() {
        return this.kortit;
    }
    // APUMETODI
    public Arvo[] annaArvot() {
        Arvo[] arvot = new Arvo[13];
        for(int i = 0; i < 13; i++) {
            arvot[i] = Arvo.values()[i];
        }
        return arvot;
    }
}
