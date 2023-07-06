package com.btagila.auth.config;

import com.btagila.auth.domain.entity.App;
import com.btagila.auth.domain.repo.AppRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiKeyFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(ApiKeyFilter.class);

    private AppRepo appRepo;

    public ApiKeyFilter(AppRepo appRepo) {
        this.appRepo = appRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String originHeader = request.getHeader(HttpHeaders.ORIGIN);

        LOG.info("Found origin [{}]", originHeader);

        App app = appRepo.findByOriginUrl(originHeader);

        if (app == null) {
            throw new ServletException("App origin not recognized");
        }

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null) {
            throw new ServletException("Invalid API key");
        }

        String apiKey = authHeader.replace("Bearer ", "");
        if (app.getApiKey() == null || !app.getApiKey().equals(apiKey)) {
            throw new ServletException("Invalid API key");
        }

        filterChain.doFilter(request, response);
    }
}
