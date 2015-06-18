/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiikka.utilities;

import java.util.ArrayList;
import logiikka.Kierros;
import logiikka.cards.Arvo;
import logiikka.cards.Kortti;
import logiikka.cards.Maa;
import logiikka.elements.Kasi;
import logiikka.elements.Pelaaja;
import logiikka.utilities.KierrosUtil;
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
public class KierrosUtilTest {
    private Kierros kierros;
    private Pelaaja pelaaja;
    @Before
    public void setUp() {
        this.pelaaja = new Pelaaja(1000);
        this.kierros = new Kierros(pelaaja);
    }
    
    @Test
    public void pelaajaBustToimii() {
        ArrayList<Kasi> pelaajanKadet = new ArrayList();
        pelaajanKadet.add(new Kasi(new Kortti(Maa.HERTTA, Arvo.KYMPPI), new Kortti(Maa.PATA, Arvo.KYMPPI)));
        pelaajanKadet.add(new Kasi(new Kortti(Maa.HERTTA, Arvo.KYMPPI), new Kortti(Maa.PATA, Arvo.KYMPPI)));
        assertFalse(KierrosUtil.pelaajaBust(pelaajanKadet));
        pelaajanKadet.get(0).addKortti(new Kortti(Maa.HERTTA, Arvo.VITONEN));
        pelaajanKadet.get(1).addKortti(new Kortti(Maa.HERTTA, Arvo.KAKKONEN));
        assertTrue(KierrosUtil.pelaajaBust(pelaajanKadet));
    }
    
    @Test
    public void pelaajaValmisToimii1() {
        ArrayList<Kasi> pelaajanKadet = new ArrayList();
        pelaajanKadet.add(new Kasi(new Kortti(Maa.HERTTA, Arvo.KYMPPI), new Kortti(Maa.PATA, Arvo.KYMPPI)));
        pelaajanKadet.add(new Kasi(new Kortti(Maa.HERTTA, Arvo.KYMPPI), new Kortti(Maa.PATA, Arvo.KYMPPI)));
        assertFalse(KierrosUtil.pelaajaValmis(pelaajanKadet));
        pelaajanKadet.get(0).setValmis();
        pelaajanKadet.get(1).setValmis();
        assertTrue(KierrosUtil.pelaajaValmis(pelaajanKadet));
    }
    
    @Test
    public void pelaajaValmisToimii2() {
        ArrayList<Kasi> pelaajanKadet = new ArrayList();
        pelaajanKadet.add(new Kasi(new Kortti(Maa.HERTTA, Arvo.KYMPPI), new Kortti(Maa.PATA, Arvo.KYMPPI)));
        pelaajanKadet.add(new Kasi(new Kortti(Maa.HERTTA, Arvo.KYMPPI), new Kortti(Maa.PATA, Arvo.KYMPPI)));
        assertFalse(KierrosUtil.pelaajaValmis(pelaajanKadet));
        pelaajanKadet.get(0).addKortti(new Kortti(Maa.HERTTA, Arvo.KOLMONEN));
        pelaajanKadet.get(1).addKortti(new Kortti(Maa.HERTTA, Arvo.KAKKONEN));
        assertTrue(KierrosUtil.pelaajaValmis(pelaajanKadet));
    }
    
    @Test
    public void pelaajaAllBjToimii() {
        ArrayList<Kasi> pelaajanKadet = new ArrayList();
        pelaajanKadet.add(new Kasi(new Kortti(Maa.HERTTA, Arvo.KYMPPI), new Kortti(Maa.PATA, Arvo.ASSA)));
        pelaajanKadet.add(new Kasi(new Kortti(Maa.HERTTA, Arvo.KYMPPI), new Kortti(Maa.PATA, Arvo.ASSA)));
        assertTrue(KierrosUtil.pelaajaAllBj(pelaajanKadet));
    }
    
    @Test
    public void montaKattaToimii() {
        ArrayList<Kasi> pelaajanKadet = new ArrayList();
        pelaajanKadet.add(new Kasi(new Kortti(Maa.HERTTA, Arvo.KYMPPI), new Kortti(Maa.PATA, Arvo.ASSA)));
        assertFalse(KierrosUtil.montaKatta(pelaajanKadet));
        pelaajanKadet.add(new Kasi(new Kortti(Maa.HERTTA, Arvo.KYMPPI), new Kortti(Maa.PATA, Arvo.ASSA)));
        assertTrue(KierrosUtil.montaKatta(pelaajanKadet));
    }
    
    @Test
    public void dealerOttaaToimii1() {
        ArrayList<Kasi> pelaajanKadet = new ArrayList();
        pelaajanKadet.add(new Kasi(new Kortti(Maa.HERTTA, Arvo.KYMPPI), new Kortti(Maa.PATA, Arvo.ASSA)));
        pelaajanKadet.add(new Kasi(new Kortti(Maa.HERTTA, Arvo.KYMPPI), new Kortti(Maa.PATA, Arvo.ASSA)));
        assertFalse(KierrosUtil.dealerOttaa(pelaajanKadet));
    }
    @Test
    public void dealerOttaaToimii2() {
        ArrayList<Kasi> pelaajanKadet = new ArrayList();
        pelaajanKadet.add(new Kasi(new Kortti(Maa.HERTTA, Arvo.KYMPPI), new Kortti(Maa.PATA, Arvo.KASI)));
        pelaajanKadet.add(new Kasi(new Kortti(Maa.HERTTA, Arvo.KYMPPI), new Kortti(Maa.PATA, Arvo.ASSA)));
        assertTrue(KierrosUtil.dealerOttaa(pelaajanKadet));
    }
    
    
}
