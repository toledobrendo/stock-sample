package com.btagila.stockserver.service;

import com.btagila.stockserver.domain.dto.WatchDto;

import java.util.List;

public interface WatchService {
    WatchDto saveWatch(WatchDto watchDto);

    List<WatchDto> getWatchlist();

    WatchDto deleteWatch(WatchDto watchDto);
}
