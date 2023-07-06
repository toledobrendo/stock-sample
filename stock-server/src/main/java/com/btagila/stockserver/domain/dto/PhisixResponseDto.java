package com.btagila.stockserver.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class PhisixResponseDto {
    private List<PhisixStockDto> stock;
}
