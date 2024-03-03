package edu.java.api.dto.response;

public record ApiErrorResponse(
    String description,
    String code,
    String exceptionName,
    String exceptionMessage
) {
}
