package com.saral.accountService.dtos;

public class Response <T>{

    private final Status status;
    private final T response;

    public Response(Status status, T response) {
        this.status = status;
        this.response = response;
    }

    public Status getStatus() {
        return status;
    }

    public T getResponse() {
        return response;
    }
}

