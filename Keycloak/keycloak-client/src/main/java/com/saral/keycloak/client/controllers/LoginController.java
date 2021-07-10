package com.saral.keycloak.client.controllers;

import com.saral.keycloak.client.dtos.TokenInfoDto;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @GetMapping("/v1/dashboard")
    private TokenInfoDto login(KeycloakPrincipal principal){
       // KeycloakPrincipal principal = (KeycloakPrincipal)request.getUserPrincipal();
        var context = (RefreshableKeycloakSecurityContext) principal
                .getKeycloakSecurityContext();
        System.out.println("access_token:  " + context.getIdTokenString());
        System.out.println("expires_in:  " + context.getToken().getExp());
        //System.out.println("refresh_expires_in:  " + context.getIdToken().get);
        System.out.println("refresh_token:  " + context.getRefreshToken());
        System.out.println("token_type:  " + context.getToken().getType());
        System.out.println("not_before_policy:  " + context.getToken().getNbf());
        System.out.println("session_state: " + context.getIdToken().getSessionState());
        System.out.println("scope:  " + context.getToken().getScope());

        System.out.println("audience:     " + context.getToken().getAudience());
        System.out.println("auth_time:    " + context.getToken().getAuth_time());
        System.out.println("issued_for:   " + context.getToken().getIssuedFor());
        System.out.println("user_name:    " + context.getToken().getPreferredUsername());

        TokenInfoDto.Builder builder = TokenInfoDto.Builder.newInstance();
        builder.setAccessToken(context.getIdTokenString());
        builder.setExpiresIn(context.getToken().getExp());
        builder.setRefreshToken(context.getRefreshToken());
        builder.setAudience(context.getToken().getAudience());
        builder.setIssuedFor(context.getToken().getIssuedFor());
        builder.setScope(context.getToken().getScope());
        builder.setUsername(context.getToken().getPreferredUsername());
        builder.setAuthTime(context.getToken().getAuth_time());
        //builder.setNotBeforePolicy(context.getToken().getNbf());
        builder.setTokenType(context.getToken().getType());
        builder.setSessionState(context.getIdToken().getSessionState());


        System.out.println(builder);

        TokenInfoDto.Builder builder2 = TokenInfoDto.Builder.newInstance();
        System.out.println(builder2);

        return builder.build();
    }
}
