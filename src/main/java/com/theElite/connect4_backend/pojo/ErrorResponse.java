package com.theElite.connect4_backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String timeStamp;
    private String errorMessage;
    private HttpStatusCode statusCode;

    public ErrorResponse(String errorMessage, HttpStatusCode statusCode) {
        this.timeStamp = LocalDateTime.now().toString();
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

}
