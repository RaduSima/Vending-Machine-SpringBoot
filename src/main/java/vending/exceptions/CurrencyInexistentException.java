package vending.exceptions;

public class CurrencyInexistentException extends RuntimeException {
    public CurrencyInexistentException(String message) {
        super(message);
    }
}
