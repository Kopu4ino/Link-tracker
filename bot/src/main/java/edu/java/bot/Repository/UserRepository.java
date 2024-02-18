package edu.java.bot.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final Map<Long, List<String>> users = new HashMap<>();

    public boolean isUserRegistered(Long userId) {
        return users.containsKey(userId);
    }

    public void registerUser(Long userId) {
        users.put(userId, new ArrayList<>());
    }

    public List<String> getUserLinksById(Long userId) {
        return users.get(userId);
    }

    public boolean addLink(Long userId, String url) {
        boolean isPresent = isLinkPresent(userId, url);

        if (!isPresent) {
            List<String> userLinks = users.get(userId);
            userLinks.add(url);
        }

        return !isPresent;
    }

    public boolean removeLink(Long userId, String url) {
        boolean isPresent = isLinkPresent(userId, url);

        if (isPresent) {
            List<String> userLinks = users.get(userId);
            userLinks.remove(url);
        }

        return isPresent;
    }

    private boolean isLinkPresent(Long userId, String url) {
        List<String> userLinks = users.get(userId);
        boolean isPresent = false;

        for (String userLink : userLinks) {
            if (userLink.equals(url)) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }
}
