package com.thedebuggers.backend.service;

import com.thedebuggers.backend.domain.entity.Post;
import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.dto.PostReqDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    Post registPost(User user, PostReqDto postReqDto, long communityNo, MultipartFile imageFile);

    List<Post> getAllPost();

    List<Post> getPostList(long communityNo);

    List<Post> getMyPostList(long userNo);

    List<Post> getMyPostListInCommunity(long communityNo, long userNo);

    Post getPost(long postNo);

    Post getPost(User user, long postNo) throws Exception;

    boolean modifyPost(User user, long postNo, PostReqDto postDto, MultipartFile imageFile);

    boolean deletePost(User user, long postNo);

    boolean likePost(long postNo, long userNo);

}
