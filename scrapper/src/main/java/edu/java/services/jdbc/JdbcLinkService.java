package edu.java.services.jdbc;

import edu.java.domain.dto.Link;
import edu.java.services.LinkService;
import java.time.OffsetDateTime;
import java.util.List;

public class JdbcLinkService implements LinkService {
    @Override
    public List<Link> getUserLinks(Long chatId) {
        return null;
    }

    @Override
    public Link addLink(Long chatId, Link link) {
        return null;
    }

    @Override
    public Link deleteLink(Long chatId, Link link) {
        return null;
    }

    @Override
    public List<Link> findAllOutdatedLinks(Integer count, Long interval) {
        return null;
    }

    @Override
    public void setUpdateAndCheckTime(Link link, OffsetDateTime lastUpdateTime, OffsetDateTime lastCheckTime) {

    }
}
