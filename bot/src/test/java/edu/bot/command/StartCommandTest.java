package edu.bot.command;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.Comands.StartCommand;
import edu.java.bot.Repository.UserRepository;
import edu.java.bot.ResponseMessage.StandardResponseMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class StartCommandTest extends CommandTest {
    @Mock
    private UserRepository userRepository;

    private StartCommand startCommand;

    @BeforeEach
    public void setUpStartCommand() {
        super.setUp();
        startCommand = new StartCommand(userRepository);
    }

    @Test
    public void whenUserNotRegistered_thenShouldRegisterUser() {
        // Arrange
        when(userRepository.isUserRegistered(userId)).thenReturn(false);

        // Act
        SendMessage result = startCommand.handle(update);

        // Assert
        verify(userRepository, times(1)).registerUser(userId);
        assertThat(result.getParameters().get("chat_id")).isEqualTo(userId);
        assertThat(result.getParameters()
            .get("text")).isEqualTo(StandardResponseMessage.SUCCESS_REGISTRATION.getMessage());
    }

    @Test
    public void whenUserAlreadyRegistered_thenShouldNotRegisterAgain() {
        // Arrange
        when(userRepository.isUserRegistered(userId)).thenReturn(true);

        // Act
        SendMessage result = startCommand.handle(update);

        // Assert
        verify(userRepository, never()).registerUser(userId);
        assertThat(result.getParameters().get("chat_id")).isEqualTo(userId);
        assertThat(result.getParameters()
            .get("text")).isEqualTo(StandardResponseMessage.ALREADY_REGISTERED.getMessage());
    }
}
