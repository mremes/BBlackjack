package logiikka.elements;

import java.util.ArrayList;
/**
     * Pelaaja on olio, joka omistaa, panostaa ja tallettaa rahaa. Oliolla on
     * balance, jota voi muokata lisäämällä rahaa ja veloittamalla tililtä
     * rahaa
     *
     */
public class Pelaaja {

    private int balance;
    /**
     * Luo pelaajan ja antaa sille alkusaldoksi parametrina annetun määrän rahaa.
     * @param balance rahan maara alussa
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
