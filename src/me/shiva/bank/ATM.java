package me.shiva.bank;

import me.shiva.bank.systems.Account;
import me.shiva.bank.systems.Transaction;

import java.io.PrintStream;
import java.util.Scanner;

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
        running = true;
        output.println("Welcome to the ATM.");

        while (running && input.hasNextLine()) {
            if (authenticatedAccount == null) {
                if (!login()) {
                    break;
                }
                continue;
            }

            displayMenu();
            if (!input.hasNextLine()) {
                break;
            }
            String choice = input.nextLine().trim();
            if (!choice.isEmpty()) {
                try {
                    handleMenuChoice(Integer.parseInt(choice));
                } catch (NumberFormatException exception) {
                    output.println("Please enter a valid menu option.");
                }
            }
        }

        output.println("Thank you for using the ATM.");
    }

    public boolean authenticate(String accountNumber, String pin) {
        Account account = bank.findAccount(accountNumber);
        if (account == null || account.isLocked()) {
            return false;
        }
        if (!account.validatePin(pin)) {
            account.registerFailedPinAttempt(maxPinAttempts);
            return false;
        }

        account.clearFailedPinAttempts();
        authenticatedAccount = account;
        return true;
    }

    public void logout() {
        authenticatedAccount = null;
    }

    public void displayMenu() {
        output.println();
        output.println("1. Deposit");
        output.println("2. Withdraw");
        output.println("3. Check balance");
        output.println("4. Transaction history");
        output.println("5. Logout");
        output.print("Choose an option: ");
    }

    public void handleMenuChoice(int choice) {
        if (authenticatedAccount == null) {
            output.println("Please authenticate first.");
            return;
        }

        switch (choice) {
            case 1 -> deposit();
            case 2 -> withdraw();
            case 3 -> showBalance();
            case 4 -> showTransactionHistory();
            case 5 -> logout();
            default -> output.println("That option is not available.");
        }
    }

    public void deposit() {
        try {
            double amount = readAmount();
            authenticatedAccount.deposit(amount);
            output.printf("Deposit successful. New balance: %.2f%n", authenticatedAccount.getBalance());
        } catch (IllegalArgumentException exception) {
            output.println(exception.getMessage());
        }
    }

    public void withdraw() {
        try {
            double amount = readAmount();
            authenticatedAccount.withdraw(amount);
            output.printf("Withdrawal successful. New balance: %.2f%n", authenticatedAccount.getBalance());
        } catch (IllegalArgumentException | InsufficientFundsException exception) {
            output.println(exception.getMessage());
        }
    }

    public void showBalance() {
        output.printf("Current balance: %.2f%n", authenticatedAccount.getBalance());
    }

    public void showTransactionHistory() {
        if (authenticatedAccount.getTransactionHistory().isEmpty()) {
            output.println("No transactions found.");
            return;
        }
        for (Transaction transaction : authenticatedAccount.getTransactionHistory()) {
            output.printf(
                    "%s: %.2f, balance: %.2f, time: %s%n",
                    transaction.type(),
                    transaction.amount(),
                    transaction.balanceAfter(),
                    transaction.timestamp()
            );
        }
    }

    private String readAccountNumber() {
        output.print("Account number: ");
        return input.nextLine().trim();
    }

    private String readPin() {
        output.print("PIN: ");
        return input.nextLine().trim();
    }

    private double readAmount() {
        output.print("Amount: ");
        try {
            double amount = Double.parseDouble(input.nextLine().trim());
            if (amount <= 0 || Double.isNaN(amount) || Double.isInfinite(amount)) {
                throw new IllegalArgumentException("Amount must be greater than zero.");
            }
            return amount;
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Please enter a valid amount.");
        }
    }

    private boolean login() {
        String accountNumber = readAccountNumber();
        String pin = readPin();

        if (authenticate(accountNumber, pin)) {
            output.println("Login successful.");
            return true;
        }

        Account account = bank.findAccount(accountNumber);
        if (account != null && account.isLocked()) {
            output.println("This account is locked.");
        } else {
            output.println("Invalid account number or PIN.");
        }
        return true;
    }
}
