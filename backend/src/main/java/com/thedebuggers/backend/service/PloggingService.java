package com.thedebuggers.backend.service;

import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.dto.PloggingReqDto;
import com.thedebuggers.backend.dto.PloggingResDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PloggingService {
    PloggingResDto registPlogging(User user, PloggingReqDto ploggingReqDto, MultipartFile resultImg, MultipartFile routeImg);

    List<PloggingResDto> getPloggingList(long userNo);

    PloggingResDto getPlogging(long ploggingNo);
}
