package me.shiva.bank;

import me.shiva.bank.systems.Account;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Bank {
    private final Map<String, Account> accounts = new LinkedHashMap<>();

    public void addAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account is required.");
        }
        if (accounts.containsKey(account.getAccountNumber())) {
            throw new IllegalArgumentException("An account with that number already exists.");
        }
        accounts.put(account.getAccountNumber(), account);
    }

    public Account findAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public boolean containsAccount(String accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    public boolean removeAccount(String accountNumber) {
        return accounts.remove(accountNumber) != null;
    }

    public Collection<Account> getAccounts() {
        return java.util.Collections.unmodifiableCollection(accounts.values());
    }
}
