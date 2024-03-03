package edu.java.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkUpdateRequest {
    @NotNull
    private Long id;
    @NotBlank
    private String url;
    private String description;
    @NotEmpty
    private List<Long> tgChatIds;
}
