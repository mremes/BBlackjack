package logiikka.cards;

/**
 * Maa-enum kertoo maan: joko hertta, risti, ruutu tai pata. Jokaisella maalla
 * on oma merkkinsä (UTF-8-merkeistä) sekä maan ilmaiseva merkkijono
 */
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

    /**
     * Palauttaa nimen merkkijonona.
     *
     * @return nimi merkkijonona
     */
    public String getNimi() {
        return this.nimi;
    }

    @Override
    public String toString() {
        return this.kirjain;
    }

}
