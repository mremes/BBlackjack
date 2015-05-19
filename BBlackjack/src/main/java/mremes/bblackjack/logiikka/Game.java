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

    public Game(Scanner lukija) {

        this.kortit = kortit();
        this.paikka = new Paikka(1);
        this.jakajanKasi = null;
        this.counter = 0;
        this.lukija = lukija;
        this.rahat = 1000;
    }

    public void play() throws InterruptedException {
        sekoitaKortit();
        while (true) {
            int money = 1000;
            System.out.println("Your stack: " + rahat);
            System.out.print("Place your bet: ");
            int panos = Integer.parseInt(lukija.nextLine());
            rahat -= panos;
            pelaaKierros(panos);
           
        }
    }

    public void pelaaKierros(int panos) {
        jaaKortit(panos);
        System.out.println("Dealer: " + jakajanKasi);
        System.out.println("You: " + paikka.getKasi() + " " + paikka.getKasi().getArvoS());
        while (true) {
            int br = 0;
            if (paikka.getKasi().onBlackjack()) {
                break;
            }
            if (!paikka.getKasi().samatKortit() && br == 0) {
                System.out.println("HIT, STAND, DOUBLE");
                String syotto = lukija.nextLine();
                if(syotto.equals("HIT")) {
                    lisaaKortti(paikka.getKasi());
                    br++;
                } else if (syotto.equals("STAND")) {
                    break;
                } else if (syotto.equals("DOUBLE")) {
                    tuplaa(paikka.getKasi(), panos);
                    break;
                }
                
                if(paikka.getKasi().getArvo() >= 21) {
                    break;
                }
                
            } else if (!paikka.getKasi().samatKortit() && br == 1) {
                System.out.println("HIT, STAND");
                String syotto = lukija.nextLine();
                if(syotto.equals("HIT")) {
                    lisaaKortti(paikka.getKasi());
                } else if (syotto.equals("STAND")) {
                    break;
                }
                
                if(paikka.getKasi().getArvo() >= 21) {
                    break;
                }
            }
 
        }
        jakajanKasi.avaa();
        System.out.print("Dealer: " + jakajanKasi + " ");
        while (jakajanKasi.getArvo() < 17 && !this.paikka.getKasi().onBust()) {
            lisaaKortti(jakajanKasi);
            System.out.print(kortti() + " ");
        }
        System.out.print(jakajanKasi.getArvoS() + "\n");
        
        tulos(panos);
        
            

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

    public void jaaKortit(int panos) {
        this.paikka.setKasi(new Kasi(kortit.get(counter), kortit.get(counter + 1), panos));
        counter += 2;
        this.jakajanKasi = new Kasi(kortit.get(counter), kortit.get(counter + 1));
        counter += 2;
    }

    public void lisaaKortti(Kasi k) {
        k.lisaaKortti(kortit.get(counter()));
    }
    
    public Kortti kortti() {
        return this.kortit.get(counter-1);
    }

    public void tuplaa(Kasi kasi, int panos) {
        lisaaKortti(kasi);
        kasi.tuplaa();
        rahat -= panos;
    }

    public int counter() {
        counter++;
        return counter - 1;
    }
    
    public void tulos(int panos) {
        if (this.paikka.getKasi().onBlackjack()) {
                System.out.print("You: ");
                System.out.println(this.paikka.getKasi() + ", BLACKJACK!");
                int voitto = panos * 2 + panos / 2;
                System.out.println("You win: " + voitto);
                rahat += voitto;
            } else if (jakajanKasi.onBlackjack() && this.paikka.getKasi().onBlackjack()) {
                System.out.println("Push");
                rahat += panos;
            } else if (jakajanKasi.getArvo() > 21 && !jakajanKasi.onBlackjack()) {
                System.out.println("Dealer's bust, you win " + panos * 2 + "!");
                rahat += panos * 2;
            } else if (jakajanKasi.getArvo() > this.paikka.getKasi().getArvo() && jakajanKasi.getArvo() < 22) {
                System.out.println("Dealer wins.");
            } else if (jakajanKasi.getArvo() == this.paikka.getKasi().getArvo()) {
                System.out.println("Push.");
                rahat += panos;
            } else if ((jakajanKasi.getArvo() < this.paikka.getKasi().getArvo()) && this.paikka.getKasi().getArvo() < 22) {
                System.out.println("You win " + panos * 2 + "!");
                rahat += panos * 2;
            } else if (jakajanKasi.onBlackjack()) {
                System.out.println("Dealer wins.");
            }
    }
}   
