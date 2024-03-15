package edu.java.configuration;

import edu.java.updateClients.GitHubClient;
import edu.java.updateClients.StackOverflowClient;
import edu.java.updateClients.impl.DefaultGitHubClient;
import edu.java.updateClients.impl.DefaultStackOverflowClient;
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
