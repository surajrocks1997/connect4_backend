package com.theElite.connect4_backend.controller;

import com.theElite.connect4_backend.pojo.MessageType;
import com.theElite.connect4_backend.pojo.Player;
import com.theElite.connect4_backend.dao.RoomManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GameController {
    private RoomManager roomManager;
    private final SimpMessageSendingOperations messageTemplate;

    @MessageMapping("/game.addUser/{key}")
    @SendTo("/topic/{key}/key")
    public Player addUser(@Payload Player player, @DestinationVariable String key,
                          SimpMessageHeaderAccessor headerAccessor) {

        log.info("GAME.ADD_USER : {}", player.getUsername());
        headerAccessor.getSessionAttributes().put("username", player.getUsername());

//        List<String> list = roomManager.getConnectionsInRoom(key);
//        list.add(player.getUsername());

        return player;
    }

    @MessageMapping("/game.removeUser/{key}")
    @SendTo("/topic/{key}/key")
    public Player removeUser(@Payload Player player, @DestinationVariable String key) {

        String username = player.getUsername();
        log.info("GAME.REMOVE_USER : {} Disconnected", username);
//        List<String> list = roomManager.getConnectionsInRoom(key);
//        list.remove(username);
//        if (list.isEmpty()) {
//            roomManager.deleteRoom(key);
//            log.info("REMOVE USER: Room {} Deleted", key);
//        }

        log.info("REMOVE USER : User Disconnected: {}", username);
        Player myPlayer = Player.builder()
                .type(MessageType.LEAVE)
                .username(username)
                .build();

        messageTemplate.convertAndSend("/topic/" + key + "/key", myPlayer);
        return myPlayer;
    }
}
