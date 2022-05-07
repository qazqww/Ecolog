package com.thedebuggers.backend.domain.repository;

import com.thedebuggers.backend.domain.entity.TrashCan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrashCanRepository extends JpaRepository<TrashCan, Long> {
}
