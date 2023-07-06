package com.btagila.stockserver.service.impl;

import com.btagila.stockserver.domain.dto.PhisixResponseDto;
import com.btagila.stockserver.domain.dto.PhisixStockDto;
import com.btagila.stockserver.domain.entity.Price;
import com.btagila.stockserver.domain.entity.Stock;
import com.btagila.stockserver.domain.repo.PriceRepository;
import com.btagila.stockserver.domain.repo.StockRepository;
import com.btagila.stockserver.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Component
public class StockServiceImpl implements StockService {

    private static final Logger LOG = LoggerFactory.getLogger(StockServiceImpl.class);

    private WebClient phisixApiClient;
    private StockRepository stockRepository;
    private PriceRepository priceRepository;

    @Autowired
    public StockServiceImpl(WebClient phisixApiClient,
                            StockRepository stockRepository,
                            PriceRepository priceRepository) {
        this.phisixApiClient = phisixApiClient;
        this.stockRepository = stockRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    @Transactional
    public void retrieveStockData() {
        LOG.info("Starting Phisix comms");
        PhisixResponseDto response = phisixApiClient
                .get()
                .uri("/stocks.json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PhisixResponseDto.class).block();

//        PhisixResponseDto responseDto = phisixResponseMono.block();

        LOG.info("Found [{}] stocks", response);

        for (PhisixStockDto stockDto : response.getStock()) {
            Stock stock = stockRepository.findByShortname(stockDto.getSymbol());
            if (stock == null) {
                stock = new Stock();
                stock.setShortname(stockDto.getSymbol());
                stockRepository.save(stock);
            }

            Price price = priceRepository.getByName(stock.getShortname());
            if (price == null) {
                price = new Price();
                price.setName(stock.getShortname());
            }
            price.setPrice(stockDto.getPrice() != null ? stockDto.getPrice().getAmount() : null);
            price.setVolume(stockDto.getVolume());
            priceRepository.save(price);
        }
    }
}
