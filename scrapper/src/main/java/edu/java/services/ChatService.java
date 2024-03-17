package edu.java.services;

import edu.java.domain.dto.Chat;
import java.util.Optional;

public interface ChatService {
    void registerChat(Long chatId);

    void unregisterChat(Long chatId);

    Optional<Chat> findChat(Long chatId);
}
