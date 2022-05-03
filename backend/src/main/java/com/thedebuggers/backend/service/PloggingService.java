package com.thedebuggers.backend.service;

import com.thedebuggers.backend.domain.entity.Plogging;
import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.dto.PloggingReqDto;

public interface PloggingService {
    Plogging registPlogging(User user, PloggingReqDto ploggingReqDto);
}
