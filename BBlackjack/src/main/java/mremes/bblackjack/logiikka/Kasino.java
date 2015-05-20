package mremes.bblackjack.logiikka;

import java.util.Scanner;

public class Kasino {

    private Scanner lukija;
    private Pelaaja pelaaja;
    private Jakaja jakaja;

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
                boolean pelataan = true;
                Jakaja.sekoitaKortit();
                while (pelataan) {
                    Kierros kierros = new Kierros(pelaaja, lukija, jakaja);
                    kierros.pelaaKierros();
                    System.out.print("NEW ROUND? (Y/N) ");
                    String syote = lukija.nextLine();
                    if (!syote.equals("Y")) {
                        pelataan = false;
                    }
                }
            } else if (syotto.equals("2")) {
                System.out.println("BALANCE: " + pelaaja.getBalance());
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
    private void deposit(int summa) throws InterruptedException {
        pelaaja.lisaaRahaa(summa);
        System.out.print("Processing.");
        Thread.sleep(1200);
        System.out.print(".");
        Thread.sleep(1200);
        System.out.print(".");
        Thread.sleep(2200);
        System.out.println("\nYou have successfully deposited " + summa + "!");
    }
}
