/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logiikka;

import logiikka.cards.Kortti;
import logiikka.utilities.Jakaja;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author mrremes
 */
public class JakajaTest {
    
    private Jakaja jakaja;
    @Before
    public void setUp() {
        jakaja = new Jakaja();
    }
    
    @Test
    public void counterAlussaNolla() {
        assertEquals(0, Jakaja.counter());
    }
    
    @Test
    public void counterKasvaaKunJakaa() {
        Jakaja.annaKortti();
        assertEquals(1, Jakaja.counter());
    }
    
    @Test
    public void counterNollaKunSekoittaa() {
        Jakaja.sekoitaKortit();
        assertEquals(0, Jakaja.counter());
    }
    
    @Test
    public void counterKasvaaKahdellaKunJakaaKaden() {
        Jakaja.sekoitaKortit();
        Jakaja.uusiKasi();
        assertEquals(2, Jakaja.counter());
    }
    
    @Test
    public void counterKasvaaNeljallaKunJakaaKaksiKatta() {
        Jakaja.sekoitaKortit();
        Jakaja.uusiKasi();
        Jakaja.uusiKasi();
        assertEquals(4, Jakaja.counter());
    }
    
    @Test
    public void antaaEdellisenKortin() {
        Jakaja.sekoitaKortit();
        Kortti k1 = Jakaja.annaKortti();
        assertEquals(k1, Jakaja.edellinenKortti());
    }
    
    @Test
    public void korttejaJaljellaOikeaMaara() {
        Jakaja.sekoitaKortit();
        assertEquals(416, Jakaja.jaljellaKortteja());
        Jakaja.uusiKasi();
        assertEquals(414, Jakaja.jaljellaKortteja());
        Jakaja.annaKortti();
        assertEquals(413, Jakaja.jaljellaKortteja());
        
    }
}
