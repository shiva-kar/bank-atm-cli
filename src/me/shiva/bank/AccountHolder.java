package me.shiva.bank;

import me.shiva.bank.systems.Account;

public final class AccountHolder {
    private final String holderId;
    private final String name;
    private final Account savingsAccount;
    private final Account currentAccount;

    public AccountHolder(String holderId, String name, Account savingsAccount, Account currentAccount) {
        this.holderId = holderId;
        this.name = name;
        this.savingsAccount = savingsAccount;
        this.currentAccount = currentAccount;
    }

    public String getHolderId() {
        return holderId;
    }

    public String getName() {
        return name;
    }

    public Account getSavingsAccount() {
        return savingsAccount;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }
}
