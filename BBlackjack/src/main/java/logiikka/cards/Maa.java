package logiikka.cards;

public enum Maa {

    HERTTA("♥", "Hertta"),
    RUUTU("♦", "Ruutu"),
    RISTI("♣", "Risti"),
    PATA("♠", "Pata");
    
    private final String kirjain;
    private final String nimi;
    
    Maa(String kirjain, String nimi) {
        this.kirjain = kirjain;
        this.nimi = nimi;
    }

    public String getNimi() {
        return this.nimi;
    }
    
    @Override
    public String toString() {
        return this.kirjain;
    }
    
}
