package mremes.bblackjack.logiikka;

import java.util.ArrayList;
import java.util.Arrays;

public class Kasi implements Comparable<Kasi> {

    private ArrayList<Kortti> kortit;
    private int panos;
    private boolean dealer;

    public Kasi(Kortti kortti1, Kortti kortti2, int panos) {
        this.kortit = new ArrayList();
        kortit.add(kortti1);
        kortit.add(kortti2);
        this.panos = panos;
        this.dealer = false;
    }

    public Kasi(Kortti kortti1, Kortti kortti2) {
        this.kortit = new ArrayList();
        kortit.add(kortti1);
        kortit.add(kortti2);
        this.panos = 0;
        this.dealer = true;

    }

    public ArrayList<Kortti> getKortit() {
        return kortit;
    }

    public Kortti getKortti(int index) {
        return this.kortit.get(index);
    }

    public int getArvo() {
        int arvo = 0;
        if (!onkoAssaa()) {
            return laskeArvo(this.kortit);
        } else {
            if (laskeArvo(this.kortit) + 10 == 21 && this.kortit.size() == 2) {
                return 999;
            }
            if (laskeArvo(this.kortit) + 10 < 21) {
                return laskeArvo(this.kortit) + 10;
            } else if (laskeArvo(this.kortit) > 21) {
                return laskeArvo(this.kortit);
            } else {
                return laskeArvo(this.kortit);
            }
        }
    }

    public void lisaaKortti(Kortti k) {
        this.kortit.add(k);
    }

    public boolean onkoAssaa() {
        for (Kortti kortti : this.kortit) {
            if (kortti.getArvo() == Arvo.ASSA) {
                return true;
            }
        }
        return false;
    }

    public int laskeArvo(ArrayList<Kortti> kortit) {
        int arvo = 0;
        for (Kortti kortti : kortit) {
            arvo += kortti.getArvo().getArvo();
        }
        return arvo;
    }

    public boolean samatKortit() {
        if (kortit.get(0).getArvo() == kortit.get(1).getArvo() && kortit.size() == 2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean onBlackjack() {
        if (((kortit.get(0).getNumeroarvo() == 10 && kortit.get(1).getNumeroarvo() == 1) || (kortit.get(0).getNumeroarvo() == 1 && kortit.get(1).getNumeroarvo() == 10)) && kortit.size() == 2) {
            return true;
        }
        return false;
    }

    public boolean onBust() {
        if (getArvo() > 21) {
            return true;
        }
        return false;
    }

    public boolean onDealerinKasi() {
        return this.dealer;
    }

    public int getPanos() {
        return panos;
    }

    public void tuplaa() {
        this.panos = panos * 2;
    }
    
    public void avaa() {
        dealer = false;
    }

    public String getArvoS() {
        return "(" + getArvo() + ")";
    }

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
        }

        if (this.onBlackjack() && jakajanKasi.onBlackjack()) {
            return 0;
        } else if (this.onBlackjack() && !jakajanKasi.onBlackjack()) {
            return 1;
        } else if (!this.onBlackjack() && jakajanKasi.onBlackjack()) {
            return -1;
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
