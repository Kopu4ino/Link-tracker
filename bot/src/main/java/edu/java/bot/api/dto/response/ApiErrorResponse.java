package edu.java.bot.api.dto.response;

public record ApiErrorResponse(
    String description,
    String code,
    String exceptionName,
    String exceptionMessage
) {
}
