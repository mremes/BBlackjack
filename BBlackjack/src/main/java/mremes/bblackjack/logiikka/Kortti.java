package mremes.bblackjack.logiikka;

public class Kortti implements Comparable<Kortti> {
    private Maa maa;
    private Arvo arvo;
    
    public Kortti(Maa maa, Arvo arvo) {
        this.maa = maa;
        this.arvo = arvo;
    }

    public Arvo getArvo() {
        return arvo;
    }

    public Maa getMaa() {
        return maa;
    }
    
    public int getNumeroarvo() {
        return this.arvo.getArvo();
    }
    
    public boolean samaArvo(Kortti k) {
        if (getNumeroarvo() == k.getNumeroarvo()) {
            return true;
        }
        return false;
    }
    
    public String getArvoS() {
        return "(" + getArvo() + ")";
    }
    @Override
    public String toString() {
        return this.maa + this.arvo.getKirjain();
    }
    
    @Override
    public int compareTo(Kortti k) {
        if(getNumeroarvo() > k.getNumeroarvo()) {
            return 1;
        } else if (getNumeroarvo() < k.getNumeroarvo()) {
            return -1;
        } else {
            return 0;
        }
    }
    
    @Override
    public int hashCode() {
        return getNumeroarvo() + getMaa().hashCode();
    }
    
   
}
