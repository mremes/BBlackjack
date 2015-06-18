package logiikka.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import logiikka.elements.Kasi;
/**
 * Luokka sisältää kierrokseen liittyviä apumetodeja, jotka kertovat käden tilasta.
 * @author mrremes
 */
public class KierrosUtil {
    /**
     * kertoo, ovatko pelaajan kaikki kädet 'poikki' (eli arvo yli 21)
     * @param pelaajanKadet pelaajan kädet listassa
     * @return jos pelaaja poikki: true; jos pelaaja "elossa": false
     */
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
    /**
     * kertoo ovatko pelaajan kaikki kädet valmiita (eli poikki, venttejä,
     * tuplattuja, black jackeja, ässä splittejä tai standattuja)
     * @param pelaajanKadet pelaajan kädet listassa
     * @return pelaaja valmis: true; pelaaja ei valmis: false
     */
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
    /**
     * kertoo, ovatko pelaajan kaikki kädet black jackeja
     * @param pelaajanKadet tarkistettavat Kasi-oliot listassa
     * @return pelaajan kaikki kädet black jackeja: true; pelaajalla on normaaleja käsiä: false
     */
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
    /**
     * kertoo sen, onko pelaajalla enemmän kuin yksi käsi
     * @param kadet Kasi-oliot
     * @return true jos enemman kuin yksi kasi, false jos vain yksi kasi
     */
    public static boolean montaKatta(ArrayList<Kasi> kadet) {
        return kadet.size() > 1;
    }
    /**
     * kertoo sen, ottaako dealer yhtään korttia. Dealer ottaa kortin silloin, jos
     * pelaajalla on mahdollisuus voittaa jakaja ja jakajan käden korttien arvo on 21 tai alle
     * @param pelaajanKadet pelaajan kädet listassa
     * @return pelaaja voi voittaa: true; pelaaja ei voi voittaa: false;
     */
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
