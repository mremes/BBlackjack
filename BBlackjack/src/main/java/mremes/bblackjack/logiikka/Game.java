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

    public Game(Scanner lukija) {

        this.kortit = kortit();
        this.paikka = new Paikka(1);
        this.jakajanKasi = null;
        this.counter = 0;
        this.lukija = lukija;
    }

    public void play() throws InterruptedException {
        sekoitaKortit();
        int money = 1000;
        while (true) {
            System.out.println("Your stack: " + money);
            System.out.print("Place your bet: ");
            int panos = Integer.parseInt(lukija.nextLine());
            money -= panos;
            jaaKortit();
            int i = 0;
            System.out.println("Dealer: " + "[H] " + jakajanKasi.getKortit().get(1) + " (" + jakajanKasi.getKortit().get(1).getNumeroarvo() + ")");
            while (i < 1) {
                int reg = 0;
                Kasi kasi = this.paikka.getKasi();
                while (true) {
                    if (!kasi.onBlackjack()) {
                        System.out.print("You: ");
                        System.out.println(kasi + " (" + kasi.getArvo() + ")");
                    } else {
                        break;
                    }
                    if (reg == 0) {
                        System.out.print("HIT, STAND, DOUBLE: ");
                        String valinta = lukija.nextLine();
                        if (valinta.equals("HIT")) {
                            Kortti k = this.kortit.get(counter);
                            counter++;
                            System.out.println("You've got: " + k.toString());
                            kasi.lisaaKortti(k);
                        } else if (valinta.equals("STAND")) {
                            break;
                        } else if (valinta.equals("DOUBLE")) {
                            money -= panos;
                            panos = panos * 2;
                            Kortti k = this.kortit.get(counter);
                            counter++;
                            System.out.println("You've got: " + k.toString());
                            kasi.lisaaKortti(k);
                            System.out.println("Your hand: ");
                            System.out.println(kasi + " (" + kasi.getArvo() + ")");
                            break;
                        }
                    } else {
                        System.out.print("HIT, STAND: ");
                        String valinta = lukija.nextLine();
                        if (valinta.equals("HIT")) {
                            Kortti k = this.kortit.get(counter);
                            counter++;
                            System.out.println("You've got: " + k.toString());
                            kasi.lisaaKortti(k);
                        } else if (valinta.equals("STAND")) {
                            break;
                        }
                    }
                    reg++;
                    if(kasi.getArvo() > 21) {
                        break;
                    }
                }
                if (kasi.getArvo() > 21 && kasi.getArvo() != 999) {
                    System.out.println("Your hand's value is " + kasi.getArvo() + ", you're bust!");
                    System.out.println("Dealer wins.");
                } else if (kasi.getArvo() == 21) {
                    System.out.println("Your hand's value is 21.");
                }
                i++;
            }
            System.out.print("Dealer: " + jakajanKasi + " ");
            while (jakajanKasi.getArvo() < 17 && !this.paikka.getKasi().onBust()) {
                Kortti k = kortit.get(counter);
                counter++;
                jakajanKasi.lisaaKortti(k);
                Thread.sleep(700);
                System.out.print(k + " ");
                if (jakajanKasi.onBlackjack()) {
                    break;
                }
            }
            System.out.print("(" + jakajanKasi.getArvo() + ") \n");
            if (this.paikka.getKasi().onBlackjack()) {
                System.out.print("You: ");
                System.out.println(this.paikka.getKasi() + ", BLACKJACK!");
                int voitto = panos * 2 + panos / 2;
                System.out.println("You win: " + voitto);
                money += voitto;
            } else if (jakajanKasi.onBlackjack() && this.paikka.getKasi().onBlackjack()) {
                System.out.println("Push");
                money += panos;
            } else if (jakajanKasi.getArvo() > 21 && !jakajanKasi.onBlackjack()) {
                System.out.println("Dealer's bust, you win " + panos * 2 + "!");
                money += panos * 2;
            } else if (jakajanKasi.getArvo() > this.paikka.getKasi().getArvo() && jakajanKasi.getArvo() < 22) {
                System.out.println("Dealer wins.");
            } else if (jakajanKasi.getArvo() == this.paikka.getKasi().getArvo()) {
                System.out.println("Push.");
                money += panos;
            } else if ((jakajanKasi.getArvo() < this.paikka.getKasi().getArvo()) && this.paikka.getKasi().getArvo() < 22) {
                System.out.println("You win " + panos * 2 + "!");
                money += panos * 2;
            } else if (jakajanKasi.onBlackjack()) {
                System.out.println("Dealer wins.");
            }
            System.out.println("New game? (Y/N) ");
            String syotto = lukija.nextLine();
            if (syotto.equals("N")) {
                i = 0;
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
        this.paikka.setKasi(new Pelaaja(kortit.get(counter), kortit.get(counter + 1)));
        counter += 2;
        this.jakajanKasi = new Jakaja(kortit.get(counter), kortit.get(counter + 1));
        counter += 2;
    }
}
