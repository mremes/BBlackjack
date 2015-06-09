package logiikka.elements;

import java.util.ArrayList;

public class Pelaaja {

    private int balance;
    /**
     * Pelaaja on olio, joka omistaa, panostaa ja tallettaa rahaa. Oliolla on
     * balance, jota voi muokata lisäämällä rahaa ja veloittamalla tililtä
     * rahaa
     *
     * @param balance
     */
    public Pelaaja(int balance) {
        this.balance = balance;
    }

    // RAHALIIKENNEMETODIT
    /**
     * Palauttaa pelaajan tilin arvon
     *
     * @return balance - tilin arvo kokonaislukuna.
     */
    public int getBalance() {
        return balance;
    }
    /**
     * Lisää pelaajan tilille parametrissa annetun summan rahaa.
     *
     * @param summa veloitettava
     */
    public void lisaaRahaa(int summa) {
        if (summa > 0) {
            this.balance += summa;
        }
    }
    /**
     * Veloittaa pelaajaa parametrina annetun summan verran
     * @param summa veloitettava
     */
    public void veloita(int summa) {
        if (summa > 0) {
            this.balance -= summa;
        }
    }

}
