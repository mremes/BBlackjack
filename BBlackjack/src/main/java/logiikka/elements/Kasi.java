package logiikka.elements;

import logiikka.cards.Kortti;
import logiikka.cards.Arvo;
import java.util.ArrayList;
import java.util.Arrays;
/**
     * Kasi-luokka määrittelee pelikorteista koostuvan käden. Kädessä on oltava
     * vähintään kaksi Kortti-oliota, jotka määritetään konstruktorissa. Käteen
     * lisätään uusia Kortti-olioita addKortti-metodilla.
     */
public class Kasi implements Comparable<Kasi> {

    private ArrayList<Kortti> kortit;
    private boolean dealer;
    private boolean valmis;
    private boolean doubled;
    private boolean splitted;
    /**
     * Luo käden parametrina annetuista korteista
     * @param kortti1 ensimmainen kortti
     * @param kortti2 toinen kortti
     */
    public Kasi(Kortti kortti1, Kortti kortti2) {
        this.kortit = new ArrayList();
        kortit.add(kortti1);
        kortit.add(kortti2);
    }

    // TILAMETODIT
    /**
     * kertoo, onko käsi jakajan kasi
     * @return true jos Kasi on dealer, false jos Kasi ei ole dealer
     */
    public boolean isDealer() {
        return dealer;
    }
    /**
     * kertoo, onko kättä tuplattu
     * @return true jos Kasi on tuplattu, false jos Kasi ei ole tuplattu
     */
    public boolean isDoubled() {
        return doubled;
    }
    /**
     * kertoo, onko käsi valmis
     * @return true jos Kasi on valmis, false jos Kasi ei ole valmis
     */
    public boolean isValmis() {
        boolean valmius = valmis || isBust() || isDoubled();
        return valmius;
    }
    /**
     * kertoo, onko kädessä splitatusta kädestä otettuja kortteja
     * @return true jos Kasi on splitattu, false jos Kasi ei ole splitattu
     */
    public boolean isSplitted() {
        return splitted;
    }

    /**
     * kertoo, onko käsi blackjack
     * @return true jos Kasi on blackjack, false jos Kasi muu kuin blackjack
     */
    public boolean isBlackjack() {
        if (this.getArvo() == 999) {
            return true;
        }
        return false;
    }
    /**
     * kertoo, onko käsi poikki
     * @return true jos kasi on bust, false jos kasi ei ole bust
     */
    public boolean isBust() {
        return getArvo() > 21 && !isBlackjack();
    }

    public boolean hasAce() {
        for (Kortti kortti : this.kortit) {
            if (kortti.getArvo() == Arvo.ASSA) {
                return true;
            }
        }
        return false;
    }
    /**
     * kertoo, onko kädessä ässää
     * @return true jos Kasi-olion voi splitata, false jos Kasi-oliota ei voi splitata
     */
    public boolean isSplittable() {
        return getKortti(0).getNumeroarvo() == getKortti(1).getNumeroarvo()
                && kortit.size() == 2;
    }

    /**
     * kertoo, onko käden toinen kortti ässä
     * @return true jos Kasi-olion toinen kortti on arvoltaan assa, false jos ei ole
     */
    public boolean nakyvaAssa() {
        return kortit.get(1).getArvo() == Arvo.ASSA;

    }
    // SETTERIT
    
    /**
     * määrittää käden valmiiksi
     */
    public void setValmis() {
        valmis = true;
    }
    /**
     * asettaa käden avoimeksi
     */
    public void setOpen() {
        dealer = false;
    }
    /**
     * asettaa käden tuplatuksi
     */
    public void setDoubled() {
        this.doubled = true;
    }
    /**
     * asettaa käden splitatuksi
     */
    public void setSplitted() {
        this.splitted = true;
    }
    /**
     * lisää parametrina annetun kortin käteen
     * @param k lisattava kortti
     */
    public void addKortti(Kortti k) {
        this.kortit.add(k);
    }
    /**
     * asettaa käden dealerin kädeksi
     */
    public void setDealer() {
        this.dealer = true;

    }
    // GETTERIT
    /**
     * Palauttaa käden kortit ArrayListinä
     * @return lista korteista
     */
    public ArrayList<Kortti> getKortit() {
        return kortit;
    }
    /**
     * Palauttaa kortin, joka on listassa parametrina annetussa indeksissä korttisäiliönä
     * toimivassa ArrayListissä.
     * @return parametrin määräämässä indeksissä oleva kortti kadessa
     * @param index maaratty indeksi
     */
    public Kortti getKortti(int index) {
        return this.kortit.get(index);
    }
    /**
     * Palauttaa käden arvon kokonaislukuna
     * @return Kasi-olion arvo kokonaislukuna
     */
    public int getArvo() {
        int arvo = 0;
        if (!hasAce()) {
            return laskeArvo();
        } else {
            if (laskeArvo() + 10 == 21 && this.kortit.size() == 2) {
                return 999;
            }
            if (laskeArvo() + 10 <= 21) {
                return laskeArvo() + 10;
            } else if (laskeArvo() > 21) {
                return laskeArvo();
            } else {
                return laskeArvo();
            }
        }
    }
    /**
     * Palauttaa käden Kortti-olioiden lukumäärän
     * @return palauttaa korttien lkm
     */

    public int korttienLkm() {
        return this.kortit.size();
    }
    
    private int laskeArvo() {
        int arvo = 0;
        for (Kortti kortti : this.kortit) {
            arvo += kortti.getArvo().getArvo();
        }
        return arvo;
    }
    /**
     * Palauttaa merkkijonoesityksen käden arvosta (myös pehmeä arvo, kun kädessä A)
     * @return esim. (10/20), kun kädessä A ja 9.
     */
    public String getArvoS() {
        if (isBlackjack()) {
            return "BLACKJACK";
        } else if (hasAce() && laskeArvo() + 10 <= 21) {
            return laskeArvo() + " / " + getArvo();
        }

        return "" + getArvo();
    }
    /**
     * Palauttaa kaikki kortit merkkijonona järjestyksessä 1..n.
     * @return palauttaa kortit merkkijonona
     */
    public String kortit() {
        String stringi = "";
        for (Kortti k : this.kortit) {
            stringi += k + " ";
        }
        return stringi;
    }

    // OVERRIDED
    @Override
    public String toString() {
        if (!dealer) {
            String kortit = "";
            for (Kortti k : this.kortit) {
                kortit += k.toString() + " ";
            }
            kortit = kortit.substring(0, kortit.length() - 1);
            kortit = kortit + " " + getArvoS();

            return kortit;
        } else {
            return "XX " + this.kortit.get(1).toString();
        }
    }
    @Override
    public int compareTo(Kasi jakajanKasi) {
        if (isBust()) {
            return -1;
        } else if (jakajanKasi.isBust()) {
            return 1;
        } else if (this.isBlackjack() && !jakajanKasi.isBlackjack()) {
            return 2;
        } else if (this.isBlackjack() && jakajanKasi.isBlackjack()) {
            return 0;
        } else {
            if (!isBust() && (this.getArvo() > jakajanKasi.getArvo())) {
                return 1;
            } else if (!isBust() && (this.getArvo() < jakajanKasi.getArvo())) {
                return -1;
            } else {
                return 0;
            }
        }
    }
    @Override
    public int hashCode() {
        int bj = 0;
        if (isBlackjack()) {
            bj = 4839;
        }
        return this.getKortit().get(0).hashCode() + this.getKortti(1).hashCode();
    }

}
