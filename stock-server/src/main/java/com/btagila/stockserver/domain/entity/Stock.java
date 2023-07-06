package com.btagila.stockserver.domain.entity;

import com.btagila.commons.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "STOCK")
@Data
@EqualsAndHashCode(callSuper = false)
public class Stock extends BaseEntity {
    @Column(name = "SHORTNAME")
    private String shortname;
}
