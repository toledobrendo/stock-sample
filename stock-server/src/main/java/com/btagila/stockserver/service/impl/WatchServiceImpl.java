package com.btagila.stockserver.service.impl;

import com.btagila.stockserver.domain.dto.WatchDto;
import com.btagila.stockserver.domain.entity.Price;
import com.btagila.stockserver.domain.entity.Stock;
import com.btagila.stockserver.domain.entity.Watch;
import com.btagila.stockserver.domain.mapper.StockMapper;
import com.btagila.stockserver.domain.repo.PriceRepository;
import com.btagila.stockserver.domain.repo.StockRepository;
import com.btagila.stockserver.domain.repo.WatchRepository;
import com.btagila.stockserver.service.WatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WatchServiceImpl implements WatchService {

    private static Logger LOG = LoggerFactory.getLogger(WatchServiceImpl.class);

    private WatchRepository watchRepository;
    private StockRepository stockRepository;
    private PriceRepository priceRepository;
    private StockMapper mapper;

    @Autowired
    private WatchServiceImpl(WatchRepository watchRepository,
                             StockRepository stockRepository,
                             PriceRepository priceRepository,
                             StockMapper mapper) {
        this.watchRepository = watchRepository;
        this.stockRepository = stockRepository;
        this.priceRepository = priceRepository;
        this.mapper = mapper;
    }

    @Override
    public WatchDto saveWatch(WatchDto watchDto) {
        LOG.info("Saving watch = [{}]", watchDto.getName());
        if (watchDto.getName() == null || watchDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Stock name should not be null");
        }

        Watch watch = watchDto.getId() != null
                ? watchRepository.getReferenceById(watchDto.getId())
                : new Watch();

        mapper.updateEntity(watchDto, watch);

        Stock stock = stockRepository.findByShortname(watchDto.getName());
        if (stock == null) {
            stock = new Stock();
            stock.setShortname(watchDto.getName());
            stockRepository.save(stock);
        }
        watch.setStock(stock);

        watchRepository.save(watch);
        return mapper.toDto(watch);
    }

    @Override
    public List<WatchDto> getWatchlist() {
        List<Watch> watchList = watchRepository.findAll();
        return watchList.stream().map(watch -> {
            WatchDto watchDto = mapper.toDto(watch);

            Price price = priceRepository.getByName(watch.getStock().getShortname());
            if (price != null) {
                watchDto.setCurrentPrice(price.getPrice());
            }

            return watchDto;
        }).collect(Collectors.toList());
    }

    @Override
    public WatchDto deleteWatch(WatchDto watchDto) {
        LOG.info("Deleting watch = [{}]", watchDto.getId());
        Watch watch = watchRepository.getReferenceById(watchDto.getId());
        watchRepository.delete(watch);
        return null;
    }
}
