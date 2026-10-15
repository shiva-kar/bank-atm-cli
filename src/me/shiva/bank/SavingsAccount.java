package me.shiva.bank;

import me.shiva.bank.systems.Account;
import me.shiva.bank.systems.Transaction;

import java.time.LocalDateTime;

public final class SavingsAccount extends Account {
    private final double minimumBalance;

    public SavingsAccount(String accountNumber, String pin, double openingBalance, double minimumBalance) {
        super(accountNumber, pin, openingBalance);
        if (minimumBalance < 0 || minimumBalance > openingBalance) {
            throw new IllegalArgumentException("Minimum balance is invalid.");
        }
        this.minimumBalance = minimumBalance;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    @Override
    public double withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (getBalance() - amount < minimumBalance) {
            throw new InsufficientFundsException(
                    "Withdrawal would reduce the balance below the minimum balance."
            );
        }

        setBalance(getBalance() - amount);
        recordTransaction(new Transaction(
                Transaction.Type.WITHDRAWAL,
                amount,
                getBalance(),
                LocalDateTime.now()
        ));
        return amount;
    }
}
