package com.theElite.connect4_backend.pojo;

import lombok.Getter;

public class Board {

    private int rows;
    private int cols;
    @Getter
    private int[][] grid;

    public Board() {
        this.rows = 6;
        this.cols = 7;
        initBoard();
    }

    private void initBoard() {
        this.grid = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = BoardPosition.EMPTY.ordinal();
            }
        }
    }

    public void playMove(int colIndex, BoardPosition piece) {
        for (int row = this.rows - 1; row >= 0; row--) {
            if (this.grid[row][colIndex] == BoardPosition.EMPTY.ordinal()) {
                this.grid[row][colIndex] = piece.ordinal();
                return;
            }
        }
    }
}
