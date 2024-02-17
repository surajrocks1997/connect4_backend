package com.theElite.connect4_backend.service;

import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomManager {
    private Map<String, List<WebSocketSession>> rooms = new HashMap<>();

    public void addConnectionToRoom(String roomKey, WebSocketSession session) {
        List<WebSocketSession> connections = rooms.getOrDefault(roomKey, new ArrayList<>());
        connections.add(session);
        rooms.put(roomKey, connections);
    }

    public List<WebSocketSession> getConnectionsInRoom(String roomKey) {
        return rooms.getOrDefault(roomKey, new ArrayList<>());
    }
}
