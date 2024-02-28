package edu.java.bot.Utils;

import com.pengrad.telegrambot.model.MessageEntity;
import com.pengrad.telegrambot.model.Update;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class EntityUtils {
    private EntityUtils() {
    }

    public static String getTextFromEntity(Update update, MessageEntity entity) {
        Integer offset = entity.offset();
        Integer length = entity.length();

        return update.message().text().substring(offset, offset + length);
    }

    public static MessageEntity getEntityByName(Update update, String entityName) {
        for (MessageEntity entity : update.message().entities()) {
            if (entity.type().toString().equals(entityName)) {
                return entity;
            }
        }
        log.warn("Не смогли обнаружить нужный entity");
        return null;
    }
}
