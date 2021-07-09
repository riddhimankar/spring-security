package com.saral.keycloak.client.controllers;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @GetMapping("/api/login")
    private String login(KeycloakPrincipal principal){
       // KeycloakPrincipal principal = (KeycloakPrincipal)request.getUserPrincipal();
        var context = (RefreshableKeycloakSecurityContext) principal
                .getKeycloakSecurityContext();
        System.out.println("accessToken: " + context.getIdTokenString());
        System.out.println("refreshToken: " + context.getRefreshToken());


        return "Success!";
    }
}
