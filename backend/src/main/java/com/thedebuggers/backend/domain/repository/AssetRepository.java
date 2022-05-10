package com.thedebuggers.backend.domain.repository;

import com.thedebuggers.backend.domain.entity.UserAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssetRepository extends JpaRepository<UserAsset, Long> {

    List<Integer> findAllByUserNoAndType(long userNo, int type);
}
