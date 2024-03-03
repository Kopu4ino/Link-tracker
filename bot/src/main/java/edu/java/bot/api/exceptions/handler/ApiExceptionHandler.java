package edu.java.bot.api.exceptions.handler;

import edu.java.bot.api.dto.response.ApiErrorResponse;
import edu.java.bot.api.exceptions.UpdateAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ApiErrorResponse handleException(UpdateAlreadyExistsException ex) {
        return new ApiErrorResponse(
            "Update уже существует",
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ex.getClass().getSimpleName(),
            ex.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public ApiErrorResponse handleException(MethodArgumentNotValidException ex) {
        return new ApiErrorResponse(
            "ex.getDescription()",
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ex.getClass().getName(),
            ex.getMessage()
        );
    }
}
