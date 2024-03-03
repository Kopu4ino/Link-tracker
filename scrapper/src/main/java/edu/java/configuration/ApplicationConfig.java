package edu.java.configuration;

import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app", ignoreUnknownFields = false)
public record ApplicationConfig(
    @NotNull String gitHubBaseUrl,
    @NotNull String stackoverflowBaseUrl,
    @NotNull String botBaseUrl,

    @Bean
    @NotNull Scheduler scheduler
) {
    public record Scheduler(boolean enable, @Bean @NotNull Duration interval, @NotNull Duration forceCheckDelay) {
    }
}
