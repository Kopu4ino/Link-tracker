package edu.bot.command;

import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.Comands.CommandType;
import edu.java.bot.Comands.HelpCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelpCommandTest extends CommandTest {

    private HelpCommand helpCommand;

    @BeforeEach
    public void setUpHelpCommand() {
        super.setUp();
        helpCommand = new HelpCommand();
    }

    @Test
    public void testHandleReturnsCorrectMessage() {
        SendMessage response = helpCommand.handle(update);

        assertThat(response.getParameters().get("chat_id")).isEqualTo(userId);

        String expectedMessageStart = "Список команд:\n";
        assertThat(response.getParameters().get("text").toString()).startsWith(expectedMessageStart);

        for (CommandType commandType : CommandType.values()) {
            String commandInfo = commandType.getName() + " - " + commandType.getDescription();
            assertThat(response.getParameters().get("text").toString()).contains(commandInfo);
        }
    }
}
