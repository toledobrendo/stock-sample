package com.btagila.stockserver.scheduler;

import com.btagila.stockserver.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StockJobScheduler {

    private StockService stockService;

    @Autowired
    public StockJobScheduler(StockService stockService) {
        this.stockService = stockService;
    }

    @Scheduled(fixedDelay = 60000)
    public void getStockData() {
        this.stockService.retrieveStockData();
    }
}
