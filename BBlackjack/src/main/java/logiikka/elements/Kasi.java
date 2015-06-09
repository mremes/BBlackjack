package logiikka.elements;

import logiikka.cards.Kortti;
import logiikka.cards.Arvo;
import java.util.ArrayList;
import java.util.Arrays;

public class Kasi implements Comparable<Kasi> {

    private ArrayList<Kortti> kortit;
    private boolean dealer;
    private boolean valmis;
    private boolean insured;
    private boolean doubled;
    private boolean splitted;
    /**
     * Kasi-luokka määrittelee pelikorteista koostuvan käden. Kädessä on oltava
     * vähintään kaksi Kortti-oliota, jotka määritetään konstruktorissa. Käteen
     * lisätään uusia Kortti-olioita addKortti-metodilla.
     * @param kortti1 pelikäden ensimmäinen kortti (jakajalla: piilossa oleva kortti)
     * @param kortti2 pelikäden toinen kortti (jakajalla: näkyvä kortti)
     */
    public Kasi(Kortti kortti1, Kortti kortti2) {
        this.kortit = new ArrayList();
        kortit.add(kortti1);
        kortit.add(kortti2);
    }

    // TILAMETODIT
    /**
     * kertoo, onko käsi jakajan kasi
     * @return 
     */
    public boolean isDealer() {
        return dealer;
    }
    /**
     * kertoo, onko kättä tuplattu
     * @return 
     */
    public boolean isDoubled() {
        return doubled;
    }
    /**
     * kertoo, onko käsi valmis
     * @return 
     */
    public boolean isValmis() {
        boolean valmius = valmis || isBust() || isDoubled();
        return valmius;
    }
    /**
     * kertoo, onko kädessä splitatusta kädestä otettuja kortteja
     * @return 
     */
    public boolean isSplitted() {
        return splitted;
    }
    /**
     * kertoo, onko kättä vakuutettu
     * @return 
     */
    public boolean isInsured() {
        return insured;
    }
    /**
     * kertoo, onko käsi blackjack
     * @return 
     */
    public boolean isBlackjack() {
        if (this.getArvo() == 999) {
            return true;
        }
        return false;
    }
    /**
     * kertoo, onko käsi poikki
     * @return 
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
     * @return 
     */
    public boolean isSplittable() {
        return getKortti(0).getNumeroarvo() == getKortti(1).getNumeroarvo()
                && kortit.size() == 2;
    }

    /**
     * kertoo, onko käden toinen kortti ässä
     * @return
     */
    public boolean nakyvaAssa() {
        return kortit.get(1).getArvo() == Arvo.ASSA;

    }
    /**
     * teksikäyttöliittymään liittyvä toiminta-merkkijonoesitys
     * @return merkkijono komennoista, jotka ovat käytettävissä TUI:ssa
     */
    public String doables(int kasienMaara) {
        String dble = "DOUBLE";
        String split = "SPLIT";
        String komennot = "\nHIT, STAND";
        if (korttienLkm() == 2) {
            komennot += ", " + dble;
        }
        if (isSplittable() && kasienMaara == 1) {
            komennot += ", " + split;
        }
        return komennot + ": ";
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
    /**
     * asettaa käden vakuutetuksi
     */
    public void setInsurance() {
        this.insured = true;
    }
    // GETTERIT
    /**
     * Palauttaa käden kortit ArrayListinä
     * @return ArrayList<Kortti> lista korteista
     */
    public ArrayList<Kortti> getKortit() {
        return kortit;
    }
    /**
     * Palauttaa kortin, joka on listassa parametrina annetussa indeksissä korttisäiliönä
     * toimivassa ArrayListissä.
     * @return 
     */
    public Kortti getKortti(int index) {
        return this.kortit.get(index);
    }
    /**
     * Palauttaa käden arvon kokonaislukuna
     * @return 
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
     * @return 
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
     * @return 
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
