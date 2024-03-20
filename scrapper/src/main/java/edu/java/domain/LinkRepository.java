package edu.java.domain;

import edu.java.domain.model.Link;
import java.time.OffsetDateTime;
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
public class LinkRepository {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public Link add(Long chatId, Link link) {
        Optional<Link> savedLink = findLinkByUrl(link.getUrl());

        if (savedLink.isEmpty()) {
            jdbcTemplate.update("INSERT INTO link (url, lastupdate, lastcheck) VALUES (?, ?, ?)",
                link.getUrl(), link.getLastUpdate(), link.getLastCheck()
            );
            savedLink = findLinkByUrl(link.getUrl());
        }

        jdbcTemplate
            .update("INSERT INTO chat_link (chatid, linkid) VALUES (?, ?)", chatId, savedLink.get().getId());
        return savedLink.get();
    }

    @Transactional
    public void delete(Long chatId, Long linkId) {
        jdbcTemplate.update("DELETE FROM chat_link WHERE chatid = ? AND linkid = ?", chatId, linkId);

        List<Long> chatIdsWithThisLink =
            jdbcTemplate.queryForList("SELECT chatid FROM chat_link WHERE linkid = ?", Long.class, linkId);

        if (chatIdsWithThisLink.isEmpty()) {
            jdbcTemplate.update("DELETE FROM link WHERE id = ?", linkId);
        }
    }

    @Transactional
    public Optional<Link> findLinkByUrl(String url) {
        return jdbcTemplate
            .query("SELECT * FROM link WHERE url = ?", new BeanPropertyRowMapper<>(Link.class), url)
            .stream().findAny();
    }

    public List<Link> findChatLinks(long chatId) {
        return jdbcTemplate
            .query("""
                        SELECT l.* FROM link l JOIN chat_link cl ON l.id = cl.linkid JOIN chat c
                            ON c.id = cl.chatid WHERE c.id=?
                    """,
                new BeanPropertyRowMapper<>(Link.class), chatId
            );
    }

    public Optional<Link> findLinkByChatIdAndUrl(long chatId, String url) {
        return jdbcTemplate
            .query("""
                    SELECT l.* FROM link l JOIN chat_link cl ON cl.chatid = ?
                        AND cl.linkid = l.id WHERE l.url = ?
                    """,
                new BeanPropertyRowMapper<>(Link.class), chatId, url
            ).stream().findAny();
    }

    public List<Link> findAllOutdatedLinks(Integer count, Long interval) {
        return jdbcTemplate
            .query("""
                    SELECT * FROM Link WHERE EXTRACT(EPOCH FROM (CURRENT_TIMESTAMP - lastcheck)) >= ? LIMIT ?
                    """,
                new BeanPropertyRowMapper<>(Link.class), interval, count
            );
    }

    public void setUpdateAndCheckTime(Link link, OffsetDateTime lastUpdateTime, OffsetDateTime lastCheckTime) {
        jdbcTemplate
            .update("UPDATE Link SET lastupdate = ?, lastcheck = ? WHERE id = ?",
                lastUpdateTime, lastCheckTime, link.getId()
            );
    }
}
