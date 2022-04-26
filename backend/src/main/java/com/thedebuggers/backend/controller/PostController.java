package com.thedebuggers.backend.controller;

import com.thedebuggers.backend.domain.entity.Community;
import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.dto.PostDto;
import com.thedebuggers.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping
    private ResponseEntity<Boolean> registPost(@RequestBody PostDto postDto) {

//        User user = UserService.getUser(postDto.getUserNo());
//        Community community = CommunityService.getCommunity(postDto.getCommunityNo());

        User user = new User();
        Community community = new Community();
        user.setNo(1);
        community.setNo(1);

        boolean result = postService.registPost(postDto, user, community);
        return ResponseEntity.ok(result);
    }
}
