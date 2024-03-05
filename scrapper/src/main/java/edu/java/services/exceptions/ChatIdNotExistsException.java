package edu.java.services.exceptions;

public class ChatIdNotExistsException extends RuntimeException {
    public ChatIdNotExistsException(String message) {
        super(message);
    }
}
