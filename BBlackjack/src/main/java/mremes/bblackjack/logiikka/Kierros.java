package mremes.bblackjack.logiikka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Kierros {

    private Pelaaja pelaaja;
    private Jakaja jakaja;
    private HashMap<Kasi, Integer> pelaajanKadet;
    private Kasi jakajanKasi;
    private Scanner lukija;
    private int panos;
    

    public Kierros(Pelaaja pelaaja, Scanner lukija, Jakaja jakaja) {
        this.pelaaja = pelaaja;
        this.pelaajanKadet = new HashMap();
        this.lukija = lukija;
        this.jakaja = jakaja;
    }

    // PELAA YHDEN KIERROKSEN
    public void pelaaKierros() throws InterruptedException {
        jaaKadet();
        dealer();
        while (!pelaajaValmis()) {
            pelaaja();
            for (Kasi k : pelaajanKadet()) {
                while (!k.isValmis()) {
                    actions(k);
                }
            }
        }
        tulos();
    }

    // TULOSTUSMETODIT
    public void jaaKadet() {
        pelaajanKadet.put(Jakaja.uusiKasi(), panostus());
        this.jakajanKasi = Jakaja.uusiKasi();
        jakajanKasi.setDealer();
        System.out.println("");
    }
    public void pelaaja() {
        System.out.println("");
        for (Kasi k : pelaajanKadet()) {
            System.out.println("You: " + k);
        }
    }
    public void dealer() {
        if (!jakajanKasi.isDealer()) {
            System.out.println("Dealer: " + jakajanKasi);
        } else {
            System.out.print("Dealer: " + jakajanKasi);
        }
    }

    // TILAMETODIT
    public boolean pelaajaBust() {
        boolean bust = true;
        for (Kasi k : pelaajanKadet()) {
            if (!k.isBust()) {
                bust = false;
                break;
            }
        }
        return bust;
    }
    public boolean pelaajaValmis() {
        boolean valmis = true;
        for (Kasi k : pelaajanKadet()) {
            if (!k.isValmis()) {
                valmis = false;
                break;
            }
        }
        return valmis;
    }
    public boolean pelaajaAllBj() {
        boolean allbj = true;
        for (Kasi k : pelaajanKadet()) {
            if (!k.isBlackjack()) {
                allbj = false;
                break;
            }
        }
        return allbj;
    }
    public boolean voiVakuuttaa(Kasi k) {
        return jakajanKasi.nakyvaAssa() && pelaajanKadet.size() == 1 && k.korttienLkm() == 2;
    }
    public boolean montaKatta() {
        return pelaajanKadet().size() > 1;
    }
    public boolean dealerOttaa() {
        int busted = 0;
        int blackjacks = 0;
        for (Kasi k : pelaajanKadet()) {
            if (k.isBust()) {
                busted++;
            }
            if (k.isBlackjack()) {
                blackjacks++;
            }
        }
        if (busted + blackjacks == pelaajanKadet().size()) {
            return false;
        }
        return true;
    }
    
    // PANOSTUS- JA VAKUUTUSMETODI
    public int panostus() {
        System.out.println("\nYour stack: " + pelaaja.getBalance());
        System.out.print("Place your bet: ");
        panos = Integer.parseInt(lukija.nextLine());
        pelaaja.veloita(panos);
        return panos;
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
            if (jakajanKasi.isBlackjack()) {
                k.setValmis();
                jakajanKasi.setOpen();
            } else {
                System.out.println("Insurance lost.");
            }
        }
    }
    
    // ACTION-METODIT
    public void actions(Kasi k) throws InterruptedException {
        if (k.isBlackjack()) {
            k.setValmis();
        } else {
            if (montaKatta()) {
                System.out.println("\n" + k);
            }
            if (voiVakuuttaa(k)) {
                insurance(k);
            }
            if (!k.isValmis()) {
                komento(k);
            }
        }
    }
    public void komento(Kasi k) {
        k.doables();
        String komento = lukija.nextLine();
        if (komento.equals("HIT")) {
            hit(k);
            System.out.println("You: " + k);
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
                System.out.println("Bust.");
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
        for (Kasi k : pelaajanKadet()) {
            kasi = k;
            k1 = k.getKortti(0);
            k2 = k.getKortti(1);
            break;
        }
        pelaajanKadet.put(new Kasi(k1, Jakaja.annaKortti()), panos);
        pelaajanKadet.put(new Kasi(k2, Jakaja.annaKortti()), panos);
        pelaaja.veloita(panos);
        pelaajanKadet.remove(kasi);
    }
    public void hit(Kasi k) {
        k.addKortti(Jakaja.annaKortti());
    }
    public void doubl(Kasi k) {
        k.addKortti(Jakaja.annaKortti());
        pelaaja.veloita(panos);
        panos *= 2;
        k.setValmis();
        System.out.println("\nYou: " + k);

    }
    
    // TULOSMETODIT
    public void tulos() throws InterruptedException {
        jakajanKasi();
        for (Kasi k : pelaajanKadet()) {
            Tulos tulos = new Tulos(pelaaja, k, jakajanKasi, panos);
            tulos.tulos(pelaajanKadet().size());
        }
    }
    public void jakajanKasi() throws InterruptedException {
        jakajanKasi.setOpen();
        System.out.println("");
        if (dealerOttaa()) {
            System.out.print("Dealer: " + jakajanKasi.kortit());
            while (jakajanKasi.getArvo() < 17 && !pelaajaAllBj()) {
                jakajanKasi.addKortti(Jakaja.annaKortti());
                Thread.sleep(1000);
                System.out.print(Jakaja.edellinenKortti() + " ");
            }
            System.out.println(jakajanKasi.getArvoS());
        }
    }
    
    // GETTERIT
    public Set<Kasi> pelaajanKadet() {
        return pelaajanKadet.keySet();
    }

}
