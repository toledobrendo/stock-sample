package com.btagila.stockserver.resource;

import com.btagila.stockserver.domain.dto.ServerHealthDto;
import com.btagila.stockserver.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HealthResource {
    @Autowired
    private HealthService healthService;

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public ResponseEntity<ServerHealthDto> getServerHealth() {
        return ResponseEntity.of(Optional.of(healthService.getServerHealth()));
    }
}
