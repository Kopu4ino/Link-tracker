package edu.java.clients;

import edu.java.dto.GitHubEventResponse;
import java.util.List;

public interface GitHubClient {
    List<GitHubEventResponse> fetchRepositoryEvents(String owner, String repoName);
}
