package vending.exceptions;

public class NoItemSelectedException extends RuntimeException {
    public NoItemSelectedException(String message) {
        super(message);
    }
}
