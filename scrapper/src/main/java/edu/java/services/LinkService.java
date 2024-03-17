package edu.java.services;

import edu.java.domain.dto.Link;
import java.time.OffsetDateTime;
import java.util.List;

public interface LinkService {
    List<Link> getUserLinks(Long chatId);

    Link addLink(Long chatId, Link link);

    Link deleteLink(Long chatId, Link link);

    List<Link> findAllOutdatedLinks(Integer count, Long interval);

    void setUpdateAndCheckTime(Link link, OffsetDateTime lastUpdateTime, OffsetDateTime lastCheckTime);
}
