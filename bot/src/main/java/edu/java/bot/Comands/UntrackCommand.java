package edu.java.bot.Comands;

import com.pengrad.telegrambot.model.MessageEntity;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.Entity.EntityType;
import edu.java.bot.Repository.UserRepository;
import edu.java.bot.ResponseMessage.StandardResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static edu.java.bot.Utils.EntityUtils.getEntityByName;
import static edu.java.bot.Utils.EntityUtils.getTextFromEntity;

@Component
public class UntrackCommand implements Command {
    @Autowired
    UserRepository userRepository;

    @Override
    public SendMessage handle(Update update) {
        String message;
        Long userId = update.message().chat().id();

        MessageEntity urlEntity = getEntityByName(update, EntityType.URL.getName());
        if (urlEntity != null) {
            String urlText = getTextFromEntity(update, urlEntity);
            boolean wasRemoved = userRepository.removeLink(userId, urlText);

            if (wasRemoved) {
                message = StandardResponseMessage.LINK_NO_LONGER_TRACKED.getMessage();
            } else {
                message = StandardResponseMessage.LINK_WAS_NOT_TRACKED.getMessage();
            }
        } else {
            message = StandardResponseMessage.LINK_NOT_RECOGNIZED.getMessage();
        }
        return new SendMessage(userId, message);
    }

    @Override
    public String getName() {
        return CommandType.UNTRACK.getName();
    }
}
