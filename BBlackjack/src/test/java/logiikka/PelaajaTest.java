/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logiikka;

import logiikka.elements.Pelaaja;
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
public class PelaajaTest {
    
    Pelaaja p1;
    Pelaaja p2;
    
    @Before
    public void setUp() {
        p1 = new Pelaaja(1000);
        p2 = new Pelaaja(0);
    }
    
    @Test
    public void oikeaMaaraRahaa() {
        assertEquals(1000, p1.getBalance());
    }
    
    @Test
    public void eiRahaaOikein() {
        assertEquals(0, p2.getBalance());
    }
    
    @Test
    public void lisaaRahaaOikein() {
        p2.lisaaRahaa(1000);
        assertEquals(1000, p2.getBalance());
    }
    
    @Test
    public void veloittaaOikein() {
        p2.veloita(1000);
        assertEquals(-1000, p2.getBalance());
    }
    
    @Test
    public void balanceSailyyKunVeloitusNolla() {
        p1.veloita(0);
        assertEquals(1000, p1.getBalance());
    }
    
    @Test
    public void negatiivinenLisaysEiVaikutaBalanceen() {
        p1.lisaaRahaa(-20);
        assertEquals(1000, p1.getBalance());
    }
    
    @Test
    public void negatiivinenVeloitusEiVaikutaBalanceen() {
        p2.veloita(-20);
        assertEquals(0, p2.getBalance());
    }
    
}
