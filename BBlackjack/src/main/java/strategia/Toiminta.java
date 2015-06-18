

package strategia;

/**
 * Kuvastaa pelissä tapahtuvia toimintoja. Hit tarkoittaa uuden kortin ottamista
 * , Stand tarkoittaa korttien ottamisen lopettamista, Doubleh yhden kortin ottamista
 * ja panoksen tuplaamista (jos ei mahdollista niin hit), Doubles yhden kortin ottamista
 * ja panoksen tuplaamista (jos ei mahdollista niin stand) ja split korttiparin splittaamista
 * @author mrremes
 */
public enum Toiminta {
    HIT("HIT"),
    STAND("STAND"),
    DOUBLEH("DOUBLE (or HIT)"),
    DOUBLES("DOUBLE (or STAND)"),
    SPLIT("SPLIT");
    
    private final String toiminta;
    
    Toiminta(String action) {
        this.toiminta = action;
    }
    
    @Override
    public String toString() {
        return toiminta;
    }
}
