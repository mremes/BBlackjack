package mremes.bblackjack.Main;

import java.util.Scanner;
import mremes.bblackjack.logiikka.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Kasino kasino = new Kasino(new Scanner(System.in));
        kasino.kaynnista();
        
    }
}
