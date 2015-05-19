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

    public Game(Scanner lukija) {

        this.kortit = kortit();
        this.paikka = new Paikka(1);
        this.jakajanKasi = null;
        this.counter = 0;
        this.lukija = lukija;
        this.rahat = 1000;
        this.panos = 0;
    }

    public void play() throws InterruptedException {
        System.out.println("*** BETTER BLACKJACK v. 0.1 ***");
        sekoitaKortit();
        while (true) {
            System.out.println(" * 1 * PLAY \n * 2 * BALANCE \n * 3 * DEPOSIT \n * 4 * EXIT");
            System.out.print("Choose: ");
            String syotto = lukija.nextLine();
            if (syotto.equals("1")) {
                boolean pelataan = true;
                while(pelataan) {
                    pelaaKierros();
                    System.out.print("New round? (Y/N) ");
                    String syote = lukija.nextLine();
                    if (!syote.equals("Y")) {
                        pelataan = false;
                    }
                }
            } else if (syotto.equals("2")) {
                System.out.println("Your balance: " + rahat);
            } else if (syotto.equals("3")) {
                System.out.print("How much? ");
                deposit(Integer.parseInt(lukija.nextLine()));
            } else if (syotto.equals("4")) {
                System.out.println("Thank you for playing!");
                break;
            }

        }
        System.out.println("*** BETTER BLACKJACK v. 0.1 ***");
    }

    public void pelaaKierros() throws InterruptedException {
        System.out.println("Your stack: " + rahat);
        System.out.print("Place your bet: ");
        panos = Integer.parseInt(lukija.nextLine());
        rahat -= panos;
        jaaKortit();
        boolean hit = false;
        System.out.println("Dealer: " + jakajanKasi);
        while (true) {
            System.out.println("You: " + paikka.getKasi() + " " + paikka.getKasi().getArvoS());
            if (paikka.getKasi().onBlackjack()) {
                break;
            }
            if (!hit) {
                System.out.print("HIT, STAND, DOUBLE: ");
                String syotto = lukija.nextLine();
                if (syotto.equals("HIT")) {
                    lisaaKortti(paikka.getKasi());
                    hit = true;
                } else if (syotto.equals("STAND")) {
                    break;
                } else if (syotto.equals("DOUBLE")) {
                    tuplaa(paikka.getKasi());
                    System.out.println("You: " + paikka.getKasi() + " " + paikka.getKasi().getArvoS());
                    break;
                }

                if (paikka.getKasi().getArvo() >= 21) {
                    System.out.println("You: " + paikka.getKasi() + " " + paikka.getKasi().getArvoS());
                    break;
                }

            } else {
                System.out.print("HIT, STAND: ");
                String syotto = lukija.nextLine();
                if (syotto.equals("HIT")) {
                    lisaaKortti(paikka.getKasi());
                } else if (syotto.equals("STAND")) {
                    break;
                }

                if (paikka.getKasi().getArvo() >= 21) {
                    System.out.println("You: " + paikka.getKasi() + " " + paikka.getKasi().getArvoS());
                    break;
                }
            }
        }
        tulos();

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
        while (jakajanKasi.getArvo() < 17 && !this.paikka.getKasi().onBust()) {
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
        k.lisaaKortti(kortit.get(counter()));
    }

    public Kortti kortti() {
        return this.kortit.get(counter - 1);
    }

    public void tuplaa(Kasi kasi) {
        lisaaKortti(kasi);
        kasi.tuplaa();
        rahat -= panos;
        panos *= 2;
    }

    public void tulos() throws InterruptedException {
        jakajanKasi.avaa();
        if (!this.paikka.getKasi().onBust()) {
            System.out.print("Dealer: " + jakajanKasi + " ");
            while (jakajanKasi.getArvo() < 17) {
                lisaaKortti(jakajanKasi);
                Thread.sleep(1000);
                System.out.print(kortti() + " ");
            }
            System.out.print(jakajanKasi.getArvoS() + "\n");
        }
        int compare = paikka.getKasi().compareTo(jakajanKasi);

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
    }

    public void voitto() {
        int voitto = panos * 2;
        if (paikka.getKasi().onBlackjack()) {
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
        if (this.paikka.getKasi().onBust()) {
            System.out.println("You're bust, dealer wins!");
        } else if (jakajanKasi.onBlackjack()) {
            System.out.println("Dealer has a BLACKJACK, dealer wins!");
        } else {
            System.out.println("Dealer wins!");
        }
    }

    public int counter() {
        counter++;
        return counter - 1;
    }
}
