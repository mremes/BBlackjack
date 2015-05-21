

import mremes.bblackjack.logiikka.Jakaja;
import mremes.bblackjack.logiikka.Kasi;
import mremes.bblackjack.logiikka.Pelaaja;

public class PelinTulos {
    private int panos;
    private Pelaaja kayttaja;
    private Kasi pelaaja;
    private Kasi jakaja;
    private int kasia;
    
    public PelinTulos(Pelaaja kayttaja, Kasi pelaaja, Kasi jakaja, int panos) {
        this.kayttaja = kayttaja;
        this.pelaaja = pelaaja;
        this.jakaja = jakaja;
        this.panos = panos;
    }
    
    public void tulos(int kasia) {
        tuloste();
        int compare = pelaaja.compareTo(jakaja);
        switch (compare) {
            case 1:
                voitto(pelaaja);
                break;
            case 0:
                tasuri(pelaaja);
                break;
            case -1:
                havio(pelaaja);
                break;
            default:
                break;
        }
    }
    private void voitto(Kasi k) {
        int voitto = panos * 2;
        if (k.isBlackjack()) {
            voitto = panos * 2 + panos / 2;
            System.out.println("BLACKJACK, you win " + voitto + "!");
        } else if (!jakaja.isBust()) {
            System.out.println("You win " + voitto + "!");
        } else {
            System.out.println("Dealer's bust, you win " + voitto + "!");
        }
        kayttaja.lisaaRahaa(voitto);
    }
    private void havio(Kasi k) {
        if (k.isBust()) {
            System.out.println("You're bust, dealer wins!");
            if (kasia == 1) {
                System.out.println("Dealer: " + jakaja);
            }
        } else if (jakaja.isBlackjack()) {
            System.out.println("Dealer has a BLACKJACK, dealer wins!");
            if (k.isInsured()) {
                System.out.println("Insurance pays " + panos);
                kayttaja.lisaaRahaa(panos);
            }
        } else {
            System.out.println("Dealer wins!");
        }
    }
    private void tasuri(Kasi k) {
        kayttaja.lisaaRahaa(panos);
        System.out.println("Push.");
    } 
    private void tuloste() {
        if (kasia > 1) {
                System.out.println("\nHand :" + pelaaja);
        }
    }

}
