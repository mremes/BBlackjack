package logiikka.cards;

/**
 * Enum-luokka, joka määrittelee kortin arvon laadun ja numeroarvon Black Jack-
 * pelissä.
 *
 * @author mrremes
 */
public enum Arvo {
    KAKKONEN (2, "2"),
    KOLMONEN (3, "3"),
    NELONEN (4, "4"),
    VITONEN (5, "5"),
    KUTONEN (6, "6"),
    SEISKA (7, "7"),
    KASI (8, "8"),
    YSI (9, "9"),
    KYMPPI (10, "T"),
    JATKA (10, "J"),
    ROUVA (10, "Q"),
    KUNKKU (10, "K"),
    ASSA (1, "A");
    
    private final int numeroarvo;
    private final String kirjain;
    /**
     * Enum-luokka, joka määrittelee kortin arvon laadun ja numeroarvon Black
     * Jack- pelissä.
     *
     * @author mrremes
     */
    Arvo(int arvo, String kirjain) {
        this.numeroarvo = arvo;
        this.kirjain = kirjain;
    }
    
    // GETTERIT
    /**
     * Palauttaa numeroarvon
     *
     * @return numeroarvo kokonaislukuna
     */

    public int getArvo() {
        return numeroarvo;
    }
    /**
     * Palauttaa arvon merkkijonona
     *
     * @return merkkijono
     */
    public String getKirjain() {
        return this.kirjain;
    }
    
    
    
    
    
}
