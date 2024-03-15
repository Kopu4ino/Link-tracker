package edu.java.updateClients.updateDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

public record GitHubEventResponse(
    @JsonProperty("type")
    String type,

    @JsonProperty("actor")
    Actor actor,

    @JsonProperty("repo")
    Repo repo,

    @JsonProperty("created_at")
    OffsetDateTime createdAt) {
    public record Actor(@JsonProperty("login") String login) {
    }

    public record Repo(
        @JsonProperty("name") String name,
        @JsonProperty("url") String url
    ) {
    }
}

