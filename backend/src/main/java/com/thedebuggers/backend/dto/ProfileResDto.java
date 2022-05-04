package com.thedebuggers.backend.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.thedebuggers.backend.domain.entity.LoginType;
import com.thedebuggers.backend.domain.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@SuperBuilder
@ApiModel("UserInfoResponse")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProfileResDto extends BaseUserInfoResDto{

    private boolean isFollowing;

    private List<FollowUserResDto> followingUser;
    private List<FollowUserResDto> followerUser;

    public static ProfileResDto of(User user, User requestUser){
        return ProfileResDto.builder()
                .no(user.getNo())
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .birth(user.getBirth())
                .image(user.getImage())
                .isFollowing(requestUser.getFollowingUser().contains(user))
                .followingUser(user.getFollowingUser().stream().map(u -> FollowUserResDto.of(u, requestUser)).collect(Collectors.toList()))
                .followerUser(user.getFollowerUser().stream().map(u -> FollowUserResDto.of(u, requestUser)).collect(Collectors.toList()))
                .build();
    }
}
