package logiikka.cards;

import logiikka.cards.Arvo;

public class Kortti implements Comparable<Kortti> {
    private Maa maa;
    private Arvo arvo;
    
    public Kortti(Maa maa, Arvo arvo) {
        this.maa = maa;
        this.arvo = arvo;
    }
    
    // GETTERIT
    public Arvo getArvo() {
        return arvo;
    }
    public Maa getMaa() {
        return maa;
    }
    public int getNumeroarvo() {
        return this.arvo.getArvo();
    }
    public String getArvoS() {
        return "(" + getArvo() + ")";
    }
    
    public String src() {
        return this.maa.getNimi() + this.arvo.getKirjain() + ".png";
    }
    // OVERRIDED
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
    
    @Override
    public boolean equals(Object o) {
        if(o.getClass() != this.getClass()) {
            return false;
        }
        
        Kortti k = (Kortti) o;
        
        if(k.getArvo() == this.getArvo() && k.getMaa() == this.getMaa()) {
            return true;
        }
        
        return false;
    }
}
