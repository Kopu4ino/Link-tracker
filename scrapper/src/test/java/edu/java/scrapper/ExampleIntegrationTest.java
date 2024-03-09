package edu.java.scrapper;

import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ExampleIntegrationTest extends IntegrationTest {

    @Test
    public void testConnection() {
        assertThat(POSTGRES.isRunning()).isTrue();
        assertThat(POSTGRES.getUsername()).isEqualTo("postgres");
        assertThat(POSTGRES.getPassword()).isEqualTo("postgres");
        assertThat(POSTGRES.getDatabaseName()).isEqualTo("scrapper");
    }

    @Test
    public void ChatTableManipulationTest() {
        //Arrange
        String expectedChatName = "Kopu4ino";
        Long expectedChatId = 1L;

        //Act
        jdbcTemplate.update("insert into chat (user_name) values (?)", expectedChatName);

        String actualChatName = jdbcTemplate
            .queryForObject("select user_name from chat where id = ?", String.class, expectedChatId);

        //Assert
        assertThat(actualChatName).isEqualTo(expectedChatName);
    }

    @Test
    public void LinkTableManipulationTest() {
        //Arrange
        String expectedUrl = "https://github.com/kopu4ino/java-2024";
        OffsetDateTime expectedLastUpdate = Instant.ofEpochSecond(100000000).atOffset(ZoneOffset.UTC);

        //Act
        jdbcTemplate.update("insert into Link (url, last_update) values (?, ?)", expectedUrl, expectedLastUpdate);

        OffsetDateTime actualLastUpdate = jdbcTemplate
            .queryForObject("select last_update from link where url = ?", OffsetDateTime.class, expectedUrl);

        //Assert
        assertThat(actualLastUpdate).isEqualTo(expectedLastUpdate);
    }
}
