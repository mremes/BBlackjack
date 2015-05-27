package Main;
import java.util.Scanner;
import logiikka.cards.Arvo;
import logiikka.cards.Kortti;
import logiikka.cards.Maa;
import userInterface.KasinoUI;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(new Kortti(Maa.HERTTA, Arvo.ASSA).src());
    }
}
