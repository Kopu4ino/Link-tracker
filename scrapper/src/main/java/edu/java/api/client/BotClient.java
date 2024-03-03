package edu.java.api.client;

import edu.java.api.dto.request.LinkUpdateRequest;
import edu.java.api.dto.response.ApiErrorResponse;
import edu.java.api.exceptions.ApiErrorException;
import edu.java.configuration.ApplicationConfig;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class BotClient {

    private final WebClient webClient;

    @Autowired
    public BotClient(WebClient.Builder webClientBuilder, ApplicationConfig config) {
        this.webClient = webClientBuilder.baseUrl(config.botBaseUrl()).build();
    }

    public BotClient(WebClient.Builder webClientBuilder, String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public Optional<String> sendUpdate(LinkUpdateRequest update) {
        return webClient
            .post()
            .uri("/updates")
            .body(BodyInserters.fromValue(update))
            .retrieve()
            .onStatus(
                HttpStatus.BAD_REQUEST::equals,
                response -> response
                    .bodyToMono(ApiErrorResponse.class)
                    .flatMap(errorResponse -> Mono.error(new ApiErrorException(errorResponse)))
            )
            .bodyToMono(String.class)
            .blockOptional();
    }
}
