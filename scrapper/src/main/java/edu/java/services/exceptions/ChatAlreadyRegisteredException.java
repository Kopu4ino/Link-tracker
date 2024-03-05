package edu.java.services.exceptions;

public class ChatAlreadyRegisteredException extends RuntimeException {
    public ChatAlreadyRegisteredException(String message) {
        super(message);
    }
}
