package com.btagila.stockserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class AppConfig {

    @Value("${server.auth.url}")
    private String authServerUrl;

    @Value("${server.auth.key}")
    private String authKey;

    @Value("${server.stock.url}")
    private String stockServerUrl;

    @Value("${server.phisix.url}")
    private String phisixServerUrl;

    @Bean
    public WebClient authApiClient() {
        return WebClient.builder()
                .baseUrl(authServerUrl)
                .defaultHeaders(headers -> {
                    headers.set(HttpHeaders.ORIGIN, stockServerUrl);
                    headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + authKey);
                })
                .build();
    }

    @Bean
    public WebClient phisixApiClient() {
        return WebClient.builder()
                .baseUrl(phisixServerUrl)
                .defaultHeaders(headers -> {
                    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                })
                .build();
    }
}
