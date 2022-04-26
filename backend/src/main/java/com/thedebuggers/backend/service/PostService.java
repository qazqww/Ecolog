package com.thedebuggers.backend.service;

import com.thedebuggers.backend.domain.entity.Community;
import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.dto.PostDto;

public interface PostService {
    boolean registPost(PostDto postDto, User user, Community community);
}
