package logiikka.utilities;

import logiikka.elements.Kasi;
import logiikka.cards.Korttipakka;
import logiikka.cards.Kortti;
import java.util.ArrayList;
import java.util.Collections;

public class Jakaja {
    private static int counter;
    private static ArrayList<Kortti> kortit;
    /**
     * Jakaja on olio, joka hallitsee kortteja. Jakaja-oliota käytetään staattisten
     * metodien avulla ja jakajalla on kahdeksan pakkaa käytössään.
     */
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
    /**
     * Antaa seuraavan kortin pakasta
     * @return 
     */
    public static Kortti annaKortti() {
        return kortit.get(counter());
    }
    /**
     * Sekoittaa kahdeksan pakkaa keskenään
     */
    public static void sekoitaKortit() {
        Collections.shuffle(Jakaja.kortit);
        counter = 0;
    } 
    /**
     * Antaa pakan viimeisenä jaetun kortin
     * @return 
     */
    public static Kortti edellinenKortti() {
        return Jakaja.kortit.get(counter - 1);
    }
    /**
     * Luo uuden Kasi-olion ja jakaa siihen kaksi uutta korttia pakasta.
     * @return 
     */
    public static Kasi uusiKasi() {
        return new Kasi(Jakaja.annaKortti(), Jakaja.annaKortti());
    }
    // LASKIJA
    /**
     * Laskee, monta korttia on jaettu.
     * @return laskurin arvo
     */
    public static int counter() {
        counter++;
        return counter - 1;
    }
    /**
     * Kertoo, monta korttia on pakassa jäljellä.
     * @return 
     */
    public static int jaljellaKortteja() {
        return Jakaja.kortit.size() - counter;
    }
    /**
     * Nollaa laskurin
     */
    
}
