package edu.java.bot.api.controller;

import edu.java.bot.services.UpdateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shared.dto.request.LinkUpdateRequest;

@RequestMapping("/updates")
@RestController
@Log4j2
@AllArgsConstructor
public class UpdateController {
    @Autowired
    private final UpdateService updateService;

    @PostMapping
    public String receiveUpdate(@RequestBody @Valid LinkUpdateRequest update) {
        log.info("Received update: " + update);
        updateService.addUpdate(update);
        return "Update received";
    }
}
