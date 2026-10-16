package me.shiva.app;

import me.shiva.bank.ATM;
import me.shiva.bank.Bank;
import me.shiva.bank.SavingsAccount;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addAccount(new SavingsAccount("1001", "1234", 1000.00, 100.00));
        ATM atm = new ATM(bank, new Scanner(System.in), System.out);
        atm.run();
    }
}