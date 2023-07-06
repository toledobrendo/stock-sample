package com.btagila.stockserver.domain.dto;

import com.btagila.stockserver.domain.reference.WatchAction;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class WatchDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal currentPrice;
    private WatchAction action;
}
