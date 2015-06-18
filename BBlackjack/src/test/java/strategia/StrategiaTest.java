/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategia;

import logiikka.cards.Arvo;
import logiikka.cards.Kortti;
import logiikka.cards.Maa;
import logiikka.elements.Kasi;
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
public class StrategiaTest {
    
    private Strategia strategia;
    
    @Before
    public void setUp() {
        this.strategia = new Strategia();
    }
    
    @Test
    public void oikeaToimintaPehmealleKadelle() {
        Kasi pelaaja = new Kasi(new Kortti(Maa.HERTTA, Arvo.ASSA), new Kortti(Maa.PATA, Arvo.KUTONEN));
        Kasi jakaja = new Kasi (new Kortti(Maa.HERTTA, Arvo.JATKA), new Kortti(Maa.PATA, Arvo.JATKA));
        assertEquals(Toiminta.HIT, Strategia.getToiminta(pelaaja, jakaja));
        pelaaja.addKortti(new Kortti (Maa.HERTTA, Arvo.KAKKONEN));
        assertEquals(Toiminta.STAND, Strategia.getToiminta(pelaaja, jakaja));
        pelaaja.addKortti(new Kortti(Maa.HERTTA, Arvo.SEISKA));
        assertEquals(Toiminta.HIT, Strategia.getToiminta(pelaaja, jakaja));
    }
    
    @Test
    public void oikeaToimintaKovalleKadelle() {
        Kasi pelaaja = new Kasi(new Kortti(Maa.HERTTA, Arvo.KASI), new Kortti(Maa.PATA, Arvo.KUTONEN));
        Kasi jakaja = new Kasi (new Kortti(Maa.HERTTA, Arvo.JATKA), new Kortti(Maa.PATA, Arvo.JATKA));
        assertEquals(Toiminta.HIT, Strategia.getToiminta(pelaaja, jakaja));
        pelaaja.addKortti(new Kortti(Maa.RISTI, Arvo.VITONEN));
        assertEquals(Toiminta.STAND, Strategia.getToiminta(pelaaja, jakaja));
    }
    
    @Test
    public void oikeaToimintaParille() {
        Kasi pelaaja1 = new Kasi(new Kortti(Maa.HERTTA, Arvo.KUTONEN), new Kortti(Maa.PATA, Arvo.KUTONEN));
        Kasi pelaaja2 = new Kasi(new Kortti(Maa.HERTTA, Arvo.KYMPPI), new Kortti(Maa.PATA, Arvo.JATKA));
        Kasi jakaja = new Kasi (new Kortti(Maa.HERTTA, Arvo.JATKA), new Kortti(Maa.PATA, Arvo.KAKKONEN));
        assertEquals(Toiminta.SPLIT, Strategia.getToiminta(pelaaja1, jakaja));
        assertEquals(Toiminta.STAND, Strategia.getToiminta(pelaaja2, jakaja));
    }
    
    @Test
    public void oikeaToimintaKunArvo21() {
        Kasi pelaaja1 = new Kasi(new Kortti(Maa.HERTTA, Arvo.KYMPPI), new Kortti(Maa.PATA, Arvo.ASSA));
        Kasi pelaaja2 = new Kasi(new Kortti(Maa.HERTTA, Arvo.KYMPPI), new Kortti(Maa.PATA, Arvo.KUTONEN));
        Kasi jakaja = new Kasi (new Kortti(Maa.HERTTA, Arvo.JATKA), new Kortti(Maa.PATA, Arvo.KAKKONEN));
        pelaaja2.addKortti(new Kortti(Maa.HERTTA, Arvo.VITONEN));
        assertEquals(Toiminta.STAND, Strategia.getToiminta(pelaaja1, jakaja));
        assertEquals(Toiminta.STAND, Strategia.getToiminta(pelaaja2, jakaja));
    }
}
