package com.btagila.auth.domain.entity;

import com.btagila.commons.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "APP")
@Data
@EqualsAndHashCode(callSuper = false)
public class App extends BaseEntity {

    @Column(name = "APP_NAME")
    private String appName;

    @Column(name = "ORIGIN_URL")
    private String originUrl;

    @Column(name = "API_KEY")
    private String apiKey;
}
