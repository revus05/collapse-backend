package com.example.collapse.dto.response;

public record ResponseBody(int status, String message, Object data) {
    public ResponseBody(int status, String message) {
        this(status, message, null);
    }
}
