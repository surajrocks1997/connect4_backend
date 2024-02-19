package com.theElite.connect4_backend.config;

import com.theElite.connect4_backend.pojo.MessageType;
import com.theElite.connect4_backend.pojo.Player;
import com.theElite.connect4_backend.service.RoomManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageTemplate;
    private RoomManager roomManager;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            log.info("User Disconnected: {}", username);
            Player player = Player.builder()
                    .type(MessageType.LEAVE)
                    .username(username)
                    .build();

            messageTemplate.convertAndSend("/topic/public", player);
        }
    }

//    @EventListener
//    public void handlerWebSocketConnectListener(SessionConnectEvent event) {
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        String username = (String) headerAccessor.getSessionAttributes().get("username");
//        log.info("CONNECT EVENT - USERNAME : {}", username);
//        String roomKey = Arrays.toString(event.getMessage().getPayload());
//        log.info("CONNECT EVENT: {}", event.toString());
//        log.info("CONNECT EVENT: RoomKey - {}", roomKey);
//}

//    @EventListener
//    public void handlerWebSocketConnectListener(SessionConnectedEvent event) {
//        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
//        String username = (String) headerAccessor.getSessionAttributes().get("username");
//        log.info("CONNECTED EVENT : {}", event);
//    }

}

