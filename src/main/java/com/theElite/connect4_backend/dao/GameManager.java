package com.theElite.connect4_backend.dao;

import com.theElite.connect4_backend.pojo.Board;
import com.theElite.connect4_backend.pojo.BoardPosition;

import java.util.HashMap;
import java.util.Map;

public class GameManager {

    private Map<String, Board> game;

    public GameManager() {
        this.game = new HashMap<>();
    }

    public void mapBoardToRoom(String roomKey, Board board) {
        game.put(roomKey, board);
    }

    public Board getBoard(String roomKey) {
        return game.get(roomKey);
    }

    public boolean playMove(int colIndex, int moveIdentifier, String key) {
        Board board = game.get(key);
        int rowIndex = board.playMove(colIndex, BoardPosition.values()[moveIdentifier]);
        return board.checkIfWon(rowIndex, colIndex, BoardPosition.values()[moveIdentifier]);
    }

    public void deleteGame(String roomKey) {
        this.game.remove(roomKey);
    }
}
