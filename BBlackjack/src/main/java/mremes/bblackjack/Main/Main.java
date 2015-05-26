package mremes.bblackjack.Main;
import mremes.bblackjack.*;

import mremes.bblackjack.logiikka.Kasino;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import mremes.bblackjack.logiikka.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Kasino kasino = new Kasino(new Scanner(System.in));
        kasino.kaynnista();
        
    }
}
