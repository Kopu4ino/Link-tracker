package edu.java.services;

import edu.java.services.exceptions.ChatAlreadyRegisteredException;
import edu.java.services.exceptions.ChatIdNotExistsException;
import edu.java.services.exceptions.LinkAlreadyTrackException;
import edu.java.services.exceptions.LinkNotExistsException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import shared.dto.request.AddLinkRequest;
import shared.dto.request.RemoveLinkRequest;
import shared.dto.response.LinkResponse;

@Component
@Log4j2
public class ChatLinksService {
    private final Map<Long, List<LinkResponse>> chatLinks = new HashMap<>();
    private Long lastLinkId = 1L;

    public void registerChat(Long id) {
        if (chatLinks.containsKey(id)) {
            throw new ChatAlreadyRegisteredException("Chat already registered");
        }

        chatLinks.put(id, new ArrayList<>());
        log.info("Chat with id {} registered", id);
    }

    public void deleteChat(Long id) {
        if (!chatLinks.containsKey(id)) {
            throw new ChatIdNotExistsException("Can not delete chat, cause chat id not exists");
        }

        log.info("Chat with id {} deleted", id);
        chatLinks.remove(id);
    }

    public List<LinkResponse> getChatLinks(Long id) {
        if (!chatLinks.containsKey(id)) {
            throw new ChatIdNotExistsException("Can not return links, cause chat id not exists");
        }

        log.info("Returning links for chat with id {}", id);
        return chatLinks.get(id);
    }

    public LinkResponse addLink(Long chatId, AddLinkRequest link) {
        if (!chatLinks.containsKey(chatId)) {
            throw new ChatIdNotExistsException("Can not add link, cause chat id not exists");
        }

        List<LinkResponse> links = chatLinks.get(chatId);
        links.stream()
            .filter(l -> l.url().equals(link.url()))
            .findAny()
            .ifPresent(l -> {
                throw new LinkAlreadyTrackException("Can not add link, cause link already tracked");
            });

        LinkResponse linkResponse = new LinkResponse(lastLinkId, link.url());
        lastLinkId += 1L;
        links.add(linkResponse);

        log.info("Link {} added to chat with id {}", link.url(), chatId);
        return linkResponse;
    }

    public LinkResponse removeLink(Long chatId, RemoveLinkRequest link) {
        if (!chatLinks.containsKey(chatId)) {
            throw new ChatIdNotExistsException("Can not remove link, cause chat id not exists");
        }

        List<LinkResponse> links = chatLinks.get(chatId);
        LinkResponse linkResponse = links.stream()
            .filter(l -> l.url().equals(link.url()))
            .findAny()
            .orElseThrow(() -> new LinkNotExistsException("Can not remove link, cause link with url: {%s} not exists"
                .formatted(link.url())));

        links.remove(linkResponse);
        log.info("Link {} removed from chat with id {}", link.url(), chatId);
        return linkResponse;
    }
}
