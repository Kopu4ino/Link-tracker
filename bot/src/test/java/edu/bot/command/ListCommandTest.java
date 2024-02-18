package edu.bot.command;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.Comands.ListCommand;
import edu.java.bot.Repository.UserRepository;
import edu.java.bot.ResponseMessage.StandardResponseMessage;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class ListCommandTest extends CommandTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ListCommand listCommand;

    @Test
    public void whenNoTrackedLinks_thenShouldReturnNoServicesMessage() {
        when(userRepository.getUserLinksById(userId)).thenReturn(Collections.emptyList());

        SendMessage result = listCommand.handle(update);

        assertThat(result.getParameters()
            .get("text")).isEqualTo(StandardResponseMessage.NO_SERVICES_BEING_TRACKED.getMessage());
    }

    @Test
    public void whenTrackedLinksExist_thenShouldReturnListOfLinks() {
        List<String> trackedLinks = Arrays.asList("https://example1.com", "https://example2.com");
        when(userRepository.getUserLinksById(userId)).thenReturn(trackedLinks);

        SendMessage result = listCommand.handle(update);

        StringBuilder expectedMessage = new StringBuilder();
        for (String link : trackedLinks) {
            expectedMessage.append(link).append("\n");
        }

        assertThat(result.getParameters().get("text")).isEqualTo(expectedMessage.toString());
    }
}
