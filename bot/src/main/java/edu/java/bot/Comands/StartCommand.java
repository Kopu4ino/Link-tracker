package edu.java.bot.Comands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import edu.java.bot.Repository.UserRepository;
import edu.java.bot.ResponseMessage.StandardResponseMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StartCommand implements Command {
    @Autowired
    private final UserRepository repository;

    @Override
    public SendMessage handle(Update update) {
        Long userId = update.message().chat().id();
        if (!repository.isUserRegistered(userId)) {
            repository.registerUser(userId);
            return new SendMessage(userId, StandardResponseMessage.SUCCESS_REGISTRATION.getMessage());
        }
        return new SendMessage(userId, StandardResponseMessage.ALREADY_REGISTERED.getMessage());
    }

    @Override
    public String getName() {
        return CommandType.START.getName();
    }
}
