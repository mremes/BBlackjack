package mremes.bblackjack.logiikka;

public enum Maa {

    HERTTA, RUUTU, RISTI, PATA;

    @Override
    public String toString() {
        switch (this) {
            case HERTTA: return "Hertta";
            case RUUTU: return "Ruutu";
            case RISTI: return "Risti";
            case PATA: return "Pata";
            default: throw new IllegalArgumentException();
        }
    }
}
