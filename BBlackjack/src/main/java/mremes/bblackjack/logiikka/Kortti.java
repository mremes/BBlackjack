package mremes.bblackjack.logiikka;

public class Kortti {
    private Maa maa;
    private String arvo;
    
    public Kortti(Maa maa, String arvo) {
        this.maa = maa;
        this.arvo = arvo;
    }
    
    @Override
    public String toString() {
        return this.maa + " " + this.arvo;
    }
}
