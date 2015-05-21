package mremes.bblackjack.utilities;

import java.util.HashMap;
import mremes.bblackjack.elements.Kasi;

public class KierrosUtil {
    
    public static boolean pelaajaBust(HashMap<Kasi, Integer> pelaajanKadet) {
        boolean bust = true;
        for (Kasi k : pelaajanKadet.keySet()) {
            if (!k.isBust()) {
                bust = false;
                break;
            }
        }
        return bust;
    }
    public static boolean pelaajaValmis(HashMap<Kasi, Integer> pelaajanKadet) {
        boolean valmis = true;
        for (Kasi k : pelaajanKadet.keySet()) {
            if (!k.isValmis()) {
                valmis = false;
                break;
            }
        }
        return valmis;
    }
    public static boolean pelaajaAllBj(HashMap<Kasi, Integer> pelaajanKadet) {
        boolean allbj = true;
        for (Kasi k : pelaajanKadet.keySet()) {
            if (!k.isBlackjack()) {
                allbj = false;
                break;
            }
        }
        return allbj;
    }
    public static boolean voiVakuuttaa(Kasi jakajanKasi, Kasi pelaajanKasi, HashMap<Kasi, Integer> kadet) {
        return jakajanKasi.nakyvaAssa() && kadet.size() == 1 && pelaajanKasi.korttienLkm() == 2;
    }
    public static boolean montaKatta(HashMap<Kasi, Integer> kadet) {
        return kadet.size() > 1;
    }
    public static boolean dealerOttaa(HashMap<Kasi,Integer> pelaajanKadet) {
        int busted = 0;
        int blackjacks = 0;
        for (Kasi k : pelaajanKadet.keySet()) {
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
