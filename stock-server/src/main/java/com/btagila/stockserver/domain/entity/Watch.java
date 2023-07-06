package com.btagila.stockserver.domain.entity;

import com.btagila.commons.domain.entity.BaseEntity;
import com.btagila.stockserver.domain.reference.WatchAction;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "WATCH")
@Data
@EqualsAndHashCode(callSuper = false)
public class Watch extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "STOCK_ID")
    private Stock stock;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "ACTION")
    private WatchAction action;
}
