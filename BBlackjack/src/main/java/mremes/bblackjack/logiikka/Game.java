package mremes.bblackjack.logiikka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {

    private ArrayList<Kortti> kortit;
    private Paikka paikka;
    private Kasi jakajanKasi;
    private int counter;
    private Scanner lukija;
    private int rahat;
    private int panos;
    private boolean breakRound;

    public Game(Scanner lukija) {

        this.kortit = kortit();
        this.paikka = new Paikka(1);
        this.jakajanKasi = null;
        this.counter = 0;
        this.lukija = lukija;
        this.rahat = 1000;
        this.panos = 0;
        this.breakRound = false;
    }

    public void play() throws InterruptedException {
        System.out.println("*** BETTER BLACKJACK v. 0.1 ***");
        sekoitaKortit();
        while (true) {
            System.out.println(" * 1 * PLAY \n * 2 * BALANCE \n * 3 * DEPOSIT \n * 4 * EXIT");
            System.out.print("CHOOSE (1-4): ");
            String syotto = lukija.nextLine();
            if (syotto.equals("1")) {
                boolean pelataan = true;
                while (pelataan) {
                    pelaaKierros();
                    System.out.print("NEW ROUND? (Y/N) ");
                    String syote = lukija.nextLine();
                    if (!syote.equals("Y")) {
                        pelataan = false;
                    }
                }
            } else if (syotto.equals("2")) {
                System.out.println("BALANCE: " + rahat);
            } else if (syotto.equals("3")) {
                System.out.print("AMOUNT: ");
                deposit(Integer.parseInt(lukija.nextLine()));
            } else if (syotto.equals("4")) {
                System.out.println("Thank you for playing!");
                break;
            }

        }
        System.out.println("*** BETTER BLACKJACK v. 0.1 ***");
    }

    public void pelaaKierros() throws InterruptedException {
        panostus();
        jaaKortit();
        System.out.println("Dealer: " + jakajanKasi);
        while (true) {
            pelaaja();
            if (pelaajaKasi().onBlackjack()) {
                break;
            }
            komennot();
            komento(lukija.nextLine());
            if (breakRound) {
                break;
            }
        }
        tulos();
    }

    public void hit() {
        lisaaKortti(pelaajaKasi());
    }

    public void pelaaja() {
        System.out.println("You: " + pelaajaKasi() + " " + pelaajaKasi().getArvoS());
    }

    public void deposit(int summa) throws InterruptedException {
        rahat += summa;
        System.out.print("Processing.");
        Thread.sleep(1200);
        System.out.print(".");
        Thread.sleep(1200);
        System.out.print(".");
        Thread.sleep(2200);
        System.out.println("\nYou have successfully deposited " + summa + "!");
    }

    public void dealerinKasi() {
        while (jakajanKasi.getArvo() < 17 && !this.pelaajaKasi().onBust()) {
            Kortti k = kortit.get(counter());
            jakajanKasi.lisaaKortti(k);
            System.out.print(k + " ");
            if (jakajanKasi.onBlackjack()) {
                break;
            }
        }
    }

    private ArrayList<Kortti> kortit() {
        ArrayList<Kortti> kortit1 = new ArrayList();
        Korttipakka[] pakat = new Korttipakka[8];
        for (int i = 0; i < 8; i++) {
            pakat[i] = new Korttipakka();
        }
        for (Korttipakka pakka : pakat) {
            for (Kortti kortti : pakka.getKortit()) {
                kortit1.add(kortti);
            }
        }
        return kortit1;
    }

    public void sekoitaKortit() {
        Collections.shuffle(this.kortit);
    }

    public void jaaKortit() {
        this.paikka.setKasi(new Kasi(kortit.get(counter), kortit.get(counter + 1), panos));
        counter += 2;
        this.jakajanKasi = new Kasi(kortit.get(counter), kortit.get(counter + 1));
        counter += 2;
    }

    public void lisaaKortti(Kasi k) {
        k.lisaaKortti(annaKortti());
    }
    
    public Kortti annaKortti() {
        return kortit.get(counter());
    }

    public Kortti kortti() {
        return this.kortit.get(counter - 1);
    }

    public void tuplaa() {
        lisaaKortti(pelaajaKasi());
        pelaajaKasi().tuplaa();
        rahat -= panos;
        panos *= 2;
        breakRound = true;
        System.out.println("You: " + pelaajaKasi() + " " + pelaajaKasi().getArvoS());
    }

    public void tulos() throws InterruptedException {
        jakajanKasi.avaa();
        if (!this.pelaajaKasi().onBust() && !jakajanKasi.onBlackjack()) {
            System.out.print("Dealer: " + jakajanKasi + " ");
            while (jakajanKasi.getArvo() < 17) {
                lisaaKortti(jakajanKasi);
                Thread.sleep(1000);
                System.out.print(kortti() + " ");
            }
            System.out.println(jakajanKasi.getArvoS());
        }
        int compare = pelaajaKasi().compareTo(jakajanKasi);

        switch (compare) {
            case 1:
                voitto();
                break;
            case 0:
                tasuri();
                break;
            case -1:
                havio();
                break;
            default:
                break;

        }
        breakRound = false;
    }

    public void voitto() {
        int voitto = panos * 2;
        if (pelaajaKasi().onBlackjack()) {
            voitto = panos * 2 + panos / 2;
            System.out.println("BLACKJACK, you win " + voitto + "!");
        } else if (!jakajanKasi.onBust()) {
            System.out.println("You win " + voitto + "!");
        } else {
            System.out.println("Dealer's bust, you win " + voitto + "!");
        }
        rahat += voitto;
    }

    public void tasuri() {
        rahat += panos;
        System.out.println("Push.");
    }

    public void havio() {
        if (this.pelaajaKasi().onBust()) {
            System.out.println("You're bust, dealer wins!");
            jakajanKasi.avaa();
            dealerKasi();
        } else if (jakajanKasi.onBlackjack()) {
            dealerKasi();
            System.out.println("Dealer has a BLACKJACK, dealer wins!");
        } else {
            System.out.println("Dealer wins!");
        }
    }

    public int counter() {
        counter++;
        return counter - 1;
    }

    public Kasi pelaajaKasi() {
        return paikka.getKasi();
    }
    
    public ArrayList<Kasi> pelaajaKadet() {
        return paikka.getKadet();
    }

    public void panostus() {
        System.out.println("Your stack: " + rahat);
        System.out.print("Place your bet: ");
        panos = Integer.parseInt(lukija.nextLine());
        rahat -= panos;
    }

    public void komennot() {
        String hit = "HIT";
        String stand = "STAND";
        String dble = "DOUBLE";
        String split = "SPLIT";
        String komennot = hit + ", " + stand;
        if (pelaajaKasi().getKortit().size() == 2) {
            komennot += ", " + dble;
        }
        if (pelaajaKasi().samatKortit()) {
            komennot += ", " + split;
        }
        System.out.print(komennot + ": ");
    }

    public void komento(String komento) {
        if (komento.equals("HIT")) {
            hit();
        } else if (komento.equals("STAND")) {
            stand();
        } else if (pelaajaKasi().getKortit().size() == 2) {
            if (komento.equals("DOUBLE")) {
                tuplaa();
            } else if (komento.equals("SPLIT")) {
                split();
            }
        }
        if (pelaajaKasi().getArvo() >= 21) {
            pelaaja();
            breakRound = true;
        }
    }

    public void dealerKasi() {
        System.out.println("Dealer: " + jakajanKasi + " " + jakajanKasi.getArvoS());
    }

    public void stand() {
        breakRound = true;
    }
    
    public void split() {
        paikka.split(annaKortti(), annaKortti());
    }

}
