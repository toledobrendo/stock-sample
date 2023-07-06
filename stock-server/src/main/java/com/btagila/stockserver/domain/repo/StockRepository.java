package com.btagila.stockserver.domain.repo;

import com.btagila.stockserver.domain.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByShortname(String name);
}
