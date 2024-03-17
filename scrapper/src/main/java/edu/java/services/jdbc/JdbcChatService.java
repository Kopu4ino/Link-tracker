package edu.java.services.jdbc;

import edu.java.domain.chat.ChatRepository;
import edu.java.domain.dto.Chat;
import edu.java.services.ChatService;
import edu.java.services.exceptions.ChatAlreadyRegisteredException;
import edu.java.services.exceptions.ChatIdNotExistsException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JdbcChatService implements ChatService {
    private final ChatRepository chatRepository;

    @Override
    public void registerChat(Long chatId) {
        Optional<Chat> chat = chatRepository.findById(chatId);
        if (chat.isPresent()) {
            throw new ChatAlreadyRegisteredException(chatId);
        }
        chatRepository.add(chatId);
    }

    @Override
    public void unregisterChat(Long chatId) {
        Optional<Chat> chat = findChat(chatId);
        if (chat.isEmpty()) {
            throw new ChatIdNotExistsException(chatId);
        }
        chatRepository.delete(chatId);
    }

    @Override
    public Optional<Chat> findChat(Long chatId) {
        return chatRepository.findById(chatId);
    }
}
