/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.cards;

import logiikka.cards.Arvo;
import logiikka.cards.Korttipakka;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class KorttipakkaTest {

    private Korttipakka pakka;
    
    @Before
    public void setUp() {
        pakka = new Korttipakka();
    }
    
    @Test
    public void oikeaMaaraKortteja() {
        assertEquals(52, pakka.getKortit().length);
    }

}
