package bblackjack.logiikka;

import mremes.bblackjack.deckofcards.Arvo;
import mremes.bblackjack.deckofcards.Kortti;
import mremes.bblackjack.deckofcards.Maa;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KorttiTest {
    
    Kortti kortti;
    
    @Before
    public void setUp() {
        kortti = new Kortti(Maa.HERTTA, Arvo.ASSA);
    }
    
    @Test
    public void oikeaMaa() {
        assertEquals(kortti.getMaa(), Maa.HERTTA);
    }
    
    @Test
    public void oikeaArvo() {
        assertEquals(kortti.getArvo(), Arvo.ASSA);
    }
    
    @Test
    public void oikeaToString() {
        assertEquals(kortti.toString(), "♥A");
    }
    
    @Test
    public void oikeaNumeroarvo() {
        assertEquals(kortti.getNumeroarvo(), 1);
    }
    
    @Test
    public void vertailuToimiiKunSamaArvo() {
        assertEquals(kortti.compareTo(new Kortti(Maa.PATA, Arvo.ASSA)), 0);
    }
    
    @Test
    public void vertailuToimiiKunVerrattavaYlempi() {
        assertEquals(kortti.compareTo(new Kortti(Maa.PATA, Arvo.KAKKONEN)),-1);
    }
    


}
