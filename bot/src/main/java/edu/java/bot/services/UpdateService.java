package edu.java.bot.services;

import edu.java.bot.services.exceptions.UpdateAlreadyExistsException;
import java.util.HashSet;
import java.util.Set;
import org.springframework.stereotype.Service;
import shared.dto.request.LinkUpdateRequest;

@Service
public class UpdateService {
    private final Set<LinkUpdateRequest> updates = new HashSet<>();

    public void addUpdate(LinkUpdateRequest update) {
        if (!updates.contains(update)) {
            updates.add(update);
        } else {
            throw new UpdateAlreadyExistsException("Update already exists");
        }
    }

}
