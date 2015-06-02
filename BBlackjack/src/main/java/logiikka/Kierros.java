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
    private ArrayList<Kasi> pelaajanKadet;
    private boolean splitattu;
    private Kasi jakajanKasi;
    private int panos;

    public Kierros(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
        this.pelaajanKadet = new ArrayList();
        this.panos = 0;
        this.splitattu = false;
    }

    // JAKAA KADET
    public void jaaKadet() {
        if (Jakaja.jaljellaKortteja() < 80) {
            Jakaja.sekoitaKortit();
        }
        pelaajanKadet.add(Jakaja.uusiKasi());
        jakajanKasi = Jakaja.uusiKasi();
        jakajanKasi.setDealer();
    }

    // TOIMINTAMETODIT
    public void stand(Kasi k) {
        k.setValmis();
    }

    public void split(Kasi hand) {
        hand.setValmis();
        this.splitattu = true;
        Kortti k1 = getPelaajanKasi().getKortti(0);
        Kortti k2 = getPelaajanKasi().getKortti(1);
        pelaajanKadet.clear();
        pelaajanKadet.add(new Kasi(k1, Jakaja.annaKortti()));
        pelaajanKadet.add(new Kasi(k2, Jakaja.annaKortti()));

        for (Kasi k : pelaajanKadet) {
            k.setSplitted();
            if (k1.getArvo() == Arvo.ASSA && k2.getArvo() == Arvo.ASSA) {
                k.setValmis();
            }
        }

        pelaaja.veloita(panos);
    }

    public void hit(Kasi k) {
        k.addKortti(Jakaja.annaKortti());
    }

    public void doubl(Kasi k) {
        k.addKortti(Jakaja.annaKortti());
        k.setDoubled();
        pelaaja.veloita(panos);
        setPanos(getPanos() * 2);
        k.setValmis();

    }

    // GETTERIT JA SETTERIT
    public Pelaaja getPelaaja() {
        return this.pelaaja;
    }

    public ArrayList<Kasi> getPelaajanKadet() {
        return this.pelaajanKadet;
    }

    public Kasi getJakajanKasi() {
        return this.jakajanKasi;
    }

    public int getPanos() {
        return this.panos;
    }

    public void setPanos(int panos) {
        this.pelaaja.veloita(panos);
        this.panos = panos;
    }
    
    public boolean splitattu() {
        return this.splitattu;
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
        for (Kasi kasi : pelaajanKadet) {
            k = kasi;
            break;
        }
        return k;
    }

    public Kortti getVikaKortti() {
        Kasi k = null;
        for (Kasi kasi : pelaajanKadet) {
            k = kasi;
            break;
        }
        return k.getKortti(k.getKortit().size() - 1);
    }

    public void jakajanKasi() throws InterruptedException {
        jakajanKasi.setOpen();
        if (KierrosUtil.dealerOttaa(pelaajanKadet)) {
            System.out.print("Dealer: " + jakajanKasi.kortit());
            while (jakajanKasi.getArvo() < 17 && !KierrosUtil.pelaajaAllBj(pelaajanKadet)) {
                jakajanKasi.addKortti(Jakaja.annaKortti());
                Thread.sleep(1000);
                System.out.print(Jakaja.edellinenKortti() + " ");
            }
            System.out.println(jakajanKasi.getArvoS());
        }
        for (Kasi k : pelaajanKadet) {
            if (k.isSplitted()) {
                System.out.println("");
                break;
            }
        }
    }
}
