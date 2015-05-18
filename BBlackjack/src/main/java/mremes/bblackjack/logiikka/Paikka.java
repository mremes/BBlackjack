package mremes.bblackjack.logiikka;

public class Paikka {
    private int nro;
    private Kasi kasi;
    private Kasi doubled;
    private int panos;
    
    public Paikka(int nro) {
        this.nro = nro;
        this.kasi = null;
        this.panos = 0;
        this.doubled = null;
    }

    public void setKasi(Kasi kasi) {
        this.kasi = kasi;
    }

    public void setPanos(int panos) {
        this.panos = panos;
    }

    public void setDoubled(Kasi doubled) {
        this.doubled = doubled;
    }
    
    

    public Kasi getKasi() {
        return this.kasi;
    }
    
}
