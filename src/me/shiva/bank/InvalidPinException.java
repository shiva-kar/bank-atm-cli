package me.shiva.bank;

public final class InvalidPinException extends RuntimeException {
    public InvalidPinException(String message) {
        super(message);
    }
}
