package edu.java.services.exceptionHandlers;

import edu.java.services.exceptions.ChatAlreadyRegisteredException;
import edu.java.services.exceptions.ChatIdNotExistsException;
import edu.java.services.exceptions.LinkAlreadyTrackException;
import edu.java.services.exceptions.LinkNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shared.dto.response.ApiErrorResponse;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleException(ChatAlreadyRegisteredException ex) {
        return new ApiErrorResponse(
            ex.getMessage(),
            ex.getStatusCode().getReasonPhrase(),
            ex.getClass().getSimpleName(),
            ex.getDescription()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleException(ChatIdNotExistsException ex) {
        return new ApiErrorResponse(
            ex.getMessage(),
            ex.getStatusCode().getReasonPhrase(),
            ex.getClass().getSimpleName(),
            ex.getDescription()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleException(LinkNotExistsException ex) {
        return new ApiErrorResponse(
            ex.getMessage(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            ex.getClass().getSimpleName(),
            ex.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleException(LinkAlreadyTrackException ex) {
        return new ApiErrorResponse(
            ex.getMessage(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ex.getClass().getSimpleName(),
            ex.getMessage()
        );
    }
}
