package edu.java.api.exceptions.handler;

import edu.java.api.dto.response.ApiErrorResponse;
import edu.java.api.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ApiErrorResponse handleException(BadRequestException ex) {
        return new ApiErrorResponse(
            ex.getDescription(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            ex.getClass().getName(),
            ex.getMessage()
        );
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public String handleException(MethodArgumentNotValidException ex) {
//        return "Ошибка";
//    }
}
