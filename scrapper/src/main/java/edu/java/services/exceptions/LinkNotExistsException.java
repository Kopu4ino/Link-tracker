package edu.java.services.exceptions;

public class LinkNotExistsException extends RuntimeException {
    public LinkNotExistsException(String message) {
        super(message);
    }
}
