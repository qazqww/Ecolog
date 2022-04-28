package com.thedebuggers.backend.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thedebuggers.backend.domain.entity.LoginType;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@ApiModel("LoginResponse")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LoginReqDto {

    private long id;
    private String email;
    private String password;
    private String imageUrl;
    private LoginType loginType;
}