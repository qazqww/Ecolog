package com.thedebuggers.backend.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Plogging {

    @Id
    private long no;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String resultImg;
    private String routeImg;
    private int time;
    private double distance;
    private double calories;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;
}
