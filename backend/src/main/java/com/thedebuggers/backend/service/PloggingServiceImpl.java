package com.thedebuggers.backend.service;

import com.thedebuggers.backend.domain.entity.Plogging;
import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.domain.repository.PloggingRepository;
import com.thedebuggers.backend.dto.PloggingReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PloggingServiceImpl implements PloggingService {

    private final PloggingRepository ploggingRepository;

    @Override
    public Plogging registPlogging(User user, PloggingReqDto ploggingReqDto) {
        Plogging plogging = Plogging.builder()
                .startedAt(ploggingReqDto.getStartedAt())
                .endedAt(ploggingReqDto.getEndedAt())
                .resultImg(ploggingReqDto.getResultImg())
                .routeImg(ploggingReqDto.getRouteImg())
                .time(ploggingReqDto.getTime())
                .distance(ploggingReqDto.getDistance())
                .calories(ploggingReqDto.getCalories())
                .user(user)
                .build();

        plogging = ploggingRepository.save(plogging);
        return plogging;
    }
}
