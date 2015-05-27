package mremes.bblackjack.tui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import mremes.bblackjack.deckofcards.Arvo;
import mremes.bblackjack.deckofcards.Kortti;
import mremes.bblackjack.deckofcards.Maa;
import mremes.bblackjack.elements.Kasi;
import mremes.bblackjack.elements.Pelaaja;
import mremes.bblackjack.utilities.Jakaja;
import mremes.bblackjack.utilities.KierrosUtil;
import mremes.bblackjack.utilities.Tulos;

public class Kierros {

    private Pelaaja pelaaja;
    private HashMap<Kasi, Integer> pelaajanKadet;
    private Kasi jakajanKasi;
    private Scanner lukija;
    private int panos;

    public Kierros(Pelaaja pelaaja, Scanner lukija) {
        this.pelaaja = pelaaja;
        this.pelaajanKadet = new HashMap();
        this.lukija = lukija;

    }

    // PELAA YHDEN KIERROKSEN
    public void pelaaKierros() throws InterruptedException {
        panostus();
        jaaKadet();
        dealer();
        while (!KierrosUtil.pelaajaValmis(pelaajanKadet)) {
            pelaaja();
            for (Kasi k : pelaajanKadet.keySet()) {
                while (!k.isValmis()) {
                    actions(k);
                }
            }
        }
        tulos();
    }

    public void jaaKadet() {
        pelaajanKadet.put(Jakaja.uusiKasi(), panos);
        jakajanKasi = Jakaja.uusiKasi();
        jakajanKasi.setDealer();
    }

    // SOUT-TULOSTUSMETODIT
    public void pelaaja() {
        System.out.println("");
        for (Kasi k : pelaajanKadet.keySet()) {
            System.out.println("You: " + k);
        }
    }

    public void dealer() {
        System.out.print("Dealer: " + jakajanKasi);
    }

    // PANOSTUS- JA VAKUUTUSMETODI
    public void panostus() {
        System.out.println("\nYour stack: " + pelaaja.getBalance());
        System.out.print("Place your bet: ");
        panos = Integer.parseInt(lukija.nextLine());
        pelaaja.veloita(panos);
    }

    public void insurance(Kasi k) throws InterruptedException {
        System.out.print("INSURANCE? (Y/N) ");
        String syotto = lukija.nextLine();
        if (syotto.equals("Y")) {
            k.setInsurance();
            System.out.print("\nChecking.");
            Thread.sleep(750);
            System.out.print(".");
            Thread.sleep(750);
            System.out.println(".");
            pelaaja.veloita(panos / 2);
            if (!jakajanKasi.isBlackjack()) {
                System.out.println("Insurance lost.");
            }
        }
        if (jakajanKasi.isBlackjack()) {
            k.setValmis();
            jakajanKasi.setOpen();
        }
    }

    // ACTION-METODIT
    public void actions(Kasi k) throws InterruptedException {
        if (k.isBlackjack()) {
            k.setValmis();
        } else {
            if (KierrosUtil.voiVakuuttaa(jakajanKasi, k, pelaajanKadet)) {
                insurance(k);
            }
            if (!k.isValmis()) {
                if (pelaajanKadet.keySet().size() > 1) {
                    System.out.println("\nYou: " + k);
                }
                komento(k);
            }
        }
    }

    public void komento(Kasi k) {
        System.out.println(k.doables(pelaajanKadet.size()));
        String komento = lukija.nextLine();
        if (komento.equals("HIT")) {
            hit(k);
            if (!k.isBust() && !k.isSplitted()) {
                System.out.println("\nYou: " + k);
            }
        } else if (komento.equals("STAND")) {
            stand(k);
        } else if (k.korttienLkm() == 2) {
            if (komento.equals("DOUBLE")) {
                doubl(k);
            } else if (komento.equals("SPLIT")) {
                split(k);
            }
        }
        if (k.getArvo() >= 21) {
            if (k.isBust() && pelaajanKadet.size() > 1) {
                System.out.println("\nYou: " + k);
            }
            k.setValmis();
        }
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
        System.out.print("\nYou: " + k);


    }

    // TULOSMETODIT
    public void tulos() throws InterruptedException {
        jakajanKasi();
        for (Kasi k : pelaajanKadet.keySet()) {
            int panos = this.panos;
            if(k.isDoubled()) {
                panos *= 2;
            }
            Tulos tulos = new Tulos(pelaaja, k, jakajanKasi, panos);
            if (!k.isDoubled() && !jakajanKasi.isBlackjack()) {
                System.out.println("You: " + k);
                tulos.tulos(pelaajanKadet.keySet().size());
            } else if (!jakajanKasi.isBust() && !k.isSplitted() && !k.isBlackjack()) {
                if (!k.isDoubled()) {
                    System.out.println("You: " + k);
                }
                tulos.tulos(pelaajanKadet.keySet().size());
            } else {
                tulos.tulos(pelaajanKadet.keySet().size());
            }
        }
        pelaajanKadet.clear();
    }

    public void jakajanKasi() throws InterruptedException {
        jakajanKasi.setOpen();
        System.out.println("");
        if (KierrosUtil.dealerOttaa(pelaajanKadet)) {
            System.out.print("Dealer: " + jakajanKasi.kortit());
            while (jakajanKasi.getArvo() < 17 && !KierrosUtil.pelaajaAllBj(pelaajanKadet)) {
                jakajanKasi.addKortti(Jakaja.annaKortti());
                Thread.sleep(1000);
                System.out.print(Jakaja.edellinenKortti() + " ");
            }
            System.out.println(jakajanKasi.getArvoS());
        }
        for (Kasi k : pelaajanKadet.keySet()) {
            if (k.isSplitted()) {
                System.out.println("");
                break;
            }
        }
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
    
    public Scanner getLukija() {
        return this.lukija;
    }

}
