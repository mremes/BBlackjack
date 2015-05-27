/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userInterface;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import logiikka.elements.Pelaaja;
import userInterface.KasinoUI;
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
public class KasinoTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private KasinoUI kasino;

    @Before
    public void setUp() {
        this.kasino = new KasinoUI(new Scanner(System.in));
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void pelaajallaTonniAlussa() {
        Pelaaja pelaaja = kasino.getPelaaja();
        assertEquals(1000, pelaaja.getBalance());
    }

    @Test
    public void kasinonDepositToimii() throws InterruptedException {
        kasino.deposit(100);
        assertEquals(1100, kasino.getPelaaja().getBalance());
    }

    @Test
    public void soutBalanceToimii() {
        kasino.balance();
        assertEquals("BALANCE: 1000\n", outContent.toString());
    }
    
    @Test
    public void soutExitToimii() {
        kasino.exit();
        assertEquals("Thank you for playing!\n", outContent.toString());
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
}
