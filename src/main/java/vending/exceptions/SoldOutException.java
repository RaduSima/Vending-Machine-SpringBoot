package vending.exceptions;

public class SoldOutException extends Exception {
    public SoldOutException(String message) {
        super(message);
    }
}
