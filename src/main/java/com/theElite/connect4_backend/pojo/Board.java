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

    public int playMove(int colIndex, BoardPosition piece) {
        for (int row = this.rows - 1; row >= 0; row--) {
            if (this.grid[row][colIndex] == BoardPosition.EMPTY.ordinal()) {
                this.grid[row][colIndex] = piece.ordinal();
                return row;
            }
        }

        return -1;
    }

    public boolean checkIfWon(int row, int col, BoardPosition piece) {
        int connectN = 4;
        int count = 0;

        // horizontal
        for (int c = 0; c < this.cols; c++) {
            if (this.grid[row][c] == piece.ordinal())
                count++;
            else
                count = 0;

            if (count == connectN)
                return true;
        }

        // vertical
        count = 0;
        for (int r = 0; r < this.rows; r++) {
            if (this.grid[r][col] == piece.ordinal())
                count++;
            else
                count = 0;

            if (count == connectN)
                return true;
        }

        // \ diagonally
        count = 0;
        for (int r = 0; r < this.rows; r++) {
            int c = row + col - r;  // checks \ diagonally
            if (c >= 0 && c < this.cols && this.grid[r][c] == piece.ordinal())
                count++;
            else
                count = 0;

            if (count == connectN)
                return true;
        }

        // / diagonally
        count = 0;
        for (int r = 0; r < this.rows; r++) {
            int c = col - row + r; // checks / diagonally
            if (c >= 0 && c < this.cols && this.grid[r][c] == piece.ordinal())
                count++;
            else
                count = 0;

            if (count == connectN)
                return true;
        }

        return false;
    }
}
