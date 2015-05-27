/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bblackjack.logiikka;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import mremes.bblackjack.elements.Kasi;
import mremes.bblackjack.tui.Kasino;
import mremes.bblackjack.tui.Kierros;
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
public class KierrosTest {

    private Kierros kierros;
    private Kasino kasino;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        this.kasino = new Kasino(new Scanner(System.in));
        this.kierros = new Kierros(kasino.getPelaaja(), kasino.getLukija());
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void eiKasiaValmiina() {
        assertEquals(0, kierros.getPelaajanKadet().size());
        assertNull(kierros.getJakajanKasi());

    }

    @Test
    public void kadetValmiina() {
        kierros.jaaKadet();
        assertEquals(1, kierros.getPelaajanKadet().size());
        assertNotNull(kierros.getJakajanKasi());
    }

    @Test
    public void tulostaaPelaajanKadenOikein() {
        kierros.jaaKadet();
        kierros.pelaaja();
        Kasi e = kierros.getJakajanKasi();
        for (Kasi k : kierros.getPelaajanKadet().keySet()) {
            e = k;
        }
        String tulostus = "\nYou: " + e.toString() + "\n";
        assertEquals(tulostus, outContent.toString());
    }

    @Test
    public void tekeeDealerin() {
        kierros.jaaKadet();
        assertTrue(kierros.getJakajanKasi().isDealer());
    }
    
}
