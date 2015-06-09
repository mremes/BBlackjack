

package BasicStrategy;

public enum Action {
    HIT("hit."),
    STAND("stand."),
    DOUBLEH("double or hit."),
    DOUBLES("double or stand."),
    SPLIT("split.");
    
    private final String action;
    
    Action(String action) {
        this.action = action;
    }
    
    @Override
    public String toString() {
        return action;
    }
}
