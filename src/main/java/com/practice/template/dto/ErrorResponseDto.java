package com.practice.template.dto;

public class ErrorResponseDto {
    private String message;
    private boolean status;

    public String getMessage() {
        return message;
    }

    public ErrorResponseDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
