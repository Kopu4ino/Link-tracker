package edu.bot.links;

import java.net.URI;
import edu.java.bot.Links.GitHubLinkHandler;
import edu.java.bot.Links.StackOverFlowLinkHandler;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LinkHandlerTest {

    @Test
    public void testGitHubLinkHandlerWithGitHubUri() throws Exception {
        URI githubUri = new URI("https://github.com/some");
        GitHubLinkHandler handler = new GitHubLinkHandler();

        boolean result = handler.handle(githubUri);

        assertThat(result).isTrue();
    }

    @Test
    public void testGitHubLinkHandlerWithNonGitHubUri() throws Exception {
        URI stackOverflowUri = new URI("https://stackoverflow.com/questions");
        GitHubLinkHandler handler = new GitHubLinkHandler();

        boolean result = handler.handle(stackOverflowUri);

        assertThat(result).isFalse();
    }

    @Test
    public void testStackOverFlowLinkHandlerWithStackOverFlowUri() throws Exception {
        URI stackOverFlowUri = new URI("https://stackoverflow.com/questions");
        StackOverFlowLinkHandler handler = new StackOverFlowLinkHandler();

        boolean result = handler.handle(stackOverFlowUri);

        assertThat(result).isTrue();
    }

    @Test
    public void testStackOverFlowLinkHandlerWithNonStackOverFlowUri() throws Exception {
        URI githubUri = new URI("https://github.com/some");
        StackOverFlowLinkHandler handler = new StackOverFlowLinkHandler();

        boolean result = handler.handle(githubUri);

        assertThat(result).isFalse();
    }
}
