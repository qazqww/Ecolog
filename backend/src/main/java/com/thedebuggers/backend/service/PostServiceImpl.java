package com.thedebuggers.backend.service;

import com.thedebuggers.backend.domain.entity.Community;
import com.thedebuggers.backend.domain.entity.Post;
import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.domain.repository.PostRepository;
import com.thedebuggers.backend.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public boolean registPost(PostDto postDto, User user, Community community) {
        Post post = Post.builder()
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .image(postDto.getImage())
                .createdAt(LocalDateTime.now())
                .isOpen(postDto.isOpen())
                .community(community)
                .user(user)
                .build();

        post = postRepository.save(post);
        return true;
    }

    @Override
    public List<Post> getPostList(long communityNo) {
        List<Post> postList = postRepository.findByCommunityNo(communityNo);
        return postList;
    }

}
