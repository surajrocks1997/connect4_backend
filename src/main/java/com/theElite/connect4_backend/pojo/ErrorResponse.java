package com.theElite.connect4_backend.pojo;

import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String timeStamp;
    private String errorMessage;
    private HttpStatusCode statusCode;

    public ErrorResponse(String errorMessage, HttpStatusCode statusCode) {
        this.timeStamp = LocalDateTime.now().toString();
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setStatusCode(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
