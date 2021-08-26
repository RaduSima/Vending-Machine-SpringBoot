package vending.exceptions;

public class ItemInexistentException extends RuntimeException {
    public ItemInexistentException(String message) {
        super(message);
    }
}
