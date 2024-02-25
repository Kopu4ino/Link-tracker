package edu.java.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public record StackOverflowQuestionResponse(
    @JsonProperty("items")
    List<Item> items) {

    public record Item(
        @JsonProperty("owner")
        Owner owner,

        @JsonProperty("last_activity_date")
        long lastActivityDate
    ) {
        public OffsetDateTime getLastActivityDateAsOffsetDateTime() {
            return Instant.ofEpochSecond(lastActivityDate).atOffset(ZoneOffset.UTC);
        }

        public record Owner(
            @JsonProperty("display_name")
            String displayName
        ) {

        }
    }
}
