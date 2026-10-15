package me.shiva.bank.systems;

import java.time.LocalDateTime;

public record Transaction(Type type, double amount, double balanceAfter, LocalDateTime timestamp) {
    public enum Type {
        DEPOSIT,
        WITHDRAWAL
    }
}
