package edu.java.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Chat {
    private Long id;

    public Chat(Long id) {
        this.id = id;
    }
}
