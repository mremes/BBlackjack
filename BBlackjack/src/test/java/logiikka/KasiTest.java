package logiikka;

import logiikka.cards.Arvo;
import logiikka.elements.Kasi;
import logiikka.cards.Kortti;
import logiikka.cards.Maa;
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
    Kasi splittable;
    Kasi nakyvaBlackjack;
    @Before
    public void setUp() {
        blackjack = new Kasi(new Kortti(Maa.HERTTA, Arvo.ASSA), new Kortti(Maa.PATA, Arvo.JATKA));
        softKasi = new Kasi(new Kortti(Maa.HERTTA, Arvo.ASSA), new Kortti(Maa.PATA, Arvo.NELONEN));
        splittable = new Kasi(new Kortti(Maa.HERTTA, Arvo.KUNKKU), new Kortti(Maa.PATA, Arvo.KUNKKU));
        nakyvaBlackjack = new Kasi(new Kortti(Maa.HERTTA, Arvo.JATKA), new Kortti(Maa.PATA, Arvo.ASSA));
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
        splittable.addKortti(new Kortti(Maa.HERTTA, Arvo.KUNKKU));
        assertTrue(splittable.isBust());
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
        assertEquals("♥A ♠J BLACKJACK", blackjack.toString());
    }
    
    @Test
    public void softKasiArvoStringinaOikein() {
        assertEquals(softKasi.getArvoS(), "5 / 15");
    }
    
    @Test
    public void softKasiMuuttuuHardiksiKunLisataanYhdeksan() {
        softKasi.addKortti(new Kortti(Maa.PATA, Arvo.YSI));
        assertEquals(softKasi.getArvoS(), "14");
    }
    
    @Test
    public void splittableToimii() {
        splittable.setSplitted();
        assertTrue(splittable.isSplitted());
    }
    
    @Test
    public void doubledToimii() {
        splittable.setDoubled();
        assertTrue(splittable.isDoubled());
    }
    
    @Test
    public void onJakajaToimii() {
        splittable.setDealer();
        assertTrue(splittable.isDealer());
    }
    
    @Test
    public void onVakuutettuToimii() {
        splittable.setInsurance();
        assertTrue(splittable.isInsured());
    }
    
    @Test
    public void onValmisToimii() {
        splittable.setValmis();
        assertTrue(splittable.isValmis());
    }
    
    @Test
    public void hasAceToimii() {
        assertTrue(blackjack.hasAce());
    }
    
    @Test
    public void oikeaDoablesKunEiVoiSplitataYksiKasi() {
        assertEquals("\nHIT, STAND, DOUBLE: ", softKasi.doables(1));
    }
    
    @Test
    public void oikeaDoablesKunVoiSplitataYksiKasi() {
        assertEquals("\nHIT, STAND, DOUBLE, SPLIT: ", splittable.doables(1));
    }
    
    @Test
    public void oikeaDoablesKunVoiSplitataUseampiKasi() {
        assertEquals("\nHIT, STAND, DOUBLE: ", splittable.doables(2));
    }
    
    @Test
    public void oikeaDoablesKunEiVoiSplitataUseampiKasi() {
        assertEquals("\nHIT, STAND, DOUBLE: ", splittable.doables(2));
    }
    
    @Test
    public void oikeaDoablesKunKateenLyoty() {
        softKasi.addKortti(new Kortti(Maa.HERTTA, Arvo.KOLMONEN));
        assertEquals("\nHIT, STAND: ", softKasi.doables(1));
        assertEquals("\nHIT, STAND: ", softKasi.doables(2));
    }
    
    @Test
    public void nakyvaBlackjackNakyvaAssa() {
        assertTrue(nakyvaBlackjack.nakyvaAssa());
    }
    
    @Test
    public void getArvoBlackjackeille() {
        assertEquals(999, nakyvaBlackjack.getArvo());
        assertEquals(999, blackjack.getArvo());
    }
    
    @Test
    public void getArvoSoftilleAssanKanssa() {
        assertEquals(15, softKasi.getArvo());
        softKasi.addKortti(new Kortti(Maa.HERTTA, Arvo.KUTONEN));
        assertEquals(21, softKasi.getArvo());
        softKasi.addKortti(new Kortti(Maa.HERTTA, Arvo.KYMPPI));
        assertEquals(21, softKasi.getArvo());
        softKasi.addKortti(new Kortti(Maa.HERTTA, Arvo.KUTONEN));
        assertEquals(27, softKasi.getArvo());
    }
    
    @Test
    public void getArvoBustedille() {
        splittable.addKortti(new Kortti(Maa.HERTTA, Arvo.NELONEN));
        assertEquals(24, splittable.getArvo());
    }
    
    @Test
    public void compareToToimii() {
        splittable.addKortti(new Kortti(Maa.HERTTA, Arvo.NELONEN));
        assertEquals(-1, splittable.compareTo(softKasi));
        assertEquals(1, blackjack.compareTo(softKasi));
    }
    
    @Test
    public void kasiStringinaToimii() {
        assertEquals("♥A ♠J ", blackjack.kortit());
    }
}
