package com.btagila.stockserver.service;

import com.btagila.stockserver.domain.dto.ServerHealthDto;

public interface HealthService {
    ServerHealthDto getServerHealth();
}
