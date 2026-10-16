package me.shiva.bank.systems;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Account {
    private final String accountNumber;
    private final List<Transaction> transactionHistory;
    private String pin;
    private double balance;
    private int failedPinAttempts;
    private boolean locked;

    protected Account(String accountNumber, String pin, double openingBalance) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Account number is required.");
        }
        validatePinFormat(pin);
        if (openingBalance < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative.");
        }
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = openingBalance;
        this.transactionHistory = new ArrayList<>();
        this.failedPinAttempts = 0;
        this.locked = false;
    }

    public final String getAccountNumber() {
        return accountNumber;
    }

    public final double getBalance() {
        return balance;
    }

    protected final void setBalance(double newBalance) {
        if (newBalance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        balance = newBalance;
    }

    public final List<Transaction> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }

    public final void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Deposit amount must be greater than zero."
            );
        }

        setBalance(getBalance() + amount);

        transactionHistory.add(
                new Transaction(
                        Transaction.Type.DEPOSIT,
                        amount,
                        getBalance(),
                        LocalDateTime.now()
                )
        );
    }

    public final boolean validatePin(String enteredPin) {
        return !locked && pin.equals(enteredPin);
    }

    public abstract double withdraw(double amount);

    public final boolean isLocked() {
        return locked;
    }

    public final int getFailedPinAttempts() {
        return failedPinAttempts;
    }

    public final void setPin(String newPin) {
        validatePinFormat(newPin);
        pin = newPin;
    }

    protected final void recordTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public final void registerFailedPinAttempt(int maximumAttempts) {
        if (maximumAttempts <= 0) {
            throw new IllegalArgumentException("Maximum PIN attempts must be positive.");
        }
        failedPinAttempts++;
        if (failedPinAttempts >= maximumAttempts) {
            locked = true;
        }
    }

    public final void clearFailedPinAttempts() {
        failedPinAttempts = 0;
    }

    private static void validatePinFormat(String value) {
        if (value == null || !value.matches("\\d{4}")) {
            throw new IllegalArgumentException("PIN must contain exactly four digits.");
        }
    }
}
