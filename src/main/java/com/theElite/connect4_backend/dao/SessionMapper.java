package com.theElite.connect4_backend.dao;

import java.util.HashMap;
import java.util.Map;


public class SessionMapper {

    private Map<String, String> sessionMap;

    public SessionMapper() {
        sessionMap = new HashMap<>();
    }

    public void mapSessionToRoom(String sessionId, String roomKey) {
        this.sessionMap.put(sessionId, roomKey);
    }

    public void removeSession(String sessionId) {
        this.sessionMap.remove(sessionId);
    }

    public String getRoomKey(String sessionId) {
        return this.sessionMap.get(sessionId);
    }

    public boolean containsSessionId(String sessionId) {
        return sessionMap.containsKey(sessionId);
    }

    public Map<String, String> getAllActiveSessions() {
        return sessionMap;
    }
}
