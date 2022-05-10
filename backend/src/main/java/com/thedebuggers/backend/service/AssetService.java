package com.thedebuggers.backend.service;

import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.dto.MyAssetResDto;

public interface AssetService {
    MyAssetResDto getMyAssetList(User user);
}
