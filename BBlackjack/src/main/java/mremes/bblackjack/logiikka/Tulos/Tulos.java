package mremes.bblackjack.logiikka.Tulos;

import mremes.bblackjack.logiikka.Jakaja;
import mremes.bblackjack.logiikka.Kasi;
import mremes.bblackjack.logiikka.Pelaaja;

public class Tulos {
    private int panos;
    private Pelaaja kayttaja;
    private Kasi pelaaja;
    private Kasi jakaja;
    
    public Tulos(Pelaaja kayttaja, Kasi pelaaja, Kasi jakaja, int panos) {
        this.kayttaja = kayttaja;
        this.pelaaja = pelaaja;
        this.jakaja = jakaja;
        this.panos = panos;
    }
    
    public void tulos() {
        this.panos = panos;
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
        if (k.onBlackjack()) {
            voitto = panos * 2 + panos / 2;
            System.out.println("BLACKJACK, you win " + voitto + "!");
        } else if (!jakaja.onBust()) {
            System.out.println("You win " + voitto + "!");
        } else {
            System.out.println("Dealer's bust, you win " + voitto + "!");
        }
        kayttaja.lisaaRahaa(voitto);
    }
    private void havio(Kasi k) {
        
    }
    
    private void tasuri(Kasi k) {
        
    }

}
