package edu.java.domain.chat;

import edu.java.domain.dto.Chat;
import edu.java.scrapper.IntegrationTest;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ChatRepositoryTest extends IntegrationTest {
    private static ChatRepository chatRepository;

    @BeforeAll
    static void setUp() {
        chatRepository = new ChatRepository(jdbcTemplate);
    }

    @Test
    @Transactional
    public void addTest() {
        //Arrange
        Long chatId = 100L;

        //Act
        chatRepository.add(chatId);
        Optional<Chat> chat = chatRepository.findById(chatId);

        //Assert
        assertThat(chat).isPresent();
        assertThat(chat.get().getId()).isEqualTo(chatId);
    }

    @Test
    @Transactional
    public void deleteTest() {
        //Arrange
        Long chatId = 200L;

        //Act
        chatRepository.add(chatId);
        chatRepository.delete(chatId);
        Optional<Chat> chat = chatRepository.findById(chatId);

        //Assert
        assertThat(chat).isEmpty();
    }

    @Test
    @Transactional
    public void findAllTrackedLinksTest() {
        //TODO
    }

}
