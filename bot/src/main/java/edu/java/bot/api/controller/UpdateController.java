package edu.java.bot.api.controller;

import edu.java.bot.api.dto.request.LinkUpdateRequest;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/updates")
@RestController
@Log4j2
public class UpdateController {

    @PostMapping
    public String receiveUpdate(@RequestBody @Valid LinkUpdateRequest update) {
        log.info("Received update: " + update);
        return "Обновление обработано";
    }
}
