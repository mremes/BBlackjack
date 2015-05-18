package mremes.bblackjack.logiikka;

public enum Maa {

    HERTTA, RUUTU, RISTI, PATA;

    @Override
    public String toString() {
        switch (this) {
            case HERTTA: return "H";
            case RUUTU: return "R";
            case RISTI: return "X";
            case PATA: return "P";
            default: throw new IllegalArgumentException();
        }
    }
}
