package vending.exceptions;

public class ItemAlreadySelectedException
        extends RuntimeException {
    public ItemAlreadySelectedException(String message) {
        super(message);
    }
}
