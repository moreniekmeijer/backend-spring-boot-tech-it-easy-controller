package nl.moreniekmeijer.backendspringboottechiteasycontroller.exceptions;

public class NameTooLongException extends RuntimeException{
    public NameTooLongException(String message) {
        super(message);
    }

    public NameTooLongException(int amount) {
        super("Name can not contain more than " + amount + " characters");
    }
}
