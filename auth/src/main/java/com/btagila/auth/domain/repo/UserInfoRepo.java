package com.btagila.auth.domain.repo;

import com.btagila.auth.domain.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepo extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);
}
