package edu.java.clients;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import edu.java.clients.impl.DefaultGitHubClient;
import edu.java.dto.GitHubEventResponse;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class DefaultGitHubClientTest {
    private static WireMockServer wireMockServer;

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        wireMockServer.start();
        wireMockServer.stubFor(WireMock.get(urlPathEqualTo("/repos/owner/repoName/events"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody(
                    """
                        [
                            {
                                "type": "PushEvent",
                                "actor": {
                                    "login": "owner"
                                },
                                "repo": {
                                    "name": "owner/repoName",
                                    "url": "https://api.github.com/repos/owner/repoName"
                                },
                                "created_at": "2024-02-24T13:28:49Z"
                            }
                        ]
                        """)
            )
        );

    }

    @AfterEach
    void tearDown() {
        wireMockServer.stop();
    }

    @Test
    void fetchRepositoryEventsShouldReturnExpectedEvent() {
        String baseUrl = String.format("http://localhost:%s", wireMockServer.port());
        GitHubClient client = new DefaultGitHubClient(WebClient.builder(), baseUrl);

        List<GitHubEventResponse> gitHubEventResponses = client.fetchRepositoryEvents("owner", "repoName");

        OffsetDateTime expectedDate = OffsetDateTime.parse("2024-02-24T13:28:49Z", DateTimeFormatter.ISO_DATE_TIME);
        GitHubEventResponse expectedResponse = new GitHubEventResponse(
            "PushEvent",
            new GitHubEventResponse.Actor("owner"),
            new GitHubEventResponse.Repo("owner/repoName", "https://api.github.com/repos/owner/repoName"),
            expectedDate
        );

        assertThat(gitHubEventResponses).hasSize(1);
        assertThat(gitHubEventResponses.getFirst()).usingRecursiveComparison().isEqualTo(expectedResponse);
    }
}
