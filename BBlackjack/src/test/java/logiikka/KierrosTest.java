/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka;

import logiikka.cards.Arvo;
import logiikka.cards.Kortti;
import logiikka.cards.Maa;
import logiikka.elements.Kasi;
import logiikka.elements.Pelaaja;
import logiikka.utilities.Jakaja;
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
public class KierrosTest {
    private Kierros kierros;
    private Jakaja jakaja;
    @Before
    public void setUp() {
        this.kierros = new Kierros(new Pelaaja(1000));
        this.jakaja = new Jakaja();
        
    }
    
    @Test
    public void jaaKadetToimii() {
        assertEquals(0, kierros.getPelaajanKadet().size());
        assertTrue(kierros.getJakajanKasi() == null);
        kierros.jaaKadet();
        assertEquals(1, kierros.getPelaajanKadet().size());
        assertTrue(kierros.getJakajanKasi() != null);
        assertTrue(kierros.getJakajanKasi().isDealer());
    }
    
    @Test
    public void oikeaMaaraKortteja() {
        kierros.jaaKadet();
        assertEquals(2, kierros.getPelaajanKasi().getKortit().size());
        assertEquals(2, kierros.getJakajanKasi().getKortit().size());
        
    }
    @Test
    public void standTekeeKadestaValmiin() {
        kierros.jaaKadet();
        kierros.stand(kierros.getPelaajanKasi());
        assertTrue(kierros.getPelaajanKasi().isValmis());
    }
    
    @Test
    public void splittausToimii1() {
        kierros.jaaKadet();
        kierros.setPanos(100);
        Kasi kasi = kierros.getPelaajanKasi();
        kierros.split(kierros.getPelaajanKasi());
        assertTrue(kasi.isValmis());
        assertEquals(2, kierros.getPelaajanKadet().size());
        assertEquals(800, kierros.getPelaaja().getBalance());
        assertTrue(kierros.splitattu());
    }
    
    @Test
    public void splittausToimii2() {
        Kasi k = new Kasi(new Kortti(Maa.HERTTA, Arvo.ASSA), new Kortti(Maa.PATA, Arvo.ASSA));
        kierros.split(k);
        assertTrue(kierros.getPelaajanKadet().get(0).isValmis());
        assertTrue(kierros.getPelaajanKadet().get(1).isValmis());
    }
    
    @Test
    public void doubleToimii() {
        kierros.jaaKadet();
        kierros.setPanos(100);
        kierros.doubl(kierros.getPelaajanKasi());
        assertEquals(800, kierros.getPelaaja().getBalance());
        assertTrue(kierros.getPelaajanKasi().isValmis());
        assertTrue(kierros.getPelaajanKasi().isDoubled());
    }
    
    @Test
    public void hitToimii() {
        kierros.jaaKadet();
        assertEquals(2, kierros.getPelaajanKasi().getKortit().size());
        kierros.hit(kierros.getPelaajanKasi());
        assertEquals(3, kierros.getPelaajanKasi().getKortit().size());
    }
    
    @Test
    public void setPanosToimii() {
        kierros.jaaKadet();
        kierros.setPanos(100);
        assertEquals(100, kierros.getPanos());
    }
    
    @Test
    public void setPanosVahentaaPelaajanSaldoaOikein() {
        kierros.jaaKadet();
        kierros.setPanos(100);
        assertEquals(900, kierros.getPelaaja().getBalance());
    }
    
    @Test
    public void josPanosSuurempiKuinPelaajanBalanceEiVahenna() {
        kierros.jaaKadet();
        kierros.setPanos(1100);
        assertEquals(1000, kierros.getPelaaja().getBalance());
    }
    
    @Test
    public void vikaKorttiToimii() {
        Kasi k = new Kasi(new Kortti(Maa.HERTTA, Arvo.KAKKONEN), new Kortti(Maa.PATA, Arvo.NELONEN));
        assertEquals(new Kortti(Maa.PATA, Arvo.NELONEN), kierros.getVikaKortti(k));
    }
    
    
}
