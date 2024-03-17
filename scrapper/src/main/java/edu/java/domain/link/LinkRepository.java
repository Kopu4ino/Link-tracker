package edu.java.domain.link;

import edu.java.domain.dto.Link;
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
    public Link add(long chatId, Link link) {
        Optional<Link> savedLink = findLinkByUrl(link.getUrl());

        if (savedLink.isEmpty()) {
            jdbcTemplate.update("INSERT INTO link (url, lastupdate) VALUES (?, ?)",
                link.getUrl(), link.getLastUpdate()
            );
            savedLink = findLinkByUrl(link.getUrl());
        }

        jdbcTemplate
            .update("INSERT INTO chat_link (chatid, linkid) VALUES (?, ?)", chatId, savedLink.get().getId());
        return savedLink.get();
    }

    @Transactional
    public Optional<Link> findLinkByUrl(String url) {
        return jdbcTemplate
            .query("SELECT * FROM link WHERE url = ?", new BeanPropertyRowMapper<>(Link.class), url)
            .stream().findAny();
    }
}
