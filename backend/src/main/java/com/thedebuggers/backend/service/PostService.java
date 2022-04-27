package com.thedebuggers.backend.service;

import com.thedebuggers.backend.domain.entity.Community;
import com.thedebuggers.backend.domain.entity.Post;
import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.dto.PostDto;

import java.util.List;

public interface PostService {
    boolean registPost(PostDto postDto);

    List<Post> getPostList(long communityNo);
}
