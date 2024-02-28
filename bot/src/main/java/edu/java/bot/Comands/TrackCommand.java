package edu.java.bot.Comands;

import com.pengrad.telegrambot.model.MessageEntity;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.Entity.EntityType;
import edu.java.bot.Links.LinkHandler;
import edu.java.bot.Repository.UserRepository;
import edu.java.bot.ResponseMessage.StandardResponseMessage;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static edu.java.bot.Utils.EntityUtils.getEntityByName;
import static edu.java.bot.Utils.EntityUtils.getTextFromEntity;

@Component
@AllArgsConstructor
@Log4j2
public class TrackCommand implements Command {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final List<LinkHandler> handlers;

    @Override
    public SendMessage handle(Update update) {
        String message;
        Long userId = update.message().chat().id();

        MessageEntity urlEntity = getEntityByName(update, EntityType.URL.getName());
        if (urlEntity != null) {
            String urlText = getTextFromEntity(update, urlEntity);
            boolean isHandled = false;
            try {
                URI uri = new URI(urlText);
                LinkHandler chain = buildChain();
                if (chain != null) {
                    isHandled = chain.handle(uri);
                }
            } catch (URISyntaxException e) {
                log.error("Ошибка преобразования ссылки в uri. Ссылка: " + urlText);
                throw new RuntimeException(e);
            }

            if (isHandled) {
                boolean wasAdded = userRepository.addLink(userId, urlText);
                if (wasAdded) {
                    message = StandardResponseMessage.LINK_ADDED_SUCCESSFULLY.getMessage();
                } else {
                    message = StandardResponseMessage.LINK_ALREADY_TRACKED.getMessage();
                }

            } else {
                message = StandardResponseMessage.LINK_PROCESSING_FAILED.getMessage();
            }
        } else {
            message = StandardResponseMessage.ADD_LINK_COMMAND_USAGE.getMessage();
        }

        return new SendMessage(userId, message);
    }

    public LinkHandler buildChain() {
        for (int i = 0; i < handlers.size() - 1; i++) {
            handlers.get(i).setNext(handlers.get(i + 1));
        }
        return handlers.isEmpty() ? null : handlers.getFirst();
    }

    @Override
    public String getName() {
        return CommandType.TRACK.getName();
    }
}
