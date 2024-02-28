package edu.java.bot.Processors;

import com.pengrad.telegrambot.model.MessageEntity;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.Comands.CommandDispatcher;
import edu.java.bot.Entity.EntityType;
import edu.java.bot.ResponseMessage.StandardResponseMessage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static edu.java.bot.Utils.EntityUtils.getEntityByName;
import static edu.java.bot.Utils.EntityUtils.getTextFromEntity;

@Component
@Log4j2
@AllArgsConstructor
public class UserMessageProcessor {
    @Autowired
    private final CommandDispatcher commandDispatcher;

    public SendMessage process(Update update) {
        MessageEntity commandEntity = null;

        if (update != null && update.message().entities() != null) {
            commandEntity = getEntityByName(update, EntityType.BOT_COMMAND.getName());
        }

        if (commandEntity != null) {
            String command = getTextFromEntity(update, commandEntity);
            log.info("command: " + command);
            return commandDispatcher.execute(update, command);
        } else {
            return new SendMessage(
                update.message().chat().id(), StandardResponseMessage.USING_COMMAND_PLS.getMessage());
        }
    }
}
