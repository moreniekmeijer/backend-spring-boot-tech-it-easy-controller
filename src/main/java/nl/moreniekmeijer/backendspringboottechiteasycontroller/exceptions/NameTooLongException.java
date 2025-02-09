package nl.moreniekmeijer.backendspringboottechiteasycontroller.exceptions;

public class NameTooLongException extends RuntimeException{
    public NameTooLongException(String message) {
        super(message);
    }
}
