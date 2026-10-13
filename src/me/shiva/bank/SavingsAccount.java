package me.shiva.bank;

import me.shiva.bank.systems.Account;

public class SavingsAccount extends Account {
    // JUST BASIC NO AUTH
    @Override
    public double withdraw(double amount) {
        if (getBalance() < amount) return 0;
        setBalance(getBalance() - amount);
        return amount;
    }
    
}
