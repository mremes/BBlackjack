package logiikka.cards;

import logiikka.cards.Maa;
import logiikka.cards.Kortti;
import logiikka.cards.Arvo;

/**
 * Korttipakka sisältää 52 pelikorttia: 13 korttia neljällä eri maalla. Olio
 * sisältää nämä kaikki Kortti-oliot taulukossa. Konstruktori konstruoi
 * korttipakan.
 */
public class Korttipakka {

    private Kortti kortit[];

    public Korttipakka() {
        this.kortit = new Kortti[52];
        int i = 0;
        for (Arvo arvo : annaArvot()) {
            kortit[i] = new Kortti(Maa.HERTTA, arvo);
            kortit[i + 1] = new Kortti(Maa.RUUTU, arvo);
            kortit[i + 2] = new Kortti(Maa.PATA, arvo);
            kortit[i + 3] = new Kortti(Maa.RISTI, arvo);
            i = i + 4;
        }

    }

    /**
     * Palauttaa Kortti-oliot taulukossa
     *
     * @return 52 Kortti-oliota taulukossa (ei sekoitettu)
     */
    public Kortti[] getKortit() {
        return this.kortit;
    }
    // APUMETODI

    public Arvo[] annaArvot() {
        Arvo[] arvot = new Arvo[13];
        for (int i = 0; i < 13; i++) {
            arvot[i] = Arvo.values()[i];
        }
        return arvot;
    }
}
