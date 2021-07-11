package com.saral.keycloak.client.dtos;

import org.springframework.http.HttpStatus;

public class Status {


    private int code;
    private String message;

    public Status() {

    }

    public Status(HttpStatus httpStatus) {
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


