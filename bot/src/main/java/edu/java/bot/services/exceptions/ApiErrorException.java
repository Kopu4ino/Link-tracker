package edu.java.bot.services.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import shared.dto.response.ApiErrorResponse;

@AllArgsConstructor
@Getter
public class ApiErrorException extends RuntimeException {
    private final ApiErrorResponse apiErrorResponse;
}
