package com.saral.keycloak.client.utils;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ClientBuilder {

    public WebClient getClient(String baseUri) {
        WebClient.Builder builder = WebClient.builder();

        builder.baseUrl(baseUri).clientConnector(new ReactorClientHttpConnector(httpClient()));
        return builder.build();
    }

    private HttpClient httpClient() {
        return HttpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(
                        conn -> conn.addHandlerLast(
                                        new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(
                                        new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                );
    }
}
