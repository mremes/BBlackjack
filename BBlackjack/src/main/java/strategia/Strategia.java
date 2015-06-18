package strategia;

import java.util.ArrayList;
import logiikka.cards.Kortti;
import logiikka.elements.Kasi;

/**
 * Luokka sisältää koko perusstrategian ja sen optimaaliset siirrot kaikille
 * käsille. Käsi on kova, jos sillä on yksiselitteinen arvo (esim. KK = 20, 38 =
 * 11, jne.) Käsi on pehmeä, jos kädessä on ässä ja muiden korttien pistearvo on
 * kymmenen tai alle (poikkeuksena jos kädessä on ässä sekä yksi pistarvoltaan
 * 10 oleva kortti, jolloin käsi on Blackjack) Käsi on pari, jos kädessä on
 * kaksi saman numeroarvon omaavaa korttia. Lisää blackjack-strategiasta: ks.
 * https://en.wikipedia.org/wiki/Blackjack
 *
 * @author mrremes
 */
public final class Strategia {

    private static Toiminta[][] kovatPisteet;
    private static Toiminta[][] pehmeatPisteet;
    private static Toiminta[][] parit;

    public Strategia() {
        this.kovatPisteet = kovatPisteetGenerointi();
        this.pehmeatPisteet = pehmeatPisteetGenerointi();
        this.parit = paritGenerointi();
    }

    /**
     * Generoi taulukon, jossa kovien käsien strategia
     *
     * @return 10 x 9, taulukko, jossa optimaalinen strategia.
     */
    private Toiminta[][] kovatPisteetGenerointi() {
        Toiminta std = Toiminta.STAND;
        Toiminta hit = Toiminta.HIT;
        Toiminta dbl = Toiminta.DOUBLEH;
        Toiminta spt = Toiminta.SPLIT;
        Toiminta[][] hardTotals = new Toiminta[][]{
            {std, std, std, std, std, std, std, std, std, std},
            {std, std, std, std, std, hit, hit, hit, hit, hit},
            {std, std, std, std, std, hit, hit, hit, hit, hit},
            {std, std, std, std, std, hit, hit, hit, hit, hit},
            {hit, hit, std, std, std, hit, hit, hit, hit, hit},
            {dbl, dbl, dbl, dbl, dbl, dbl, dbl, dbl, dbl, hit},
            {dbl, dbl, dbl, dbl, dbl, dbl, dbl, dbl, hit, hit},
            {hit, dbl, dbl, dbl, dbl, hit, hit, hit, hit, hit},
            {hit, hit, hit, hit, hit, hit, hit, hit, hit, hit}
        };
        return hardTotals;
    }

    /**
     * Generoi taulukon, jossa pehmeiden käsien strategia
     *
     * @return 10 x 5 -taulukko, jossa optimaalinen strategia.
     */
    private Toiminta[][] pehmeatPisteetGenerointi() {
        Toiminta std = Toiminta.STAND;
        Toiminta hit = Toiminta.HIT;
        Toiminta dbh = Toiminta.DOUBLEH;
        Toiminta dbs = Toiminta.DOUBLES;
        Toiminta spt = Toiminta.SPLIT;
        Toiminta[][] softTotals = new Toiminta[][]{
            {std, std, std, std, std, std, std, std, std, std},
            {std, dbs, dbs, dbs, dbs, std, std, hit, hit, hit},
            {hit, dbh, dbh, dbh, dbh, hit, hit, hit, hit, hit},
            {hit, hit, dbh, dbh, dbh, hit, hit, hit, hit, hit},
            {hit, hit, hit, dbh, dbh, hit, hit, hit, hit, hit}

        };
        return softTotals;
    }

    /**
     * Generoi taulukon, jossa korttiparien optimaalinen strategia
     *
     * @return 10 x 9 -taulukko, jossa optimaalinen strategia.
     */
    private Toiminta[][] paritGenerointi() {
        Toiminta std = Toiminta.STAND;
        Toiminta hit = Toiminta.HIT;
        Toiminta dbl = Toiminta.DOUBLEH;
        Toiminta spt = Toiminta.SPLIT;
        Toiminta[][] pairs = new Toiminta[][]{
            {spt, spt, spt, spt, spt, spt, spt, spt, spt, spt},
            {std, std, std, std, std, std, std, std, std, std},
            {spt, spt, spt, spt, spt, std, spt, spt, std, std},
            {spt, spt, spt, spt, spt, spt, spt, spt, spt, spt},
            {spt, spt, spt, spt, spt, spt, hit, hit, hit, hit},
            {spt, spt, spt, spt, spt, hit, hit, hit, hit, hit},
            {dbl, dbl, dbl, dbl, dbl, dbl, dbl, dbl, hit, hit},
            {hit, hit, hit, spt, spt, hit, hit, hit, hit, hit},
            {spt, spt, spt, spt, spt, spt, hit, hit, hit, hit}
        };
        return pairs;
    }

    /**
     * Palauttaa optimaalisen siirron (Toiminta-olio)
     *
     * @param pelaaja pelaajan Käsi-olio
     * @param jakaja jakajan Käsi-olio
     * @return oikea Toiminta-olio
     */
    public static Toiminta getToiminta(Kasi pelaaja, Kasi jakaja) {
        if (pelaaja.isSplittable()) {
            if (pelaaja.isSplitted()) {
                return kovatPisteet[y(pelaaja)][x(jakaja)];
            }
            return parit[y(pelaaja)][x(jakaja)];
        } else if (pelaaja.getArvoS().contains("/")) {
            return pehmeatPisteet[y(pelaaja)][x(jakaja)];
        } else if (!pelaaja.getArvoS().contains("/")) {
            return kovatPisteet[y(pelaaja)][x(jakaja)];
        }
        return Toiminta.STAND;
    }

    /**
     * Palauttaa y-akselin indeksin (riippuu pelaajan kädestä)
     *
     * @param pelaaja pelaajan Käsi-olio
     * @return int indeksi taulukossa
     */
    public static int y(Kasi pelaaja) {
        if (pelaaja.getArvoS().length() > 2) {
            int arvo;
            if (pelaaja.nakyvaAssa()) {
                arvo = pelaaja.getArvo() - 10;
            } else {
                arvo = pelaaja.getArvo() - 10;
            }
            if (arvo == 2 || arvo == 3) {
                return 4;
            } else if (arvo == 4 || arvo == 5) {
                return 3;
            } else if (arvo == 6) {
                return 2;
            } else if (arvo == 7) {
                return 1;
            } else if (arvo == 8 || arvo == 9) {
                return 0;
            }
        } else if (pelaaja.isSplittable()) {
            Kortti k = pelaaja.getKortti(0);
            int n = k.getNumeroarvo();
            if (n == 2 || n == 3) {
                return 8;
            } else if (n == 4) {
                return 7;
            } else if (n == 5) {
                return 6;
            } else if (n == 6) {
                return 5;
            } else if (n == 7) {
                return 4;
            } else if (n == 8) {
                return 3;
            } else if (n == 9) {
                return 2;
            } else if (n == 10) {
                return 1;
            } else if (n == 1) {
                return 0;
            }
        } else if (!pelaaja.getArvoS().contains("/")) {
            int arvo = pelaaja.getArvo();
            if (arvo > 4 && arvo < 9) {
                return 8;
            } else if (arvo == 9) {
                return 7;
            } else if (arvo == 10) {
                return 6;
            } else if (arvo == 11) {
                return 5;
            } else if (arvo == 12) {
                return 4;
            } else if (arvo == 13 || arvo == 14) {
                return 3;
            } else if (arvo == 15) {
                return 2;
            } else if (arvo == 16) {
                return 1;
            } else if (arvo < 21 && arvo > 16) {
                return 0;
            }
        }
        return 0;
    }

    /**
     * Palauttaa x-akselin indeksin (riippuen jakajan käden näkyvästä kortista)
     *
     * @param jakaja jakajan Käsi-olio
     * @return int indeksi taulukossa
     */
    private static int x(Kasi jakaja) {
        if (jakaja.nakyvaAssa()) {
            return 9;
        } else {
            return jakaja.getKortti(1).getNumeroarvo() - 2;
        }
    }
}
