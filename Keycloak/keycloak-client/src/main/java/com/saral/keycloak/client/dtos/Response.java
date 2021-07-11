package com.saral.keycloak.client.dtos;

public class Response<T>{

    private Status status;
    private T response;

    public Response(){

    }

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

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}

