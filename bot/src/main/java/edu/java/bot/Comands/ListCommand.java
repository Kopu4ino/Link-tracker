package edu.java.bot.Comands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.Repository.UserRepository;
import edu.java.bot.ResponseMessage.StandardResponseMessage;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Log4j2
public class ListCommand implements Command {
    @Autowired
    private final UserRepository userRepository;

    @Override
    public SendMessage handle(Update update) {
        Long userId = update.message().chat().id();
        List<String> userLinks = userRepository.getUserLinksById(userId);
        String message;
        if (userLinks.isEmpty()) {
            message = StandardResponseMessage.NO_SERVICES_BEING_TRACKED.getMessage();
        } else {
            StringBuilder builder = new StringBuilder();
            for (String userLink : userLinks) {
                builder.append(userLink).append("\n");
            }
            message = builder.toString();
        }
        return new SendMessage(userId, message);
    }

    @Override
    public String getName() {
        return CommandType.LIST.getName();
    }
}
