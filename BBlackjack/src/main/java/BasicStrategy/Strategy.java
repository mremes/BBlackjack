package BasicStrategy;

import java.util.ArrayList;
import logiikka.cards.Kortti;
import logiikka.elements.Kasi;

public final class Strategy {

    private static Action[][] hardTotals;
    private static Action[][] softTotals;
    private static Action[][] pairs;

    public Strategy() {
        this.hardTotals = hardTotalsGeneration();
        this.softTotals = softTotalsGeneration();
        this.pairs = pairsGeneration();
    }

    private Action[][] hardTotalsGeneration() {
        Action std = Action.STAND;
        Action hit = Action.HIT;
        Action dbl = Action.DOUBLEH;
        Action spt = Action.SPLIT;
        Action[][] hardTotals = new Action[][]{
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

    private Action[][] softTotalsGeneration() {
        Action std = Action.STAND;
        Action hit = Action.HIT;
        Action dbh = Action.DOUBLEH;
        Action dbs = Action.DOUBLES;
        Action spt = Action.SPLIT;
        Action[][] softTotals = new Action[][]{
            {std, std, std, std, std, std, std, std, std, std},
            {std, dbs, dbs, dbs, dbs, std, std, hit, hit, hit},
            {hit, dbh, dbh, dbh, dbh, hit, hit, hit, hit, hit},
            {hit, hit, dbh, dbh, dbh, hit, hit, hit, hit, hit},
            {hit, hit, hit, dbh, dbh, hit, hit, hit, hit, hit}

        };
        return softTotals;
    }

    private Action[][] pairsGeneration() {
        Action std = Action.STAND;
        Action hit = Action.HIT;
        Action dbl = Action.DOUBLEH;
        Action spt = Action.SPLIT;
        Action[][] pairs = new Action[][]{
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

    public static Action get(Kasi pelaaja, Kasi jakaja) {
        if (pelaaja.isSplittable()) {
            return pairs[y(pelaaja)][x(jakaja)];
        } else if (pelaaja.getArvoS().contains("/")) {
            return softTotals[y(pelaaja)][x(jakaja)];
        } else if (!pelaaja.getArvoS().contains("/")) {
            return hardTotals[y(pelaaja)][x(jakaja)];
        }
        return Action.STAND;
    }

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

    public static int x(Kasi jakaja) {
        if (jakaja.nakyvaAssa()) {
            return 9;
        } else {
            return jakaja.getKortti(1).getNumeroarvo() - 2;
        }
    }
}
