package com.theElite.connect4_backend.pojo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {
    private String username;
    private MessageType type;
}
