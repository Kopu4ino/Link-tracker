package edu.java.domain;

import edu.java.domain.model.Chat;
import edu.java.domain.model.Link;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class ChatRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void add(Long chatId) {
        jdbcTemplate.update("INSERT INTO chat VALUES (?)", chatId);
    }

    @Transactional
    public Optional<Chat> findById(Long chatId) {
        return jdbcTemplate.query("select * from chat where id=?", new BeanPropertyRowMapper<>(Chat.class), chatId)
            .stream().findAny();
    }

    @Transactional
    public void delete(Long chatId) {
        jdbcTemplate.update("DELETE FROM chat WHERE id=?", chatId);
    }

    @Transactional
    public List<Link> findAllTrackedLinksById(Long chatId) {
        return jdbcTemplate.query(
            "SELECT * FROM chat_link where chatId=?", new BeanPropertyRowMapper<>(Link.class), chatId);
    }

    public List<Long> findAllChatIdsWithLink(Long linkId) {
        return
            jdbcTemplate.queryForList("SELECT chatid FROM chat_link WHERE linkid = ?", Long.class, linkId);
    }

}
