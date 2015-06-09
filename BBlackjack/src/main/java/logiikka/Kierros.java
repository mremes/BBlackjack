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

/**
 * Luokka kuvaa yhtä Black Jack -korttipelin pelattua kierrosta ja sisältää
 * kierrokseen liittyviä toiminnallisuuksia.
 * @author mrremes
 */
public class Kierros {

    private Pelaaja pelaaja;
    private ArrayList<Kasi> pelaajanKadet;
    private boolean splitattu;
    private Kasi jakajanKasi;
    private int panos;
/**
     * Luo Kierros-luokan ilmentymän, ts. yhden kierroksen. Konstruktorille
     * syötetään Pelaaja-olio, johon kierroksen lopputulema vaikuttaa.
     * 
     * @param pelaaja Pelaaja, jolta veloitetaan kierroksen panos ja joka
     * saa mahdolliset voitot
     * 
     */
    public Kierros(Pelaaja pelaaja) {
        this.pelaaja = pelaaja;
        this.pelaajanKadet = new ArrayList();
        this.panos = 0;
        this.splitattu = false;
    }

    /**
     * määrittelee pelaajan ensimmäisen alkukäden sekä jakajan käden
     */
    public void jaaKadet() {
        if (Jakaja.jaljellaKortteja() < 80) {
            Jakaja.sekoitaKortit();
        }
        pelaajanKadet.add(Jakaja.uusiKasi());
        jakajanKasi = Jakaja.uusiKasi();
        jakajanKasi.setDealer();
    }

    /**
     * määrittelee käden valmiiksi tulosta varten
     * @param k käsi, johon metodi kohistuu
     */
    public void stand(Kasi k) {
        k.setValmis();
    }
    /**
     * Jakaa käden kahtia ja määrittelee uudet kädet. Vanhasta kädestä otetaan
     * molempiin uusiin käsiin yksi kortti ja Jakaja-olio jakaa toisen kortin
     * korttipakasta.
     * @param hand käsi, johon metodi kohdistuu
     */
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
    /**
     * lisää yhden (1) Kortti-olion parametrina annettuun käteen
     * @param k käsi, johon metodi kohdistuu
     */
    public void hit(Kasi k) {
        k.addKortti(Jakaja.annaKortti());
    }
    /**
     * lisää yhden (1) Kortti-olion parametrina annettuun Käsi-olioon ja määrittelee
     * Käsi-olion valmiiksi. Parametrina annettu käsi määritellään tuplatuksi ja
     * Pelaaja-oliota veloitetaan toisen panoksen verran.
     * @param k käsi, johon metodi kohdistuu
     */
    public void doubl(Kasi k) {
        k.addKortti(Jakaja.annaKortti());
        k.setDoubled();
        pelaaja.veloita(panos);
        k.setValmis();

    }

    // GETTERIT JA SETTERIT
    /**
     * Palauttaa Kierroksen pelaajan
     * @return palauttaa Pelaaja-olion
     */
    public Pelaaja getPelaaja() {
        return this.pelaaja;
    }
    /**
     * Palauttaa Kierros-olion kaikki pelaajan Käsi-oliot ArrayListinä.
     * @return palauttaa ArrayList<Kasi> olion.
     */
    public ArrayList<Kasi> getPelaajanKadet() {
        return this.pelaajanKadet;
    }
    /**
     * Palauttaa jakajan Kasi-olion
     * @return 
     */
    public Kasi getJakajanKasi() {
        return this.jakajanKasi;
    }
    /**
     * Palauttaa kierroksen Panoksen kokonaislukuna
     * @return palauttaa panoksen
     */
    public int getPanos() {
        return this.panos;
    }
    /**
     * Määrittelee kierroksen panoksen
     * @param panos panoksen koko kokonaislukuna
     */
    public void setPanos(int panos) {
        this.pelaaja.veloita(panos);
        this.panos = panos;
    }
    /**
     * Kertoo, onko kierroksella splitattu käsiä.
     * @return onko splitattu / ei ole splitattu (t/f)
     */
    public boolean splitattu() {
        return this.splitattu;
    }
    /**
     * Määrittelee vakuutuksen parametrina annetulle kädelle
     * @param k Kasi-olio, jolle vakuutus määritetään.
     */
    public void setInsurance(Kasi k) {
        k.setInsurance();
        pelaaja.veloita(panos / 2);
        if (jakajanKasi.isBlackjack()) {
            k.setValmis();
            jakajanKasi.setOpen();
        }
    }
    /**
     * Palauttaa pelaajan alkukäden
     * @return Kasi-olio, joka on määritetty pelaajalle ensimmäisenä.
     */
    public Kasi getPelaajanKasi() {
        Kasi k = null;
        for (Kasi kasi : pelaajanKadet) {
            k = kasi;
            break;
        }
        return k;
    }
    /**
     * Palauttaa parametrina annetun Käsi-olion viimeisen kortin
     * @param k Käsi-olio, josta viimeinen kortti halutaan
     * @return  Kortti-olio, joka on Käsi-olion viimeiseksi lisätty kortti
     */
    public Kortti getVikaKortti(Kasi k) {
        return k.getKortti(k.getKortit().size() - 1);
    }
    /**
     * käytetään, kun pelaaja on kokonaisuudessaan valmis, jolloin on jakajan
     * vuoro ottaa kortteja.
     * @throws InterruptedException tekstikäyttöliittymässä viive (Thread.sleep)
     */
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
