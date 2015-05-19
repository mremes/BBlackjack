package mremes.bblackjack.logiikka;

public class Korttipakka {

    private Kortti kortit[];

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

    public Kortti[] kortit() {
        Kortti[] kortit = new Kortti[51];
        return kortit;
    }

    public Arvo[] annaArvot() {
        Arvo[] arvot = new Arvo[13];
        arvot[0] = Arvo.KAKKONEN;
        arvot[1] = Arvo.KOLMONEN;
        arvot[2] = Arvo.NELONEN;
        arvot[3] = Arvo.VITONEN;
        arvot[4] = Arvo.KUTONEN;
        arvot[5] = Arvo.SEISKA;
        arvot[6] = Arvo.KASI;
        arvot[7] = Arvo.YSI;
        arvot[8] = Arvo.KYMPPI;
        arvot[9] = Arvo.JATKA;
        arvot[10] = Arvo.ROUVA;
        arvot[11] = Arvo.KUNKKU;
        arvot[12] = Arvo.ASSA;

        return arvot;
    }

    public Kortti[] getKortit() {
        return this.kortit;
    }
}
