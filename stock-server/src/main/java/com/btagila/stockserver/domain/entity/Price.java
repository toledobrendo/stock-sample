package com.btagila.stockserver.domain.entity;

import com.btagila.commons.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "PRICE")
@Data
@EqualsAndHashCode(callSuper = false)
public class Price extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "VOLUME")
    private BigDecimal volume;
}
