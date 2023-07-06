package com.btagila.stockserver.domain.repo;

import com.btagila.stockserver.domain.entity.Watch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchRepository extends JpaRepository<Watch, Long> {
}
