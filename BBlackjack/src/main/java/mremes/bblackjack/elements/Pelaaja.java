package mremes.bblackjack.elements;

import java.util.ArrayList;

public class Pelaaja {

    private int balance;

    public Pelaaja(int balance) {
        this.balance = balance;
    }

    // RAHALIIKENNEMETODIT
    public int getBalance() {
        return balance;
    }

    public void lisaaRahaa(int summa) {
        if (summa > 0) {
            this.balance += summa;
        }
    }

    public void veloita(int summa) {
        if (summa > 0) {
            this.balance -= summa;
        }
    }

}
