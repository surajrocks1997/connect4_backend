package com.theElite.connect4_backend.controller;

import com.theElite.connect4_backend.dao.GameManager;
import com.theElite.connect4_backend.dao.RoomManager;
import com.theElite.connect4_backend.pojo.CustomException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@org.springframework.web.bind.annotation.RestController
@Slf4j
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class RestController {

    private RoomManager roomManager;
    private GameManager gameManager;

    @GetMapping("/generateRoomKey")
    public String generateKey() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mmssddMM");
        LocalDateTime now = LocalDateTime.now();
        log.info("Key Generated - {}", dtf.format(now));

        return dtf.format(now);
    }

    @GetMapping("/isJoinRoomAvailable/{roomKey}")
    public ResponseEntity<Boolean> joinRoomValidation(@PathVariable String roomKey) throws Exception {
        if (!this.roomManager.isRoomKeyPresent(roomKey)) {
            throw new CustomException("INVALID ROOM KEY!!!", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (this.roomManager.getConnectionsInRoom(roomKey).size() > 1) {
            throw new CustomException("ROOM IS FULL!!. Try Again with different Room Key", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/boardState")
    public ResponseEntity<int[][]> initBoard(@RequestParam String roomKey) {
        int[][] grid = this.gameManager.getBoard(roomKey).getGrid();
        log.info("GET BOARD");
        return new ResponseEntity<>(grid, HttpStatus.OK);
    }
}
