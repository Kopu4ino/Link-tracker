package edu.java.bot.Comands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

public interface Command {
    SendMessage handle(Update update);

    String getName();
}
