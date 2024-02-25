package com.theElite.connect4_backend.pojo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerMove {
    private int colIndex;
    private int moveIdentifier;
    private int[][] board;
    private boolean hasWon;
    private int turn;
}
