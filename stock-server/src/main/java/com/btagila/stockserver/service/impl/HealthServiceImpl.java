package com.btagila.stockserver.service.impl;

import com.btagila.stockserver.domain.dto.ServerHealthDto;
import com.btagila.stockserver.domain.entity.Price;
import com.btagila.stockserver.domain.reference.ServerStatus;
import com.btagila.stockserver.domain.repo.PriceRepository;
import com.btagila.stockserver.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HealthServiceImpl implements HealthService {

    private PriceRepository priceRepository;

    @Autowired
    public HealthServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public ServerHealthDto getServerHealth() {
        Price price = priceRepository.findTopByOrderByUpdatedDate();

        ServerHealthDto healthDto = new ServerHealthDto();
        healthDto.setDate(price.getCreatedDate());
        healthDto.setStatus(ServerStatus.GOOD);

        return healthDto;
    }
}
