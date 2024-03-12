package edu.java.clients.impl;

import edu.java.clients.GitHubClient;
import edu.java.configuration.ApplicationConfig;
import edu.java.dto.GitHubEventResponse;
import java.util.List;
import org.springframework.web.reactive.function.client.WebClient;

public class DefaultGitHubClient implements GitHubClient {
    private final WebClient webClient;

    public DefaultGitHubClient(WebClient.Builder webClientBuilder, ApplicationConfig config) {
        this.webClient = webClientBuilder.baseUrl(config.gitHubBaseUrl()).build();
    }

    public DefaultGitHubClient(WebClient.Builder webClientBuilder, String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public List<GitHubEventResponse> fetchRepositoryEvents(String owner, String repoName) {
        return webClient.get()
            .uri("/repos/{owner}/{repoName}/events", owner, repoName)
            .retrieve()
            .bodyToFlux(GitHubEventResponse.class)
            .collectList()
            .block();
    }

}
