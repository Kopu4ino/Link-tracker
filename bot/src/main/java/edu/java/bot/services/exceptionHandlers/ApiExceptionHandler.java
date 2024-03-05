package edu.java.bot.services.exceptionHandlers;

import edu.java.bot.services.exceptions.UpdateAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shared.dto.response.ApiErrorResponse;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ApiErrorResponse handleException(UpdateAlreadyExistsException ex) {
        return new ApiErrorResponse(
            ex.getMessage(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ex.getClass().getSimpleName(),
            ex.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ApiErrorResponse handleException(MethodArgumentNotValidException ex) {
        return new ApiErrorResponse(
            "The passed argument is not valid",
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ex.getClass().getName(),
            ex.getMessage()
        );
    }
}
