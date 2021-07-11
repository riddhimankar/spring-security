package com.saral.keycloak.client.dtos;

public class Request {


    private final String accessToken;

    public Request(String accessToken) {
        this.accessToken = accessToken;
    }


    public String getAccessToken() {
        return accessToken;
    }

}
