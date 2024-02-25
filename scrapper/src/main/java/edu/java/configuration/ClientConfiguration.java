package edu.java.configuration;

import edu.java.clients.GitHubClient;
import edu.java.clients.StackOverflowClient;
import edu.java.clients.impl.DefaultGitHubClient;
import edu.java.clients.impl.DefaultStackOverflowClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {
    private final ApplicationConfig config;

    @Autowired
    public ClientConfiguration(ApplicationConfig config) {
        this.config = config;
    }

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public GitHubClient gitHubClient(WebClient.Builder webClientBuilder) {
        return new DefaultGitHubClient(webClientBuilder, config);
    }

    @Bean
    public StackOverflowClient stackOverflowClient(@Autowired WebClient.Builder webClientBuilder) {
        return new DefaultStackOverflowClient(webClientBuilder, config);
    }
}
