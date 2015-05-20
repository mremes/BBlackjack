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
        // PANOSTEN ASETTAMINEN JA KORTTIEN JAKO
        pelaajanKadet.put(uusiKasi(), panostus());
        this.jakajanKasi = uusiKasi();
        jakajanKasi.setDealer();
        System.out.println("");
        // PELI ALKAA
        dealer();
        while (true) {
            pelaaja();
            for (Kasi k : pelaajanKadet.keySet()) {
                System.out.println("");
                if (k.onBlackjack()) {
                    k.valmis();
                } else {
                    if (pelaajanKadet.keySet().size() > 1) {
                        System.out.println(k + " " + k.getArvoS());
                    }
                    if (jakajanKasi.nakyvaAssa() && k.getKortit().size() > 2) {
                        insurance(k);
                    }
                    if (!k.isValmis()) {
                        komennot(k);
                        komento(lukija.nextLine(), k);
                    }
                }
            }
            if (pelaajaValmis()) {
                break;
            }
        }
        tulos();
    }

    // UUDEN KÄDEN GENEROINTI
    
    public Kasi uusiKasi() {
        return new Kasi(Jakaja.annaKortti(), Jakaja.annaKortti());
    }

    // TULOSTUSMETODIT
    
    public void pelaaja() {
        for (Kasi k : pelaajanKadet.keySet()) {
            System.out.println("You: " + k + " " + k.getArvoS());
        }
    }

    public void dealer() {
        if (!jakajanKasi.isDealer()) {
            System.out.println("Dealer: " + jakajanKasi + " " + jakajanKasi.getArvoS());
        } else {
            System.out.println("Dealer: " + jakajanKasi);
        }
    }

    // TILAMETODIT
    
    public boolean pelaajaBust() {
        boolean bust = true;
        for (Kasi k : pelaajanKadet.keySet()) {
            if (!k.onBust()) {
                bust = false;
                break;
            }
        }
        return bust;
    }

    public boolean pelaajaValmis() {
        boolean valmis = true;
        for (Kasi k : pelaajanKadet.keySet()) {
            if (!k.isValmis()) {
                valmis = false;
                break;
            }
        }
        return valmis;
    }

    public boolean pelaajaAllBj() {
        boolean allbj = true;
        for (Kasi k : pelaajanKadet.keySet()) {
            if (!k.onBlackjack()) {
                allbj = false;
                break;
            }
        }
        return allbj;
    }

    // PANOSTUS- JA VAKUUTUSMETODI
    
    public int panostus() {
        System.out.println("Your stack: " + pelaaja.getBalance());
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
            System.out.println("Checking...");
            Thread.sleep(1000);
            pelaaja.veloita(panos / 2);
            if (jakajanKasi.onBlackjack()) {
                k.valmis();
                jakajanKasi.avaa();
            } else {
                System.out.println("Insurance lost.");
            }
        }
    }

    // KOMENTOMETODIT
    
    public void komennot(Kasi k) {
        String hit = "HIT";
        String stand = "STAND";
        String dble = "DOUBLE";
        String split = "SPLIT";
        String komennot = hit + ", " + stand;
        if (k.getKortit().size() == 2) {
            komennot += ", " + dble;
        }
        if (k.getKortti(0).getNumeroarvo() == k.getKortti(1).getNumeroarvo()) {
            komennot += ", " + split;
        }
        System.out.print(komennot + ": ");
    }

    public void komento(String komento, Kasi k) {
        if (komento.equals("HIT")) {
            hit(k);
            System.out.println("");
        } else if (komento.equals("STAND")) {
            k.valmis();
        } else if (k.getKortit().size() == 2) {
            if (komento.equals("DOUBLE")) {
                doubl(k);
            } else if (komento.equals("SPLIT")) {
                split();
            }
        }
        if (k.getArvo() >= 21) {
            System.out.println(k + " " + k.getArvoS());;

            if (k.getArvo() > 21 && pelaajanKadet.size() > 1) {
                System.out.println("Bust.");
            }
            k.valmis();
        }
    }

    // TOIMINTAMETODIT
    
    public void stand(Kasi k) {
        k.valmis();
    }

    public void split() {
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
        System.out.println("\nYou: " + k + " " + k.getArvoS());
    }

    // TULOSMETODIT
    
    public void tulos() throws InterruptedException {
        jakajanKasi.avaa();

        if (!pelaajaBust() && !jakajanKasi.onBlackjack()) {
            System.out.print("Dealer: " + jakajanKasi + " ");
            while (jakajanKasi.getArvo() < 17 && !pelaajaAllBj()) {
                jakajanKasi.lisaaKortti(Jakaja.annaKortti());
                Thread.sleep(1000);
                System.out.print(Jakaja.edellinenKortti() + " ");
            }
            System.out.println(jakajanKasi.getArvoS() + "\n");
        }
        int i = 1;
        for (Kasi k : pelaajanKadet.keySet()) {
            if (pelaajanKadet.size() > 1) {
                System.out.println("\nHand " + i + ": " + k + " " + k.getArvoS());
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
            System.out.println("\nYou're bust, dealer wins!");
            jakajanKasi.avaa();
            dealer();
        } else if (jakajanKasi.onBlackjack()) {
            dealer();
            System.out.println("Dealer has a BLACKJACK, dealer wins!");
            if (k.isInsured()) {
                System.out.println("Insurance pays " + pelaajanKadet.get(k));
                pelaaja.lisaaRahaa(pelaajanKadet.get(k));
            }
        } else {
            System.out.println("Dealer wins!");
        }
    }
}
