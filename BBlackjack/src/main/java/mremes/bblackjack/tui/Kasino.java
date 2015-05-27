package mremes.bblackjack.tui;

import java.io.ByteArrayOutputStream;
import java.util.Scanner;
import mremes.bblackjack.utilities.Jakaja;
import mremes.bblackjack.tui.Kierros;
import mremes.bblackjack.elements.Pelaaja;

public class Kasino {

    private Scanner lukija;
    private Pelaaja pelaaja;

    public Kasino(Scanner lukija) {
        this.lukija = lukija;
        this.pelaaja = new Pelaaja(1000);
        Jakaja jakaja = new Jakaja();
    }

    // KAYNNISTYSMETODI
    public void kaynnista() throws InterruptedException {
        System.out.println("*** BETTER BLACKJACK v. 0.1 ***");
        while (true) {
            System.out.println(" * 1 * PLAY \n * 2 * BALANCE \n * 3 * DEPOSIT \n * 4 * EXIT");
            System.out.print("CHOOSE (1-4): ");
            String syotto = lukija.nextLine();
            if (syotto.equals("1")) {
                play();
            } else if (syotto.equals("2")) {
                balance();
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

    // DEPO-METODI
    public void deposit(int summa) throws InterruptedException {
        pelaaja.lisaaRahaa(summa);
        System.out.print("Processing.");
        Thread.sleep(1200);
        System.out.print(".");
        Thread.sleep(1200);
        System.out.print(".");
        Thread.sleep(2200);
        System.out.println("\nYou have successfully deposited " + summa + "!");
    }

    public void play() throws InterruptedException {
        boolean pelataan = true;
        Jakaja.sekoitaKortit();
        while (pelataan) {
            Kierros kierros = new Kierros(pelaaja, lukija);
            kierros.pelaaKierros();
            System.out.print("\nNEW ROUND? (Y/N) ");
            String syote = lukija.nextLine();
            if (!syote.equals("Y")) {
                pelataan = false;
            }
            if (Jakaja.jaljellaKortteja() < 80) {
                shuffle();
            }
        }
    }
    
    public void balance() {
        System.out.println("BALANCE: " + pelaaja.getBalance());
    }
    
    public void exit() {
        System.out.println("Thank you for playing!");
    }
    
    private void shuffle() throws InterruptedException {
        Jakaja.sekoitaKortit();
        System.out.print("Shuffling.");
        Thread.sleep(750);
        System.out.print(".");
        Thread.sleep(750);
        System.out.println(".");
        Thread.sleep(750);
    }
    
    public Pelaaja getPelaaja() {
        return this.pelaaja;
    }
    
    public Scanner getLukija() {
        return this.lukija;
    }
}
