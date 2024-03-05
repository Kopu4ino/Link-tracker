package edu.java.bot.services.exceptions;

public class UpdateAlreadyExistsException extends RuntimeException {
    public UpdateAlreadyExistsException(String message) {
        super(message);
    }

}
