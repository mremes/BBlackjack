package mremes.bblackjack.Main;

import java.util.Scanner;
import mremes.bblackjack.logiikka.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Scanner lukija = new Scanner(System.in);
        Game peli = new Game(lukija);
        peli.play();
    }
}
