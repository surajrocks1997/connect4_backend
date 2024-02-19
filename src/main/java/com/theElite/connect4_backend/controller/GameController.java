package com.theElite.connect4_backend.controller;

import com.theElite.connect4_backend.pojo.Player;
import com.theElite.connect4_backend.service.RoomManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

@Controller
@Slf4j
public class GameController {

    @Autowired
    private RoomManager roomManager;

    @MessageMapping("/game.addUser/{key}")
    @SendTo("/topic/{key}/key")
    public Player addUser(@Payload Player player, @DestinationVariable String key,
                          SimpMessageHeaderAccessor headerAccessor) {

        log.info("from addUser");
        log.info(player.getUsername());
        headerAccessor.getSessionAttributes().put("username", player.getUsername());
        List<WebSocketSession> list = roomManager.getConnectionsInRoom(key);
        return player;
    }
}
