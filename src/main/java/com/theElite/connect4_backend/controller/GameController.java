package com.theElite.connect4_backend.controller;

import com.theElite.connect4_backend.dao.RoomManager;
import com.theElite.connect4_backend.dao.SessionMapper;
import com.theElite.connect4_backend.pojo.Player;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@Slf4j
public class GameController {
    private RoomManager roomManager;
    private SessionMapper sessionMapper;

    @MessageMapping("/game.addUser/{key}")
    @SendTo("/topic/{key}/key")
    public Player addUser(@Payload Player player, @DestinationVariable String key, SimpMessageHeaderAccessor headerAccessor) {

        String sessionId = headerAccessor.getSessionId();
        String username = player.getUsername();

        headerAccessor.getSessionAttributes().put("username", username);

        roomManager.addConnectionToRoom(key, sessionId);
        sessionMapper.mapSessionToRoom(sessionId, key);

        log.info("GAME.ADD_USER : {}, Session ID: {}", username, sessionId);
        log.info("Connections in Room: {}", roomManager.getConnectionsInRoom(key).toString());

        for (Map.Entry<String, String> entrySet : sessionMapper.getAllActiveSessions().entrySet()) {
            log.info("SessionId: {}, RoomKey: {}", entrySet.getKey(), entrySet.getValue());
        }
        return player;
    }

    @MessageMapping("/game.removeUser/{key}")
    @SendTo("/topic/{key}/key")
    public Player removeUser(@Payload Player player, @DestinationVariable String key, SimpMessageHeaderAccessor headerAccessor) {

        String sessionId = headerAccessor.getSessionId();
        String username = player.getUsername();

        List<String> list = roomManager.getConnectionsInRoom(key);

        if (list.contains(sessionId)) {
            list.remove(sessionId);
        }
        if (sessionMapper.containsSessionId(sessionId)) {
            sessionMapper.removeSession(sessionId);
        }

        log.info("GAME.REMOVE_USER: {} Disconnected", username);

        if (list.isEmpty()) {
            roomManager.deleteRoom(key);
            log.info("GAME.REMOVE_USER: Room {} Deleted", key);
        }
        return player;
    }

    @MessageMapping("/game.startGame/{key}")
    @SendTo("/topic/{key}/key")
    public Player startGame(@Payload Player player, @DestinationVariable String key){
        return player;
    }
}
