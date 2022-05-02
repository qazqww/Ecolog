package com.thedebuggers.backend.service;

import com.thedebuggers.backend.domain.entity.Post;
import com.thedebuggers.backend.domain.entity.PostLike;
import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.domain.repository.CommunityRepository;
import com.thedebuggers.backend.domain.repository.PostLikeRepository;
import com.thedebuggers.backend.domain.repository.PostRepository;
import com.thedebuggers.backend.domain.repository.UserRepository;
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

    @Override
    public Post registPost(PostReqDto postReqDto, long communityNo) {
        try {
            Post post = Post.builder()
                    .title(postReqDto.getTitle())
                    .content(postReqDto.getContent())
                    .image(postReqDto.getImage())
                    .createdAt(LocalDateTime.now())
                    .isOpen(postReqDto.isOpen())
                    .community(communityRepository.findByNo(communityNo))
                    .user(userRepository.findByNo(postReqDto.getUserNo()).orElse(null))
                    .build();

            post = postRepository.save(post);
            return post;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Post> getAllPost() {
        try {
            return postRepository.findOpenPosts();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Post> getPostList(long communityNo) {
        try {
            List<Post> postList = postRepository.findPostsByCommunity(communityNo);
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

//    @Override
//    public Post getPost(User user, long communityNo, long postNo) {
//        try {
//            if (userCommunityRepository.findAllByCommunityNoAndUserNo(communityNo, user.getNo()) == null) {
//                // 커뮤니티 미가입으로 권한 없음
//            }
//            return postRepository.findByNo(postNo).orElse(null);
//        } catch (Exception e) {
//            return null;
//        }
//    }

    @Override
    @Transactional
    public boolean modifyPost(long postNo, PostReqDto postDto) {
        try {
            Post post = Post.builder()
                    .title(postDto.getTitle())
                    .content(postDto.getContent())
                    .image(postDto.getImage())
                    .isOpen(postDto.isOpen())
                    .build();

            postRepository.modifyPost(postNo, post);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deletePost(long postNo) {
        try {
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
            }
            else {
                long existNo = existLike.getNo();
                postLikeRepository.deleteById(existNo);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
