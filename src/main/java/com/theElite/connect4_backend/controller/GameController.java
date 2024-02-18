package com.theElite.connect4_backend.controller;

import com.theElite.connect4_backend.pojo.Player;
import com.theElite.connect4_backend.service.RoomManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

@Controller
public class GameController {

    @Autowired
    private RoomManager roomManager;

    @MessageMapping("/game.addUser/{key}")
    public Player addUser(@Payload Player player, @DestinationVariable String key) {
        List<WebSocketSession> list = roomManager.getConnectionsInRoom(key);
        return player;
    }
}
