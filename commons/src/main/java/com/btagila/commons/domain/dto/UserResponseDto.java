package com.btagila.commons.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class UserResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String jwtToken;
}