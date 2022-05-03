package com.thedebuggers.backend.service;

import com.thedebuggers.backend.common.exception.CustomException;
import com.thedebuggers.backend.common.util.ErrorCode;
import com.thedebuggers.backend.domain.entity.Post;
import com.thedebuggers.backend.domain.entity.PostLike;
import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.domain.entity.UserCommunity;
import com.thedebuggers.backend.domain.repository.*;
import com.thedebuggers.backend.dto.PostReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;
    private final PostLikeRepository postLikeRepository;
    private final UserCommunityRepository userCommunityRepository;

    @Override
    public Post registPost(User user, PostReqDto postReqDto, long communityNo) {
        if (userCommunityRepository.findAllByCommunityNoAndUserNo(communityNo, user.getNo()) == null) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }

        Post post = Post.builder()
                .title(postReqDto.getTitle())
                .content(postReqDto.getContent())
                .image(postReqDto.getImage())
                .createdAt(LocalDateTime.now())
                .isOpen(postReqDto.isOpen())
                .community(communityRepository.findByNo(communityNo))
                .user(user)
                .build();

        post = postRepository.save(post);
        return post;
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAllByIsOpenTrue();
    }

    @Override
    public List<Post> getPostList(long communityNo) {
        try {
            List<Post> postList = postRepository.findAllByCommunityNo(communityNo);
            if (postList.size() == 0) {
                return null;
            }
            return postList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Post getPost(long postNo) {
        try {
            return postRepository.findByNo(postNo).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Post getPost(User user, long postNo) {
        Post post = postRepository.findByNo(postNo).orElse(null);
        if (!post.isOpen() && userCommunityRepository.findAllByCommunityNoAndUserNo(post.getCommunity().getNo(), user.getNo()) == null) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return post;
    }

    @Override
    public boolean modifyPost(User user, long postNo, PostReqDto postDto) {
        try {
            Post post = postRepository.findByNo(postNo).orElse(null);
            if (user.getNo() != post.getUser().getNo())
                throw new RuntimeException();

            if (postDto.getTitle() != null)
                post.setTitle(postDto.getTitle());

            if (postDto.getContent() != null)
                post.setContent(postDto.getContent());

            if (postDto.getImage() != null)
                post.setImage(postDto.getImage());

            post.setOpen(postDto.isOpen());

            postRepository.save(post);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deletePost(User user, long postNo) {
        try {
            Post post = postRepository.findByNo(postNo).orElse(null);
            if (user.getNo() != post.getUser().getNo())
                throw new RuntimeException();

            postRepository.deleteById(postNo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean likePost(long postNo, long userNo) {
        try {
            PostLike like = PostLike.builder()
                    .post(postRepository.findByNo(postNo).orElse(null))
                    .user(userRepository.findByNo(userNo).orElse(null))
                    .build();

            PostLike existLike = postLikeRepository.findByPostNoAndUserNo(postNo, userNo);

            if (existLike == null) {
                postLikeRepository.save(like);
                postRepository.updateLikePlus(postNo);
            }
            else {
                long existNo = existLike.getNo();
                postRepository.updateLikeMinus(postNo);
                postLikeRepository.deleteById(existNo);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
