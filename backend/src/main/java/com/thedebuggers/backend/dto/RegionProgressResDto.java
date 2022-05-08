package com.thedebuggers.backend.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thedebuggers.backend.domain.entity.RankingData;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RegionProgressResDto {
    private String region;
    private int cnt;
    private double dist;

    public static RegionProgressResDto of(RankingData data) {
        return RegionProgressResDto.builder()
                .region(data.getRegion())
                .cnt(data.getCnt())
                .dist(data.getDist())
                .build();
    }
}
