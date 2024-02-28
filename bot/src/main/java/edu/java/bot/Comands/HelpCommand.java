package edu.java.bot.Comands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class HelpCommand implements Command {
    @Override
    public SendMessage handle(Update update) {
        StringBuilder builder = new StringBuilder();
        builder.append("Список команд:\n");
        for (CommandType type : CommandType.values()) {
            builder.append(type.getName()).append(" - ").append(type.getDescription()).append("\n");
        }
        return new SendMessage(update.message().chat().id(), builder.toString());
    }

    @Override
    public String getName() {
        return CommandType.HELP.getName();
    }
}
