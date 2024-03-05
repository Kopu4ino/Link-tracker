package edu.java.api.controller;

import edu.java.services.ChatLinksService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shared.dto.request.AddLinkRequest;
import shared.dto.request.RemoveLinkRequest;
import shared.dto.response.LinkResponse;

@RequestMapping("/links")
@RestController
@AllArgsConstructor
public class LinkController {
    @Autowired
    private final ChatLinksService service;

    @GetMapping
    public List<LinkResponse> getLinks(@RequestHeader("Tg-Chat-Id") Long chatId) {
        return service.getChatLinks(chatId);
    }

    @PostMapping
    public LinkResponse addLink(@RequestHeader("Tg-Chat-Id") Long chatId, @RequestBody AddLinkRequest link) {
        return service.addLink(chatId, link);
    }

    @DeleteMapping
    public LinkResponse deleteLink(@RequestHeader("Tg-Chat-Id") Long chatId, @RequestBody RemoveLinkRequest link) {
        return service.removeLink(chatId, link);
    }
}
