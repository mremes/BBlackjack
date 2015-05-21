package mremes.bblackjack.logiikka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Kierros {

    private Pelaaja pelaaja;
    private HashMap<Kasi, Integer> pelaajanKadet;
    private Kasi jakajanKasi;
    private Scanner lukija;
    private int panos;
    private boolean breakRound;
    private Jakaja jakaja;

    public Kierros(Pelaaja pelaaja, Scanner lukija, Jakaja jakaja) {
        this.pelaaja = pelaaja;
        this.pelaajanKadet = new HashMap();
        this.lukija = lukija;
        this.breakRound = false;
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
            if (!k.onBust()) {
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
            if (!k.onBlackjack()) {
                allbj = false;
                break;
            }
        }
        return allbj;
    }

    public boolean voiVakuuttaa(Kasi k) {
        return jakajanKasi.nakyvaAssa() && pelaajanKadet.size() == 1 && k.kortteja() == 2;
    }

    public boolean montaKatta() {
        return pelaajanKadet().size() > 1;
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
            if (jakajanKasi.onBlackjack()) {
                k.valmis();
                jakajanKasi.avaa();
            } else {
                System.out.println("Insurance lost.");
            }
        }
    }
    // ACTION-METODIT
    public void actions(Kasi k) throws InterruptedException {
        if (k.onBlackjack()) {
            k.valmis();
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
            k.valmis();
        } else if (k.kortteja() == 2) {
            if (komento.equals("DOUBLE")) {
                doubl(k);
            } else if (komento.equals("SPLIT")) {
                split(k);
            }
        }
        if (k.getArvo() >= 21) {
            if (k.onBust() && pelaajanKadet.size() > 1) {
                System.out.println("Bust.");
            }
            k.valmis();
        }
    }
    // TOIMINTAMETODIT
    public void stand(Kasi k) {
        k.valmis();
    }
    public void split(Kasi hand) {
        hand.valmis();
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
        k.lisaaKortti(Jakaja.annaKortti());
    }
    public void doubl(Kasi k) {
        k.lisaaKortti(Jakaja.annaKortti());
        pelaaja.veloita(panos);
        panos *= 2;
        k.valmis();
        System.out.println("\nYou: " + k);

    }

    // TULOSMETODIT
    public void tulos() throws InterruptedException {
        jakajanKasi.avaa();
        System.out.println("");
        if (dealerOttaa()) {
            System.out.print("Dealer: " + jakajanKasi.kortit());
            while (jakajanKasi.getArvo() < 17 && !pelaajaAllBj()) {
                jakajanKasi.lisaaKortti(Jakaja.annaKortti());
                Thread.sleep(1000);
                System.out.print(Jakaja.edellinenKortti() + " ");
            }
            System.out.println(jakajanKasi.getArvoS());
        }
        int i = 1;
        for (Kasi k : pelaajanKadet()) {
            if (pelaajanKadet.size() > 1) {
                System.out.println("\nHand " + i + ": " + k);
                i++;
            }
            int compare = k.compareTo(jakajanKasi);
            switch (compare) {
                case 1:
                    voitto(k);
                    break;
                case 0:
                    tasuri(k);
                    break;
                case -1:
                    havio(k);
                    break;
                default:
                    break;
            }
        }
    }
    public void voitto(Kasi k) {
        int voitto = panos * 2;
        if (k.onBlackjack()) {
            voitto = panos * 2 + panos / 2;
            System.out.println("BLACKJACK, you win " + voitto + "!");
        } else if (!jakajanKasi.onBust()) {
            System.out.println("You win " + voitto + "!");
        } else {
            System.out.println("Dealer's bust, you win " + voitto + "!");
        }
        pelaaja.lisaaRahaa(voitto);
    }
    public void tasuri(Kasi k) {
        pelaaja.lisaaRahaa(panos);
        System.out.println("Push.");
    }
    public void havio(Kasi k) {
        if (k.onBust()) {
            System.out.println("You're bust, dealer wins!");
            if (pelaajanKadet().size() == 1) {
                System.out.println("Dealer: " + jakajanKasi);
            }
        } else if (jakajanKasi.onBlackjack()) {
            System.out.println("Dealer has a BLACKJACK, dealer wins!");
            if (k.isInsured()) {
                System.out.println("Insurance pays " + pelaajanKadet.get(k));
                pelaaja.lisaaRahaa(pelaajanKadet.get(k));
            }
        } else {
            System.out.println("Dealer wins!");
        }
    }

    // GETTERIT
    public Set<Kasi> pelaajanKadet() {
        return pelaajanKadet.keySet();
    }
    public boolean dealerOttaa() {
        int busted = 0;
        int blackjacks = 0;
        for (Kasi k : pelaajanKadet()) {
            if (k.onBust()) {
                busted++;
            }
            if (k.onBlackjack()) {
                blackjacks++;
            }
        }
        if (busted + blackjacks == pelaajanKadet().size()) {
            return false;
        }
        return true;
    }

}
