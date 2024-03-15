package edu.java.api.controller;

import edu.java.services.ChatLinksService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/tg-chat")
@RestController
@AllArgsConstructor
public class ChatController {
    @Autowired
    private final ChatLinksService chatService;

    @PostMapping("/{id}")
    public String registerChat(@PathVariable @NotNull Long id) {
        chatService.registerChat(id);
        return "Chat registered";
    }

    @DeleteMapping("/{id}")
    public String deletChat(@PathVariable @NotNull Long id) {
        chatService.deleteChat(id);
        return "Chat deleted";
    }
}
