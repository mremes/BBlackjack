package mremes.bblackjack.logiikka;

import java.util.ArrayList;
import java.util.Collections;

public class Jakaja {
    private static int counter;
    private static ArrayList<Kortti> kortit;
    
    public Jakaja() {
        Jakaja.counter = 0;
        ArrayList<Kortti> kortit1 = new ArrayList();
        Korttipakka pakka = new Korttipakka();
        for (int i = 0; i < 8; i++) {
            for (Kortti kortti : pakka.getKortit()) {
                kortit1.add(kortti);
            }
        }
        Jakaja.kortit = kortit1;
    }
    
    // KORTTIMETODIT
    public static Kortti annaKortti() {
        return kortit.get(counter());
    }
    public static void sekoitaKortit() {
        Collections.shuffle(Jakaja.kortit);
        counter = 0;
    } 
    public static Kortti edellinenKortti() {
        return Jakaja.kortit.get(counter - 1);
    }
    // LASKIJA
    private static int counter() {
        counter++;
        return counter - 1;
    }
}
