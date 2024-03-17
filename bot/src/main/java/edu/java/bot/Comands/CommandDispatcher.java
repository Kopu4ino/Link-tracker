package edu.java.bot.Comands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.ResponseMessage.StandardResponseMessage;
import edu.java.bot.api.client.ScrapperClient;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class CommandDispatcher {
    private final Map<String, Command> commandsMap;
    private final ScrapperClient scrapperClient;

    public CommandDispatcher(@Autowired List<Command> commands, ScrapperClient scrapperClient) {
        this.scrapperClient = scrapperClient;
        this.commandsMap = commands.stream().collect(Collectors.toMap(Command::getName, Function.identity()));
//        log.info("CommandImpl: " + commands);
    }

    public SendMessage execute(Update update, String commandName) {
        Long userId = update.message().chat().id();
        Optional<String> registerResponse = scrapperClient.registerChat(userId);
        boolean isUserRegistered = registerResponse.isPresent();

        if (isUserRegistered || commandName.equals(CommandType.START.getName())) {
            if (commandsMap.containsKey(commandName)) {
                return commandsMap.get(commandName).handle(update);
            } else {
                return new SendMessage(userId, StandardResponseMessage.COMMAND_UNSUPPORTED.getMessage());
            }
        } else {
            return new SendMessage(userId, StandardResponseMessage.NOT_REGISTERED_YET.getMessage());
        }
    }
}
