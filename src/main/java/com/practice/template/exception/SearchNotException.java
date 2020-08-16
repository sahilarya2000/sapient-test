package com.practice.template.exception;

import org.springframework.http.HttpStatus;

public class SearchNotException extends RuntimeException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public SearchNotException() {
    }

    public SearchNotException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public SearchNotException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
