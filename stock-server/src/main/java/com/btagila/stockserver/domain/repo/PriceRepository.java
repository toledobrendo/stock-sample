package com.btagila.stockserver.domain.repo;

import com.btagila.stockserver.domain.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
    Price getByName(String shortname);

    Price findTopByOrderByUpdatedDate();
}
