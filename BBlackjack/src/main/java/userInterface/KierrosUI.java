package userInterface;

import logiikka.utilities.KierrosUtil;
import logiikka.utilities.Jakaja;
import logiikka.utilities.TulosPrint;
import logiikka.elements.Pelaaja;
import logiikka.elements.Kasi;
import logiikka.Kierros;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class KierrosUI {

    private Kierros kierros;
    private Scanner lukija;

    public KierrosUI(Pelaaja pelaaja, Scanner lukija) {
        this.kierros = new Kierros(pelaaja);
        this.lukija = lukija;
    }
    
    public void actions(Kasi k) throws InterruptedException {
        if (k.isBlackjack()) {
            k.setValmis();
        } else {
            komento(k);
        }
        
    }
    
    public void pelaaKierros() throws InterruptedException {
        HashMap<Kasi, Integer> pelaajanKadet = kierros.getPelaajanKadet();
        panostus();
        kierros.jaaKadet();
        dealer();
        while (!KierrosUtil.pelaajaValmis(pelaajanKadet)) {
            pelaaja();
            for (Kasi k : pelaajanKadet.keySet()) {
                while (!k.isValmis()) {
                    if (KierrosUtil.voiVakuuttaa(kierros.getJakajanKasi(), k, pelaajanKadet)) {
                        insurance(k);
                    }
                    actions(k);
                }
            }
        }
        tulos();
    }

    public void pelaaja() {
        System.out.println("");
        for (Kasi k : kierros.getPelaajanKadet().keySet()) {
            System.out.println("You: " + k);
        }
    }

    public void dealer() {
        System.out.print("Dealer: " + kierros.getJakajanKasi());
    }

    public void panostus() {
        System.out.println("\nYour stack: " + kierros.getPelaaja().getBalance());
        System.out.print("Place your bet: ");
        kierros.setPanos(Integer.parseInt(lukija.nextLine()));
    }

    public void insurance(Kasi k) throws InterruptedException {
        System.out.print("INSURANCE? (Y/N) ");
        String syotto = lukija.nextLine();
        if (syotto.equals("Y")) {
            kierros.setInsurance(k);
            System.out.print("\nChecking.");
            Thread.sleep(750);
            System.out.print(".");
            Thread.sleep(750);
            System.out.println(".");
            if (!kierros.getJakajanKasi().isBlackjack()) {
                System.out.println("Insurance lost.");
            }
        }
    }

    public void komento(Kasi k) {
        System.out.println(k.doables(kierros.getPelaajanKadet().size()));
        String komento = lukija.nextLine();
        if (komento.equals("HIT")) {
            kierros.hit(k);
            if (!k.isBust() && !k.isSplitted()) {
                System.out.println("\nYou: " + k);
            }
        } else if (komento.equals("STAND")) {
            kierros.stand(k);
        } else if (komento.equals("DOUBLE")) {
            kierros.doubl(k);
            System.out.print("\nYou: " + k);
        } else if (komento.equals("SPLIT")) {
            kierros.split(k);
        }

        if (k.getArvo() >= 21) {
            if (k.isBust() && kierros.getPelaajanKadet().size() > 1) {
                System.out.println("\nYou: " + k);
            }
            k.setValmis();
        }
    }

    public void tulos() throws InterruptedException {
        Kasi jakaja = kierros.getJakajanKasi();
        Set<Kasi> pelaajanKadet = kierros.getPelaajanKadet().keySet();
        Pelaaja pelaaja = kierros.getPelaaja();
        jakajanKasi();
        for (Kasi k : pelaajanKadet) {
            int panos = kierros.getPanos();
            if (k.isDoubled()) {
                panos *= 2;
            }
            TulosPrint tulos = new TulosPrint(pelaaja, k, jakaja, panos);
            if (!k.isDoubled() && !jakaja.isBlackjack()) {
                System.out.println("You: " + k);
                tulos.tulos(pelaajanKadet.size());
            } else if (!jakaja.isBust() && !k.isSplitted() && !k.isBlackjack()) {
                if (!k.isDoubled()) {
                    System.out.println("You: " + k);
                }
                tulos.tulos(pelaajanKadet.size());
            } else {
                tulos.tulos(pelaajanKadet.size());
            }
        }

        pelaajanKadet.clear();
    }
    
    public void jakajanKasi() throws InterruptedException {
        HashMap<Kasi, Integer> pelaajanKadet = kierros.getPelaajanKadet();
        Kasi jakajanKasi = kierros.getJakajanKasi();
        jakajanKasi.setOpen();
        System.out.println("");
        if (KierrosUtil.dealerOttaa(pelaajanKadet)) {
            System.out.print("Dealer: " + jakajanKasi.kortit());
            while (jakajanKasi.getArvo() < 17 && !KierrosUtil.pelaajaAllBj(pelaajanKadet)) {
                jakajanKasi.addKortti(Jakaja.annaKortti());
                Thread.sleep(1000);
                System.out.print(Jakaja.edellinenKortti() + " ");
            }
            System.out.println(jakajanKasi.getArvoS());
        }
        for (Kasi k : pelaajanKadet.keySet()) {
            if (k.isSplitted()) {
                System.out.println("");
                break;
            }
        }
    }
}
