//package edu.bot.command;
//
//import com.pengrad.telegrambot.model.Chat;
//import com.pengrad.telegrambot.model.Message;
//import com.pengrad.telegrambot.model.Update;
//import com.pengrad.telegrambot.request.SendMessage;
//import edu.java.bot.Comands.Command;
//import edu.java.bot.Comands.CommandDispatcher;
//import edu.java.bot.Comands.CommandType;
//import edu.java.bot.Comands.HelpCommand;
//import edu.java.bot.Comands.StartCommand;
//import edu.java.bot.Repository.UserRepository;
//import edu.java.bot.ResponseMessage.StandardResponseMessage;
//import java.util.Arrays;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class CommandDispatcherTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private StartCommand startCommand;
//
//    @Mock
//    private HelpCommand helpCommand;
//
//    private CommandDispatcher commandDispatcher;
//
//    private Update update;
//    private final Long userId = 111L;
//
//    @BeforeEach
//    public void setUp() {
//        update = mock(Update.class);
//        Message message = mock(Message.class);
//        Chat chat = mock(Chat.class);
//
//        when(update.message()).thenReturn(message);
//        when(message.chat()).thenReturn(chat);
//        when(chat.id()).thenReturn(userId);
//
//        when(startCommand.getName()).thenReturn(CommandType.START.getName());
//        when(helpCommand.getName()).thenReturn(CommandType.HELP.getName());
//
//        List<Command> commands = Arrays.asList(startCommand, helpCommand);
//
//        commandDispatcher = new CommandDispatcher(commands, userRepository);
//    }
//
//    @Test
//    public void whenUserRegisteredAndCommandSupported_thenShouldExecuteCommand() {
//        when(userRepository.isUserRegistered(userId)).thenReturn(true);
//        when(helpCommand.handle(update)).thenReturn(new SendMessage(userId, "Help message"));
//
//        SendMessage result = commandDispatcher.execute(update, CommandType.HELP.getName());
//
//        assertThat(result.getParameters().get("text")).isEqualTo("Help message");
//    }
//
//    @Test
//    public void whenUserRegisteredAndCommandUnsupported_thenShouldReturnUnsupportedMessage() {
//        when(userRepository.isUserRegistered(userId)).thenReturn(true);
//
//        SendMessage result = commandDispatcher.execute(update, "nonexistent");
//
//        assertThat(result.getParameters()
//            .get("text")).isEqualTo(StandardResponseMessage.COMMAND_UNSUPPORTED.getMessage());
//    }
//
//    @Test
//    public void whenUserNotRegisteredAndCommandNotStart_thenShouldReturnNotRegisteredMessage() {
//        when(userRepository.isUserRegistered(userId)).thenReturn(false);
//
//        SendMessage result = commandDispatcher.execute(update, CommandType.HELP.getName());
//
//        assertThat(result.getParameters()
//            .get("text")).isEqualTo(StandardResponseMessage.NOT_REGISTERED_YET.getMessage());
//    }
//
//    @Test
//    public void whenUserNotRegisteredAndCommandIsStart_thenShouldExecuteStartCommand() {
//        when(userRepository.isUserRegistered(userId)).thenReturn(false);
//        when(startCommand.handle(update)).thenReturn(new SendMessage(userId, "Welcome"));
//
//        SendMessage result = commandDispatcher.execute(update, CommandType.START.getName());
//
//        assertThat(result.getParameters().get("text")).isEqualTo("Welcome");
//    }
//}
