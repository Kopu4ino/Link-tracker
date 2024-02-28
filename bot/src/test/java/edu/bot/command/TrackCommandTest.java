package edu.bot.command;

import com.pengrad.telegrambot.model.MessageEntity;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.Comands.TrackCommand;
import edu.java.bot.Entity.EntityType;
import edu.java.bot.Links.LinkHandler;
import edu.java.bot.Repository.UserRepository;
import edu.java.bot.ResponseMessage.StandardResponseMessage;
import edu.java.bot.Utils.EntityUtils;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TrackCommandTest extends CommandTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private List<LinkHandler> handlers;
    @Mock
    private MessageEntity urlEntity;
    @Mock
    LinkHandler GitHubLinkHandler;

    @InjectMocks
    private TrackCommand trackCommand;

    private final String urlText = "https://github.com/artur";

    @Test
    public void whenUrlAddedSuccessfully_thenShouldReturnSuccessMessage() {
        try (var mocked = mockStatic(EntityUtils.class)) {
            mocked.when(() -> EntityUtils.getEntityByName(update, EntityType.URL.getName())).thenReturn(urlEntity);
            mocked.when(() -> EntityUtils.getTextFromEntity(update, urlEntity)).thenReturn(urlText);

            when(trackCommand.buildChain()).thenReturn(GitHubLinkHandler);
            when(GitHubLinkHandler.handle(any())).thenReturn(true);
            when(userRepository.addLink(anyLong(), eq(urlText))).thenReturn(true);

            SendMessage result = trackCommand.handle(update);

            assertThat(result.getParameters()
                .get("text")).isEqualTo(StandardResponseMessage.LINK_ADDED_SUCCESSFULLY.getMessage());
        }
    }

    @Test
    public void whenUrlAlreadyTracked_thenShouldReturnAlreadyTrackedMessage() {
        try (var mocked = mockStatic(EntityUtils.class)) {
            mocked.when(() -> EntityUtils.getEntityByName(update, EntityType.URL.getName())).thenReturn(urlEntity);
            mocked.when(() -> EntityUtils.getTextFromEntity(update, urlEntity)).thenReturn(urlText);

            when(trackCommand.buildChain()).thenReturn(GitHubLinkHandler);
            when(GitHubLinkHandler.handle(any())).thenReturn(true);
            when(userRepository.addLink(anyLong(), eq(urlText))).thenReturn(false);

            SendMessage result = trackCommand.handle(update);

            assertThat(result.getParameters()
                .get("text")).isEqualTo(StandardResponseMessage.LINK_ALREADY_TRACKED.getMessage());
        }
    }

    @Test
    public void whenUrlNotProvided_thenShouldReturnUsageMessage() {
        try (var mocked = mockStatic(EntityUtils.class)) {
            mocked.when(() -> EntityUtils.getEntityByName(update, EntityType.URL.getName())).thenReturn(null);

            SendMessage result = trackCommand.handle(update);

            assertThat(result.getParameters()
                .get("text")).isEqualTo(StandardResponseMessage.ADD_LINK_COMMAND_USAGE.getMessage());
        }
    }

    // Дополнительные тесты по необходимости...
}
