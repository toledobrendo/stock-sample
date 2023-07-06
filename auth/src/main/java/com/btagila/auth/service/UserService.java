package com.btagila.auth.service;

import com.btagila.commons.domain.dto.UserRequestDto;

public interface UserService {
    void checkAuthentication(UserRequestDto requestDto);
}
