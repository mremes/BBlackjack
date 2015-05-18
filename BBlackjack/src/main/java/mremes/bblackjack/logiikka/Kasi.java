package mremes.bblackjack.logiikka;

import java.util.ArrayList;

public abstract class Kasi {

    private ArrayList<Kortti> kortit;

    public Kasi(Kortti kortti1, Kortti kortti2) {
        this.kortit = new ArrayList<>();
        this.kortit.add(kortti1);
        this.kortit.add(kortti2);
    }

    public ArrayList<Kortti> getKortit() {
        return kortit;
    }

    public int getArvo() {
        int arvo = 0;
        if (!onkoAssaa()) {
            return laskeArvo(this.kortit);
        } else {
            if(laskeArvo(this.kortit) + 10 == 21 && this.kortit.size() == 2) {
                return 999;
            }
            if (laskeArvo(this.kortit) + 10 < 21) {
                return laskeArvo(this.kortit) + 10;
            } else if(laskeArvo(this.kortit) > 21) {
                return laskeArvo(this.kortit);
            } else {
                return laskeArvo(this.kortit);
            }
        }
    }

    @Override
    public String toString() {
        String kortit = "";
        for (Kortti k : this.kortit) {
            kortit += k.toString() + ", ";
        }
        kortit = kortit.substring(0, kortit.length() - 2);
        return kortit;
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
        if(kortit.get(0).getArvo() == kortit.get(1).getArvo() && kortit.size() == 2) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean onBlackjack() {
        if(((kortit.get(0).getNumeroarvo() == 10 && kortit.get(1).getNumeroarvo() == 1) || (kortit.get(0).getNumeroarvo() == 1 && kortit.get(1).getNumeroarvo() == 10)) && kortit.size() == 2) {
            return true;
        }
        return false;
    }
    
    public boolean onBust() {
        if(getArvo() > 21) {
            return true;
        }
        return false;
    }

}
