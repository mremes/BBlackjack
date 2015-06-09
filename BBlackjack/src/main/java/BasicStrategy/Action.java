

package BasicStrategy;

public enum Action {
    HIT("HIT"),
    STAND("STAND"),
    DOUBLEH("DOUBLE (or HIT)"),
    DOUBLES("DOUBLE (or STAND)"),
    SPLIT("SPLIT");
    
    private final String action;
    
    Action(String action) {
        this.action = action;
    }
    
    @Override
    public String toString() {
        return action;
    }
}
