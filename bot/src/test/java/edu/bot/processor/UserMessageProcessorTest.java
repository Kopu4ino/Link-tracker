package edu.bot.processor;

import com.pengrad.telegrambot.model.Chat;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.MessageEntity;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.Comands.CommandDispatcher;
import edu.java.bot.Comands.CommandType;
import edu.java.bot.Entity.EntityType;
import edu.java.bot.ResponseMessage.StandardResponseMessage;
import edu.java.bot.Processors.UserMessageProcessor;
import edu.java.bot.Utils.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserMessageProcessorTest {

    @Mock
    private CommandDispatcher commandDispatcher;

    @InjectMocks
    private UserMessageProcessor userMessageProcessor;

    private Update update;
    private final Long chatId = 111L;

    @BeforeEach
    public void setUp() {
        update = mock(Update.class);
        Message message = mock(Message.class);
        Chat chat = mock(Chat.class);
        MessageEntity messageEntity = mock(MessageEntity.class);
        MessageEntity[] entities = {messageEntity};

        lenient().when(update.message()).thenReturn(message);
        lenient().when(message.chat()).thenReturn(chat);
        lenient().when(message.entities()).thenReturn(entities);
        lenient().when(chat.id()).thenReturn(chatId);
    }

    @Test
    public void whenCommandPresent_thenDelegateToCommandDispatcher() {
        MessageEntity commandEntity = mock(MessageEntity.class);

        try (var mocked = mockStatic(EntityUtils.class)) {
            mocked.when(() -> EntityUtils.getEntityByName(update, EntityType.BOT_COMMAND.getName()))
                .thenReturn(commandEntity);
            mocked.when(() -> EntityUtils.getTextFromEntity(update, commandEntity))
                .thenReturn(CommandType.START.getName());
            SendMessage expectedSendMessage =
                new SendMessage(chatId, StandardResponseMessage.SUCCESS_REGISTRATION.getMessage());

            when(commandDispatcher.execute(update, CommandType.START.getName())).thenReturn(expectedSendMessage);

            SendMessage actualSendMessage = userMessageProcessor.process(update);

            assertThat(actualSendMessage.getParameters()
                .get("text")).isEqualTo(StandardResponseMessage.SUCCESS_REGISTRATION.getMessage());
        }
    }

    @Test
    public void whenCommandAbsent_thenReturnStandardMessage() {
        when(update.message().entities()).thenReturn(null);

        SendMessage actualSendMessage = userMessageProcessor.process(update);

        assertThat(actualSendMessage.getParameters()
            .get("text")).isEqualTo(StandardResponseMessage.USING_COMMAND_PLS.getMessage());
    }
}
