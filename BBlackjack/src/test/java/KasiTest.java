import mremes.bblackjack.logiikka.Arvo;
import mremes.bblackjack.logiikka.Kasi;
import mremes.bblackjack.logiikka.Kortti;
import mremes.bblackjack.logiikka.Maa;
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
        assertTrue(blackjack.onBlackjack());
    }
    
    @Test
    public void onBustToimii() {
        assertTrue(!blackjack.onBust());
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
        blackjack.avaa();
        assertEquals(blackjack.toString(), "♥A ♠J");
    }
    
    @Test
    public void softKasiArvoStringinaOikein() {
        assertEquals(softKasi.getArvoS(), "(5 / 15)");
    }
    
    @Test
    public void softKasiMuuttuuHardiksiKunLisataanYhdeksan() {
        softKasi.lisaaKortti(new Kortti(Maa.PATA, Arvo.YSI));
        assertEquals(softKasi.getArvoS(), "(14)");
    }
}
