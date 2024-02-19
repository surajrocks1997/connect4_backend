package com.theElite.connect4_backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@Slf4j
@CrossOrigin("http://localhost:3000")
public class RestEndpoints {

    @GetMapping("/generateRoomKey")
    public String generateKey() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("mmssddMM");
        LocalDateTime now = LocalDateTime.now();
        log.info("Key Generated - {}", dtf.format(now));

        return dtf.format(now);
    }
}
