package com.saral.keycloak.client.controllers;

import com.saral.keycloak.client.dtos.AccountDto;
import com.saral.keycloak.client.dtos.Request;
import com.saral.keycloak.client.dtos.Response;
import com.saral.keycloak.client.services.DashboardBuilderService;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * This service demonstrates how to obtain access tokens from keycloak server.
 * and use the same to call secured APIs.
 *
 */

@RestController
public class LoginController {

    @Autowired
    DashboardBuilderService dashboardBuilderService;

    /**
     *
     * This API is extracting access token from KeycloakPrinciple and passing it as
     * authorization bearer token while calling accounts service.
     *
     * @param keyCloakPrincipal
     * @return
     */
    @GetMapping("/v1/dashboard")
    private ResponseEntity<Response<List<AccountDto>>> login(KeycloakPrincipal keyCloakPrincipal) {
        var context = (RefreshableKeycloakSecurityContext) keyCloakPrincipal
                .getKeycloakSecurityContext();
        Response<List<AccountDto>> response = dashboardBuilderService.handleResponse(
                new Request(context.getIdTokenString()));
        return new ResponseEntity<Response<List<AccountDto>>>( response, HttpStatus.OK);
    }

//    private TokenInfoDto buildTokenInfo(RefreshableKeycloakSecurityContext context) {
//        TokenInfoDto.Builder builder = TokenInfoDto.Builder.newInstance();
//        builder.setAccessToken(context.getIdTokenString());
//        builder.setExpiresIn(context.getToken().getExp());
//        builder.setRefreshToken(context.getRefreshToken());
//        builder.setAudience(context.getToken().getAudience());
//        builder.setIssuedFor(context.getToken().getIssuedFor());
//        builder.setScope(context.getToken().getScope());
//        builder.setUsername(context.getToken().getPreferredUsername());
//        builder.setAuthTime(context.getToken().getAuth_time());
//        //builder.setNotBeforePolicy(context.getToken().getNbf());
//        builder.setTokenType(context.getToken().getType());
//        builder.setSessionState(context.getIdToken().getSessionState());
//
//        return builder.build();
//    }
}
