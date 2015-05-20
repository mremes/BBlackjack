package mremes.bblackjack.logiikka;

import java.util.ArrayList;
import java.util.Arrays;

public class Kasi implements Comparable<Kasi> {

    private ArrayList<Kortti> kortit;
    private boolean dealer;
    private boolean soft;
    private boolean valmis;

    public Kasi(Kortti kortti1, Kortti kortti2) {
        this.kortit = new ArrayList();
        kortit.add(kortti1);
        kortit.add(kortti2);
        this.dealer = false;
        this.soft = false;
        this.valmis = false;
    }
    // TILAMETODIT
    public boolean isDealer() {
        return dealer;
    }
    public boolean isValmis() {
        return valmis;
    }
    public boolean onBlackjack() {
        if (((kortit.get(0).getNumeroarvo() == 10 && kortit.get(1).getNumeroarvo() == 1) || (kortit.get(0).getNumeroarvo() == 1 && kortit.get(1).getNumeroarvo() == 10)) && kortit.size() == 2) {
            return true;
        }
        return false;
    }
    public boolean onBust() {
        if (getArvo() > 21 && !onBlackjack()) {
            return true;
        }
        return false;
    }
    private boolean onkoAssaa() {
        for (Kortti kortti : this.kortit) {
            if (kortti.getArvo() == Arvo.ASSA) {
                return true;
            }
        }
        return false;
    }
    // DEALERIN TILAMETODI
    public boolean nakyvaAssa() {
        if(kortit.get(1).getArvo() == Arvo.ASSA) {
            return true;
        }
        return false;
    }
    // SETTERIT
    public void valmis() {
        valmis = true;
    }
    public void avaa() {
        dealer = false;
    }
    public void lisaaKortti(Kortti k) {
        this.kortit.add(k);
    }
    public void setDealer() {
        this.dealer = true;
        
    }
    // GETTERIT
    public ArrayList<Kortti> getKortit() {
        return kortit;
    }
    public Kortti getKortti(int index) {
        return this.kortit.get(index);
    }
    public int getArvo() {
        int arvo = 0;
        if (!onkoAssaa()) {
            return laskeArvo();
        } else {
            if (laskeArvo() + 10 == 21 && this.kortit.size() == 2) {
                return 999;
            }
            if (laskeArvo() + 10 <= 21) {
                this.soft = true;
                return laskeArvo() + 10;
            } else if (laskeArvo() > 21) {
                return laskeArvo();
            } else {
                return laskeArvo();
            }
        }
    } // LASKEE KAIKKIEN YHDISTELMIEN ARVOT
    private int laskeArvo() {
        int arvo = 0;
        for (Kortti kortti : this.kortit) {
            arvo += kortti.getArvo().getArvo();
        }
        return arvo;
    } // LASKEE VAIN KADET ILMAN A:TA
    public String getArvoS() {
        if (onBlackjack()) {
            return "(BLACKJACK)";
        } else if (onkoAssaa() && laskeArvo() + 10 <= 21) {
            soft = true;
            return "(" + laskeArvo() + " / " + getArvo() + ")";
        }
        
        return "(" + getArvo() + ")";
    } // PALAUTTAA ARVON STRINGINÄ
    // OVERRIDED
    @Override
    public String toString() {
        if (!dealer) {
            String kortit = "";
            for (Kortti k : this.kortit) {
                kortit += k.toString() + " ";
            }
            kortit = kortit.substring(0, kortit.length() - 1);
            return kortit;
        } else {
            return "XX " + this.kortit.get(1).toString();
        }
    }
    @Override
    public int compareTo(Kasi jakajanKasi) {
        if (onBust()) {
            return -1;
        } else if (jakajanKasi.onBust()) {
            return 1;
        }

        if (this.onBlackjack() && jakajanKasi.onBlackjack()) {
            return 0;
        } else {
            if (!onBust() && (this.getArvo() > jakajanKasi.getArvo())) {
                return 1;
            } else if (!onBust() && (this.getArvo() < jakajanKasi.getArvo())) {
                return -1;
            } else {
                return 0;
            }
        }
    }
    @Override
    public int hashCode() {
        int bj = 0;
        if (onBlackjack()) {
            bj = 4839;
        }
        return this.getArvo() + this.getKortit().get(0).hashCode() + this.getKortit().get(1).hashCode() + bj;
    }
    
}
