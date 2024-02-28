package edu.java.bot.Links;

import java.net.URI;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class StackOverFlowLinkHandler implements LinkHandler {
    LinkHandler next;

    @Override
    public boolean handle(URI url) {
        if (LinkType.StackOverFlow.getName().equals(url.getHost())) {
            return true;
        } else if (next != null) {
            return next.handle(url);
        } else {
            return false;
        }
    }

    @Override
    public LinkHandler setNext(LinkHandler handler) {
        this.next = handler;
        return this.next;
    }
}
