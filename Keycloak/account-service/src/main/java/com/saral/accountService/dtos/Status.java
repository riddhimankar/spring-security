package com.saral.accountService.dtos;

import org.springframework.http.HttpStatus;

public class Status {

    private final int code;
    private final String message;

    public Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Status(HttpStatus httpStatus){
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}


