package com.theElite.connect4_backend.config;

import com.theElite.connect4_backend.service.RoomManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageTemplate;
    private RoomManager roomManager;

    @EventListener
    public void handlerWebSocketConnectListener(SessionConnectEvent event) {
        String roomKey = Arrays.toString(event.getMessage().getPayload());
        log.info(event.toString());
    }
}
