package edu.java.updateClients.impl;

import edu.java.configuration.ApplicationConfig;
import edu.java.updateClients.StackOverflowClient;
import edu.java.updateClients.updateDto.StackOverflowQuestionResponse;
import java.util.List;
import org.springframework.web.reactive.function.client.WebClient;

public class DefaultStackOverflowClient implements StackOverflowClient {
    ApplicationConfig config;
    private final WebClient webClient;

    public DefaultStackOverflowClient(WebClient.Builder webClientBuilder, ApplicationConfig config) {
        this.config = config;
        this.webClient = webClientBuilder.baseUrl(config.stackoverflowBaseUrl()).build();
    }

    public DefaultStackOverflowClient(WebClient.Builder webClientBuilder, String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    @Override
    public List<StackOverflowQuestionResponse> fetchQuestion(String questionId) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/questions/{questionId}")
                .queryParam("site", "stackoverflow")
                .build(questionId))
            .retrieve()
            .bodyToFlux(StackOverflowQuestionResponse.class)
            .collectList()
            .block();
    }
}
