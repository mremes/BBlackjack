
package mremes.bblackjack.logiikka;


public enum Arvo {
    KAKKONEN (2),
    KOLMONEN (3),
    NELONEN (4),
    VITONEN (5),
    KUTONEN (6),
    SEISKA (7),
    KASI (8),
    YSI (9),
    KYMPPI (10),
    JATKA (11),
    ROUVA (12),
    KUNKKU (13),
    ASSA (14);
    
    private final int numeroarvo;
    Arvo(int arvo) {
        this.numeroarvo = arvo;
    }
    
    public int getArvo() {
        return numeroarvo;
    }
    
    
    
    
    
}
