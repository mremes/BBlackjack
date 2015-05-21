package bblackjack.logiikka;

import mremes.bblackjack.deckofcards.Arvo;
import mremes.bblackjack.elements.Kasi;
import mremes.bblackjack.deckofcards.Kortti;
import mremes.bblackjack.deckofcards.Maa;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mattiremes
 */
public class KasiTest {
    
    Kasi blackjack;
    Kasi softKasi;
    @Before
    public void setUp() {
        blackjack = new Kasi(new Kortti(Maa.HERTTA, Arvo.ASSA), new Kortti(Maa.PATA, Arvo.JATKA));
        softKasi = new Kasi(new Kortti(Maa.HERTTA, Arvo.ASSA), new Kortti(Maa.PATA, Arvo.NELONEN));
    }
    
    @Test
    public void onBlackjackToimii() {
        
        assertTrue(blackjack.isBlackjack());
    }
    
    @Test
    public void onBustToimii() {
        softKasi.addKortti(new Kortti(Maa.HERTTA, Arvo.KUNKKU));
        softKasi.addKortti((new Kortti(Maa.HERTTA, Arvo.KUNKKU)));
        assertTrue(softKasi.isBust());
    }
    
    @Test
    public void assaEiNay() {
        assertTrue(!blackjack.nakyvaAssa());
    }
    
    @Test
    public void suljettuKasiKunDealer() {
        blackjack.setDealer();
        assertEquals(blackjack.toString(), "XX ♠J");
    }
    
    @Test
    public void avoinKasiNakyy() {
        blackjack.setOpen();
        assertEquals(blackjack.toString(), "♥A ♠J (BLACKJACK)");
    }
    
    @Test
    public void softKasiArvoStringinaOikein() {
        assertEquals(softKasi.getArvoS(), "(5 / 15)");
    }
    
    @Test
    public void softKasiMuuttuuHardiksiKunLisataanYhdeksan() {
        softKasi.addKortti(new Kortti(Maa.PATA, Arvo.YSI));
        assertEquals(softKasi.getArvoS(), "(14)");
    }
}
