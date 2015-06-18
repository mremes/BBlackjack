package logiikka.cards;

import logiikka.cards.Arvo;

/**
     * Luokka määrittelee pelikortin, jolla on maa ja arvo (Maa- ja Arvo-enumit)
     */
public class Kortti implements Comparable<Kortti> {
    private Maa maa;
    private Arvo arvo;
    
    
    public Kortti(Maa maa, Arvo arvo) {
        this.maa = maa;
        this.arvo = arvo;
    }
    
    // GETTERIT
    /**
     * palauttaa kortin Arvo-enumin
     * @return kortin Arvo-enum
     */
    public Arvo getArvo() {
        return arvo;
    }
    /**
     * palauttaa kortin Maa-enumin
     * @return kortin Maa-enum
     */
    public Maa getMaa() {
        return maa;
    }
     /**
     * palauttaa kortin numeroarvon
     * @return palauttaa kortin numeroarvon
     */
    public int getNumeroarvo() {
        return this.arvo.getArvo();
    }
    /**
     * palauttaa kortin numeroarvon merkkijonona kaarisulkeiden sisällä
     * @return kortin numeroarvo Stringinä
     */
    public String getArvoS() {
        return "(" + getArvo() + ")";
    }
    /**
     * palauttaa kortin .png-kuvan tiedostonimen, joka koostuu maan merkkijonosta ja
     * arvon kirjaimesta sekä .png-päätteestä
     * @return kortin .png-kuvatiedoston nimi
     */
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
