package com.thedebuggers.backend.domain.entity;

import com.thedebuggers.backend.dto.UserUpdateReqDto;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long no;

    private String email;

    private String password;

    private String name;

    private String nickname;

    private String birth;

    private double height;

    private double weight;

    private String phone;

    private String image;

    private String address;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @OneToMany(mappedBy = "follower", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserFollow> following = new ArrayList<>();
    @OneToMany(mappedBy = "followee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserFollow> follower = new ArrayList<>();

    public List<User> getFollowingUser(){
        return this.following.stream().map(UserFollow::getFollowee).collect(Collectors.toList());
    }
    public List<User> getFollowerUser(){
        return this.follower.stream().map(UserFollow::getFollower).collect(Collectors.toList());
    }

    public void addFollowing(UserFollow following){
        this.following.add(following);
    }

    public void addFollower(UserFollow follower){
        this.following.add(follower);
    }

}
