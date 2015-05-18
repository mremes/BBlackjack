package mremes.bblackjack.logiikka;

public class Kortti {
    private Maa maa;
    private Arvo arvo;
    
    public Kortti(Maa maa, Arvo arvo) {
        this.maa = maa;
        this.arvo = arvo;
    }
    
    public Kortti(Arvo arvo) {
        this.maa = null;
        this.arvo = arvo;
    }

    public Arvo getArvo() {
        return arvo;
    }

    public Maa getMaa() {
        return maa;
    }
    
    @Override
    public String toString() {
        return this.maa + " " + this.arvo.getKirjain();
    }
    
    public int getNumeroarvo() {
        return this.arvo.getArvo();
    }
    
   
}
