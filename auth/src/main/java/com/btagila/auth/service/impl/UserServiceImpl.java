package com.btagila.auth.service.impl;

import com.btagila.auth.domain.entity.UserInfo;
import com.btagila.auth.domain.repo.UserInfoRepo;
import com.btagila.auth.resource.AuthenticationResource;
import com.btagila.auth.service.UserService;
import com.btagila.commons.domain.dto.UserRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserInfoRepo userInfoRepo;

    @Autowired
    public UserServiceImpl(UserInfoRepo userInfoRepo) {
        this.userInfoRepo = userInfoRepo;
    }

    @Override
    public void checkAuthentication(UserRequestDto requestDto) {
        LOG.info("Logging-in user: [{}]", requestDto.getUsername());
        UserInfo userInfo = userInfoRepo.findByUsername(requestDto.getUsername());
        if (userInfo == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (requestDto.getPassword() == null || !requestDto.getPassword().equals(userInfo.getPassword())) {
            throw new IllegalArgumentException("Bad credentials");
        }
    }
}
