package com.btagila.stockserver.domain.dto;

import com.btagila.stockserver.domain.reference.ServerStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServerHealthDto {
    private ServerStatus status;
    private LocalDateTime date;
}
