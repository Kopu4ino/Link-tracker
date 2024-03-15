package shared.dto.response;

public record ApiErrorResponse(
    String description,
    String code,
    String exceptionName,
    String exceptionMessage
) {
}
