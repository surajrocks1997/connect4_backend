package com.theElite.connect4_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Defines the WebSocket endpoint used by clients to connect to the server
        // This is the endpoint for connecting to the WebSocket
        registry.addEndpoint("/ws-connect4").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Enable a simple in-memory message broker to send messages to clients

        // Client messages with this prefix will be routed to @MessageMapping methods
        registry.setApplicationDestinationPrefixes("/app");

        // Clients will subscribe to topics prefixed with "/topic"
        registry.enableSimpleBroker("/topic");
    }
}
