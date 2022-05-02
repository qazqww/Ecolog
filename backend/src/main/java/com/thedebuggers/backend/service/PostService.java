package com.thedebuggers.backend.service;

import com.thedebuggers.backend.domain.entity.Post;
import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.dto.PostReqDto;

import java.util.List;

public interface PostService {
    Post registPost(PostReqDto postReqDto, long communityNo);

    List<Post> getAllPost();

    List<Post> getPostList(long communityNo);

    Post getPost(long postNo);

    boolean modifyPost(long postNo, PostReqDto postDto);

    boolean deletePost(long postNo);

    boolean likePost(long postNo, long userNo);
}
