package com.btagila.auth.domain.repo;

import com.btagila.auth.domain.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepo extends JpaRepository<App, Long> {
    App findByOriginUrl(String originHeader);
}
