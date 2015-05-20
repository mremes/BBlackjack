package mremes.bblackjack.logiikka;

import java.util.ArrayList;

public class Paikka implements Comparable<Paikka> {
    private int nro;
    private Kasi kasi;
    private Kasi splitted;
    
    public Paikka(int nro) {
        this.nro = nro;
        this.kasi = null;
        this.splitted = null;
    }

    public void setKasi(Kasi kasi) {
        this.kasi = kasi;
    }
    
    public void split(Kortti k1, Kortti k2) {
        Kasi ekaKasi = new Kasi(this.kasi.getKortti(0), k1);
        Kasi tokaKasi = new Kasi(this.kasi.getKortti(1), k2);
        this.kasi = ekaKasi;
        this.splitted = tokaKasi;
    }

    public void setDoubled(Kasi splitted) {
        this.splitted = splitted;
    }

    public Kasi getKasi() {
        return this.kasi;
    }

    public Kasi getSplitted() {
        return this.splitted;
    }
    
    public ArrayList<Kasi> getKadet() {
        ArrayList<Kasi> kadet = new ArrayList();
        kadet.add(this.kasi);
        kadet.add(this.splitted);
        return kadet;
    }

    public int getNro() {
        return nro;
    }

    @Override
    public int compareTo(Paikka k) {
        return this.nro - k.nro;
    }
}
