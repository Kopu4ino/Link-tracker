package edu.java.bot.Comands;

import lombok.Getter;

@Getter
public enum CommandType {
    START("/start", "Запуск бота и регистрация пользователя."),
    HELP("/help", "Показать список всех доступных команд."),
    TRACK("/track", "Начать отслеживание ссылки."),
    UNTRACK("/untrack", "Прекратить отслеживание ссылки."),
    LIST("/list", "Показать список всех отслеживаемых ссылок.");

    private final String name;
    private final String description;

    CommandType(String command, String description) {
        this.name = command;
        this.description = description;
    }
}

