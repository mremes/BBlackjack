package logiikka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import logiikka.cards.Arvo;
import logiikka.cards.Kortti;
import logiikka.cards.Maa;
import logiikka.elements.Kasi;
import logiikka.elements.Pelaaja;
import logiikka.utilities.Jakaja;
import logiikka.utilities.KierrosUtil;
import logiikka.utilities.TulosPrint;

public class Kierros {

    private Pelaaja pelaaja;
    private HashMap<Kasi, Integer> pelaajanKadet;
    private Kasi jakajanKasi;
    private int panos;

    public Kierros(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
        this.pelaajanKadet = new HashMap();
    }

    // JAKAA KADET
    public void jaaKadet() {
        pelaajanKadet.put(Jakaja.uusiKasi(), panos);
        jakajanKasi = Jakaja.uusiKasi();
        jakajanKasi.setDealer();
    }

    // TOIMINTAMETODIT
    public void stand(Kasi k) {
        k.setValmis();
    }

    public void split(Kasi hand) {
        hand.setValmis();
        Kortti k1 = null;
        Kortti k2 = null;
        Kasi kasi = null;
        for (Kasi k : pelaajanKadet.keySet()) {
            kasi = k;
            k1 = k.getKortti(0);
            k2 = k.getKortti(1);
            break;
        }
        pelaajanKadet.put(new Kasi(k1, Jakaja.annaKortti()), panos);
        pelaajanKadet.put(new Kasi(k2, Jakaja.annaKortti()), panos);

        for (Kasi k : pelaajanKadet.keySet()) {
            k.setSplitted();
            if (k1.getArvo() == Arvo.ASSA && k2.getArvo() == Arvo.ASSA) {
                k.setValmis();
            }
        }

        pelaaja.veloita(panos);
        pelaajanKadet.remove(kasi);
    }

    public void hit(Kasi k) {
        k.addKortti(Jakaja.annaKortti());
    }

    public void doubl(Kasi k) {
        k.addKortti(Jakaja.annaKortti());
        k.setDoubled();
        pelaaja.veloita(panos);
        k.setValmis();

    }

    // GETTERIT JA SETTERIT
    public Pelaaja getPelaaja() {
        return this.pelaaja;
    }

    public HashMap<Kasi, Integer> getPelaajanKadet() {
        return this.pelaajanKadet;
    }

    public Kasi getJakajanKasi() {
        return this.jakajanKasi;
    }

    public int getPanos() {
        return this.panos;
    }

    public void setPanos(int panos) {
        this.panos = panos;
        pelaaja.veloita(panos);
    }

    public void setInsurance(Kasi k) {
        k.setInsurance();
        pelaaja.veloita(panos / 2);
        if (jakajanKasi.isBlackjack()) {
            k.setValmis();
            jakajanKasi.setOpen();
        }
    }

    public Kasi getPelaajanKasi() {
        Kasi k = null;
        for (Kasi kasi : pelaajanKadet.keySet()) {
            k = kasi;
            break;
        }
        return k;
    }

    public Kortti getVikaKortti() {
        Kasi k = null;
        for (Kasi kasi : pelaajanKadet.keySet()) {
            k = kasi;
            break;
        }
        return k.getKortti(k.getKortit().size() - 1);
    }
}
