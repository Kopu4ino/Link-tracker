package edu.java.bot.Entity;

import lombok.Getter;

@Getter
public enum EntityType {
    URL("url"),
    BOT_COMMAND("bot_command");

    EntityType(String name) {
        this.name = name;
    }

    private final String name;
    }
