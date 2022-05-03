package com.thedebuggers.backend.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thedebuggers.backend.domain.entity.Plogging;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PloggingResDto {
    private long no;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String resultImg;
    private String routeImg;
    private int time;
    private double distance;
    private double calories;
    private ProfileResDto user;

    public static PloggingResDto of(Plogging plogging) {
        return PloggingResDto.builder()
                .no(plogging.getNo())
                .startedAt(plogging.getStartedAt())
                .endedAt(plogging.getEndedAt())
                .resultImg(plogging.getResultImg())
                .routeImg(plogging.getRouteImg())
                .time(plogging.getTime())
                .distance(plogging.getDistance())
                .calories(plogging.getCalories())
                .user(ProfileResDto.of(plogging.getUser()))
                .build();
    }
}
