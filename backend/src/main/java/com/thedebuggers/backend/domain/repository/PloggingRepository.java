package com.thedebuggers.backend.domain.repository;

import com.thedebuggers.backend.domain.entity.Plogging;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PloggingRepository extends JpaRepository<Plogging, Long> {
}
