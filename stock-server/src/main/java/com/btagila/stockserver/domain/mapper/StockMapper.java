package com.btagila.stockserver.domain.mapper;

import com.btagila.stockserver.domain.dto.WatchDto;
import com.btagila.stockserver.domain.entity.Watch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StockMapper {
    void updateEntity(WatchDto watchDto, @MappingTarget Watch watch);

    @Mapping(source = "stock.shortname", target = "name")
    WatchDto toDto(Watch watch);
}
