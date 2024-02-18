package edu.bot.command;

import com.pengrad.telegrambot.model.MessageEntity;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.Comands.UntrackCommand;
import edu.java.bot.Entity.EntityType;
import edu.java.bot.Repository.UserRepository;
import edu.java.bot.ResponseMessage.StandardResponseMessage;
import edu.java.bot.Utils.EntityUtils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

public class UntrackCommandTest extends CommandTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MessageEntity urlEntity;

    @InjectMocks
    private UntrackCommand untrackCommand;

    private final String urlText = "https://example.com";

    @Test
    public void whenUrlRemovedSuccessfully_thenShouldReturnSuccessMessage() {
        try (var mocked = mockStatic(EntityUtils.class)) {
            mocked.when(() -> EntityUtils.getEntityByName(update, EntityType.URL.getName())).thenReturn(urlEntity);
            mocked.when(() -> EntityUtils.getTextFromEntity(update, urlEntity)).thenReturn(urlText);

            when(userRepository.removeLink(userId, urlText)).thenReturn(true);

            SendMessage result = untrackCommand.handle(update);

            assertThat(result.getParameters()
                .get("text")).isEqualTo(StandardResponseMessage.LINK_NO_LONGER_TRACKED.getMessage());
        }
    }

    @Test
    public void whenUrlWasNotTracked_thenShouldReturnWasNotTrackedMessage() {
        try (var mocked = mockStatic(EntityUtils.class)) {
            mocked.when(() -> EntityUtils.getEntityByName(update, EntityType.URL.getName())).thenReturn(urlEntity);
            mocked.when(() -> EntityUtils.getTextFromEntity(update, urlEntity)).thenReturn(urlText);

            when(userRepository.removeLink(userId, urlText)).thenReturn(false);

            SendMessage result = untrackCommand.handle(update);

            assertThat(result.getParameters()
                .get("text")).isEqualTo(StandardResponseMessage.LINK_WAS_NOT_TRACKED.getMessage());
        }
    }

    @Test
    public void whenUrlNotProvided_thenShouldReturnNotRecognizedMessage() {
        try (var mocked = mockStatic(EntityUtils.class)) {
            mocked.when(() -> EntityUtils.getEntityByName(update, EntityType.URL.getName())).thenReturn(null);

            SendMessage result = untrackCommand.handle(update);

            assertThat(result.getParameters()
                .get("text")).isEqualTo(StandardResponseMessage.LINK_NOT_RECOGNIZED.getMessage());
        }
    }
}
