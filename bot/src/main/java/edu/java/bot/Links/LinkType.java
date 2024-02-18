package edu.java.bot.Links;

import lombok.Getter;

@Getter
public enum LinkType {
    GitHub("github.com"),
    StackOverFlow("stackoverflow.com");

    LinkType(String name) {
        this.name = name;
    }

    private final String name;
}
