package com.btagila.stockserver.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PhisixStockDto {
    private String name;
    private PhisixPriceDto price;
    private BigDecimal percentChange;
    private BigDecimal volume;
    private String symbol;
}
