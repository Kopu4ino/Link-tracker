package edu.java.bot.api.dto.response;

import jakarta.validation.constraints.NotBlank;

public record LinkUpdateResponse(
    @NotBlank
    String message
) {
}
