package me.shiva.bank;

import me.shiva.bank.systems.Account;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Console adapter that coordinates authentication, menus, and account operations.
 */
public final class ATM {
    private static final int DEFAULT_MAX_PIN_ATTEMPTS = 3;

    private final Bank bank;
    private final Scanner input;
    private final PrintStream output;
    private final int maxPinAttempts;
    private Account authenticatedAccount;
    private boolean running;

    public ATM(Bank bank, Scanner input, PrintStream output) {
        this(bank, input, output, DEFAULT_MAX_PIN_ATTEMPTS);
    }

    public ATM(Bank bank, Scanner input, PrintStream output, int maxPinAttempts) {
        this.bank = bank;
        this.input = input;
        this.output = output;
        this.maxPinAttempts = maxPinAttempts;
    }

    public void run() {
        // TODO: Implement the top-level CLI loop and cleanly close the session.
    }

    public boolean authenticate(String accountNumber, String pin) {
        // TODO: Find the account, validate the PIN, apply lockout rules, and store the session account.
        return false;
    }

    public void logout() {
        // TODO: Clear the authenticated account and end the current session.
    }

    public void displayMenu() {
        // TODO: Print the authenticated user's available menu options.
    }

    public void handleMenuChoice(int choice) {
        // TODO: Dispatch a menu choice to the corresponding operation.
    }

    public void deposit() {
        // TODO: Read an amount and deposit it into the authenticated account.
    }

    public void withdraw() {
        // TODO: Read an amount and withdraw it from the authenticated account.
    }

    public void showBalance() {
        // TODO: Print the authenticated account balance.
    }

    public void showTransactionHistory() {
        // TODO: Print the authenticated account transaction history.
    }

    private String readAccountNumber() {
        // TODO: Read and validate an account number from input.
        return null;
    }

    private String readPin() {
        // TODO: Read and validate a PIN from input.
        return null;
    }

    private double readAmount() {
        // TODO: Read and validate a positive monetary amount from input.
        return 0.0;
    }
}
