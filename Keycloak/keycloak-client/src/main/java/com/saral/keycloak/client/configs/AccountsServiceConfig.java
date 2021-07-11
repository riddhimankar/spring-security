package com.saral.keycloak.client.configs;

import com.saral.keycloak.client.utils.ClientBuilder;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class AccountsServiceConfig {

    @Value("${service.accounts.baseUri}")
    private String accountsBaseUri;

    @Value("${service.accounts.api}")
    private String accountsApi;

    @Bean
    public WebClient getClient() {
        var clientBuilder = new ClientBuilder();
        return clientBuilder.getClient(accountsBaseUri);
    }

    public String getAccountsApi() {
        return accountsApi;
    }
}
