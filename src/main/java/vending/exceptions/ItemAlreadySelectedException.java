package vending.exceptions;

public class ItemAlreadySelectedException
        extends Exception {
    public ItemAlreadySelectedException(String message) {
        super(message);
    }
}
