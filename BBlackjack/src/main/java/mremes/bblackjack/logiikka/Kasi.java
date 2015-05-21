package mremes.bblackjack.logiikka;

import java.util.ArrayList;
import java.util.Arrays;

public class Kasi implements Comparable<Kasi> {

    private ArrayList<Kortti> kortit;
    private boolean dealer;
    private boolean soft;
    private boolean valmis;
    private boolean insured;

    public Kasi(Kortti kortti1, Kortti kortti2) {
        this.kortit = new ArrayList();
        kortit.add(kortti1);
        kortit.add(kortti2);
        this.dealer = false;
        this.soft = false;
        this.valmis = false;
        this.insured = false;
    }

    // TILAMETODIT
    public boolean isDealer() {
        return dealer;
    }
    public boolean isValmis() {
        return valmis;
    }
    public boolean isBlackjack() {
        if (((kortit.get(0).getNumeroarvo() == 10 && kortit.get(1).getNumeroarvo() == 1) || (kortit.get(0).getNumeroarvo() == 1 && kortit.get(1).getNumeroarvo() == 10)) && kortit.size() == 2) {
            return true;
        }
        return false;
    }
    public boolean isBust() {
        if (getArvo() > 21 && !isBlackjack()) {
            return true;
        }
        return false;
    }
    private boolean hasAce() {
        for (Kortti kortti : this.kortit) {
            if (kortti.getArvo() == Arvo.ASSA) {
                return true;
            }
        }
        return false;
    }
    public boolean isInsured() {
        return insured;
    }
    public boolean isSplittable() {
        return getKortti(0).getNumeroarvo() == getKortti(1).getNumeroarvo()
                && kortit.size() == 2;
    }

    // DEALERIN TILAMETODI
    public boolean nakyvaAssa() {
        if (kortit.get(1).getArvo() == Arvo.ASSA) {
            return true;
        }
        return false;
    }
    public void doables() {
        String dble = "DOUBLE";
        String split = "SPLIT";
        String komennot = "\nHIT, STAND";
        if (korttienLkm() == 2) {
            komennot += ", " + dble;
        }
        if (isSplittable()) {
            komennot += ", " + split;
        }
        System.out.print(komennot + ": ");
    }
    // SETTERIT
    public void setValmis() {
        valmis = true;
    }
    public void setOpen() {
        dealer = false;
    }
    public void addKortti(Kortti k) {
        this.kortit.add(k);
    }
    public void setDealer() {
        this.dealer = true;

    }
    public void setInsurance() {
        this.insured = true;
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
        if (!hasAce()) {
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
    public int korttienLkm() {
        return this.kortit.size();
    }
    private int laskeArvo() {
        int arvo = 0;
        for (Kortti kortti : this.kortit) {
            arvo += kortti.getArvo().getArvo();
        }
        return arvo;
    } // LASKEE VAIN KADET ILMAN A:TA
    public String getArvoS() {
        if (isBlackjack()) {
            return "(BLACKJACK)";
        } else if (hasAce() && laskeArvo() + 10 <= 21) {
            soft = true;
            return "(" + laskeArvo() + " / " + getArvo() + ")";
        }

        return "(" + getArvo() + ")";
    } // PALAUTTAA ARVON STRINGINÄ
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
        }

        if (this.isBlackjack() && jakajanKasi.isBlackjack()) {
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
        return this.getArvo() + this.getKortit().get(0).hashCode() + this.getKortit().get(1).hashCode() + bj;
    }

}
