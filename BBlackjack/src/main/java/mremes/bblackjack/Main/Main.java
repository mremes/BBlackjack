package mremes.bblackjack.Main;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import mremes.bblackjack.*;
import mremes.bblackjack.deckofcards.Arvo;
import mremes.bblackjack.deckofcards.Kortti;
import mremes.bblackjack.deckofcards.Korttipakka;
import mremes.bblackjack.deckofcards.Maa;
import mremes.bblackjack.elements.Kasi;
import mremes.bblackjack.tui.Kasino;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(new Kasi(new Kortti(Maa.HERTTA, Arvo.ASSA), new Kortti(Maa.PATA, Arvo.JATKA)));
        
    }
}
