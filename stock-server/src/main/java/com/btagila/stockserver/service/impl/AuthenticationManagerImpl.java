package com.btagila.stockserver.service.impl;

import com.btagila.commons.domain.dto.UserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
@Primary
public class AuthenticationManagerImpl implements AuthenticationManager {

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);

    private WebClient authApiClient;

    @Autowired
    public AuthenticationManagerImpl(WebClient authApiClient) {
        this.authApiClient = authApiClient;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserRequestDto requestDto = new UserRequestDto();
        requestDto.setUsername((String) authentication.getPrincipal());
        requestDto.setPassword((String) authentication.getCredentials());

        authApiClient
                .post()
                .uri("/authenticate")
                .body(Mono.just(requestDto), UserRequestDto.class)
                .retrieve()
                .bodyToMono(String.class)
                .block(REQUEST_TIMEOUT);
        return authentication;
    }
}
