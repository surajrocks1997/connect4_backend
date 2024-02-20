package com.theElite.connect4_backend.dao;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RoomManager {
    private Map<String, List<String>> rooms = new HashMap<>();

    public void addConnectionToRoom(String roomKey, String username) {
        List<String> connections = rooms.getOrDefault(roomKey, new ArrayList<>());
        connections.add(username);
        rooms.put(roomKey, connections);
    }

    public List<String> getConnectionsInRoom(String roomKey) {
        return rooms.getOrDefault(roomKey, new ArrayList<>());
    }

    public void deleteRoom(String roomKey) {
        rooms.remove(roomKey);
    }
}
