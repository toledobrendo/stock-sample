package com.btagila.stockserver.domain.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PhisixPriceDto {
    private String currency;
    private BigDecimal amount;
}
