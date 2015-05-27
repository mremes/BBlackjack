/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bblackjack.logiikka;

import mremes.bblackjack.deckofcards.Kortti;
import mremes.bblackjack.utilities.Jakaja;
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
    public void sekoitaKortitToimii() {
        Jakaja.sekoitaKortit();
        Kortti k1 = Jakaja.annaKortti();
        Kortti k2 = Jakaja.annaKortti();
        Kortti k3 = Jakaja.annaKortti();
        Kortti k4 = Jakaja.annaKortti();
        Kortti k5 = Jakaja.annaKortti();
        // tn, että on samat kortit on 1/380204032, joten voimme luottaa
        // sekoituksen onnistumiseen jos eri kortit
        Jakaja.sekoitaKortit();
        assertFalse(Jakaja.annaKortti().equals(k1));
        assertFalse(Jakaja.annaKortti().equals(k2));
        assertFalse(Jakaja.annaKortti().equals(k3));
        assertFalse(Jakaja.annaKortti().equals(k4));
        assertFalse(Jakaja.annaKortti().equals(k5));
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
    
    @Test
    public void counterResetToimii() {
        Jakaja.resetCounter();
        assertEquals(0, Jakaja.counter());
    }
}
