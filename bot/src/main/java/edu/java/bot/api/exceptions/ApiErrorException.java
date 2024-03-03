package edu.java.bot.api.exceptions;

import edu.java.bot.api.dto.response.ApiErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiErrorException extends RuntimeException {
    private final ApiErrorResponse apiErrorResponse;
}
