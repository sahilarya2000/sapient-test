package com.practice.template.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponseEntity<T> {
    private final HttpStatus statusCode;
    private final String message;
    private T data;
    private boolean status = Boolean.TRUE;

    public MessageResponseEntity(T data) {
        this(data, HttpStatus.OK);
    }

    public MessageResponseEntity(T data, HttpStatus statusCode) {
        this(statusCode, "success");
        this.data = data;
    }

    public MessageResponseEntity(T data, HttpStatus statusCode, String message) {
        this(statusCode, message);
        this.data = data;
    }

    public MessageResponseEntity(HttpStatus statusCode, String message) {
        if (message == null) {
            message = "wrong";
        }
        this.statusCode = statusCode;
        this.message = message;
    }

    public MessageResponseEntity(HttpStatus statusCode, String message, Boolean status) {
        this(statusCode, message);
        this.status = status;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public boolean isStatus() {
        return status;
    }
}
