package edu.java.bot.Links;

import java.net.URI;

public interface LinkHandler {
    boolean handle(URI url);

    LinkHandler setNext(LinkHandler handler);
}
