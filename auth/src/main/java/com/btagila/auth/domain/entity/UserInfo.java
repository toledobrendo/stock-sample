package com.btagila.auth.domain.entity;

import com.btagila.commons.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "USER_INFO")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInfo extends BaseEntity {

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;
}
