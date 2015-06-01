package logiikka.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import logiikka.elements.Kasi;

public class KierrosUtil {
    
    public static boolean pelaajaBust(ArrayList<Kasi> pelaajanKadet) {
        boolean bust = true;
        for (Kasi k : pelaajanKadet) {
            if (!k.isBust()) {
                bust = false;
                break;
            }
        }
        return bust;
    }
    public static boolean pelaajaValmis(ArrayList<Kasi> pelaajanKadet) {
        boolean valmis = true;
        for (Kasi k : pelaajanKadet) {
            if (!k.isValmis()) {
                valmis = false;
                break;
            }
        }
        return valmis;
    }
    public static boolean pelaajaAllBj(ArrayList<Kasi> pelaajanKadet) {
        boolean allbj = true;
        for (Kasi k : pelaajanKadet) {
            if (!k.isBlackjack()) {
                allbj = false;
                break;
            }
        }
        return allbj;
    }
    public static boolean voiVakuuttaa(Kasi jakajanKasi, Kasi pelaajanKasi, ArrayList<Kasi> kadet) {
        return jakajanKasi.nakyvaAssa() && kadet.size() == 1 && pelaajanKasi.korttienLkm() == 2;
    }
    public static boolean montaKatta(ArrayList<Kasi> kadet) {
        return kadet.size() > 1;
    }
    public static boolean dealerOttaa(ArrayList<Kasi> pelaajanKadet) {
        int busted = 0;
        int blackjacks = 0;
        for (Kasi k : pelaajanKadet) {
            if (k.isBust()) {
                busted++;
            }
            if (k.isBlackjack()) {
                blackjacks++;
            }
        }
        if (busted + blackjacks == pelaajanKadet.size()) {
            return false;
        }
        return true;
    }
    
}
