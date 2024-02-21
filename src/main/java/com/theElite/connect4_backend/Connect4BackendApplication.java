package com.theElite.connect4_backend;

import com.theElite.connect4_backend.dao.RoomManager;
import com.theElite.connect4_backend.dao.SessionMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Connect4BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Connect4BackendApplication.class, args);
    }

    @Bean
    public RoomManager roomManager() {
        return new RoomManager();
    }

    @Bean
    public SessionMapper sessionMapper() {
        return new SessionMapper();
    }


}
