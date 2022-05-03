package com.thedebuggers.backend.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thedebuggers.backend.domain.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class PloggingReqDto {
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String resultImg;
    private String routeImg;
    private int time;
    private double distance;
    private double calories;
    private User user;
}
