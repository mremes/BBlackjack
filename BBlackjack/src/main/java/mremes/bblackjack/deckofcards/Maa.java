package mremes.bblackjack.deckofcards;

public enum Maa {

    HERTTA("♥"),
    RUUTU("♦"),
    RISTI("♣"),
    PATA("♠");
    
    private final String kirjain;
    
    Maa(String kirjain) {
        this.kirjain = kirjain;
    }

    @Override
    public String toString() {
        return this.kirjain;
    }
    
}
