package com.saral.keycloak.client.services;

import com.saral.keycloak.client.configs.AccountsServiceConfig;
import com.saral.keycloak.client.dtos.AccountDto;
import com.saral.keycloak.client.dtos.Request;
import com.saral.keycloak.client.dtos.Response;
import com.saral.keycloak.client.dtos.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class DashboardBuilderService extends ResponseService<Request, Response<List<AccountDto>>> {

    private static final Logger logger = LoggerFactory.getLogger(DashboardBuilderService.class);

    @Autowired
    AccountsServiceConfig accountsServiceConfig;

    @Autowired
    private WebClient webClient;

    @Override
    public Response<List<AccountDto>> handle(Request rq) throws Exception {

        Response<List<AccountDto>> response = webClient
                .get()
                .uri(accountsServiceConfig.getAccountsApi())
                .header(HttpHeaders.AUTHORIZATION, "bearer " + rq.getAccessToken())
                .retrieve()
                .bodyToMono(Response.class)
                .block();

        List<AccountDto> accounts = response.getResponse();
        logger.info("accounts -> {}", accounts);
        return new Response<>(new Status(HttpStatus.OK), response.getResponse());
    }

    @Override
    public Response<List<AccountDto>> handleException(Exception ex) {
        ex.printStackTrace();
        return new Response<>(new Status(HttpStatus.INTERNAL_SERVER_ERROR), null);
    }
}
