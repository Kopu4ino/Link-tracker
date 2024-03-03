package edu.java;

import edu.java.configuration.ApplicationConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
@Log4j2
public class ScrapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrapperApplication.class, args);

//        BotClient botClient = new BotClient(WebClient.builder(), "http://localhost:8090");
//        LinkUpdateRequest linkUpdateRequest = new LinkUpdateRequest();
//        linkUpdateRequest.setId(null);
//        linkUpdateRequest.setUrl("https://sdfsdfs");
//        linkUpdateRequest.setTgChatIds(List.of(1L, 2L, 3L, 4L));
//
//        log.info("Пытаюсь отправить запрос");
//        Optional<String> response = botClient.sendUpdate(linkUpdateRequest);
//
//        log.info("isPresent? --> " + response.isPresent());
//        log.info(response.get());
    }
}
