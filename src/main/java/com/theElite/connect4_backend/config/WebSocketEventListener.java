package com.theElite.connect4_backend.config;

import com.theElite.connect4_backend.dao.RoomManager;
import com.theElite.connect4_backend.dao.SessionMapper;
import com.theElite.connect4_backend.pojo.MessageType;
import com.theElite.connect4_backend.pojo.Player;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private RoomManager roomManager;
    private SessionMapper sessionMapper;
    private final SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        String sessionId = event.getSessionId();

        String roomKey = sessionMapper.getRoomKey(sessionId);

        List<String> list = roomManager.getConnectionsInRoom(roomKey);
        if (list.contains(sessionId)) {
            list.remove(sessionId);
        }
        if (sessionMapper.containsSessionId(sessionId)) {
            sessionMapper.removeSession(sessionId);
        }

        log.info("DISCONNECT EVENT: Username {}", username);

        Player player = Player.builder()
                .type(MessageType.LEAVE)
                .username(username)
                .build();
        messageTemplate.convertAndSend("/topic/" + roomKey + "/key", player);

    }


}

