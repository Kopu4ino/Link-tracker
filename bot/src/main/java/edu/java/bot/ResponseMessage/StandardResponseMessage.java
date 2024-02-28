package edu.java.bot.ResponseMessage;

import lombok.Getter;

@Getter
public enum StandardResponseMessage {
    SUCCESS_REGISTRATION("Вы успешно зарегестрированы.\n/help - список команд"),
    ALREADY_REGISTERED("Вы уже зарегистрированы\n/help - список команд"),
    LINK_ADDED_SUCCESSFULLY("Ссылка успешно добавлена"),
    LINK_ALREADY_TRACKED("Переданная ссылка уже обслуживается\n/list"),
    LINK_PROCESSING_FAILED("Не удалось обработать ссылку"),
    ADD_LINK_COMMAND_USAGE("Добавьте к команде /track ссылку\n/track <url>"),
    LINK_NO_LONGER_TRACKED("Ссылка больше не отслеживается\n/list"),
    LINK_WAS_NOT_TRACKED("Данная ссылка не отслеживалась"),
    LINK_NOT_RECOGNIZED("Ссылка не распознана"),
    NO_SERVICES_BEING_TRACKED("Пока нет сервисов на обслуживании.\nИспользуйте: /track <url>"),
    NOT_REGISTERED_YET("Вы не зарегестрированны\nВведите команду /start"),
    COMMAND_UNSUPPORTED("Команда не поддерживается\n/help - список команд"),
    USING_COMMAND_PLS("Используйте команды для взаимодействия.\n/help - список команд");

    StandardResponseMessage(String message) {
        this.message = message;
    }

    private final String message;

}
