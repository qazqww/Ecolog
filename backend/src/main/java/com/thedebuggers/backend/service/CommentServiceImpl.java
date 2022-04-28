package com.thedebuggers.backend.service;

import com.thedebuggers.backend.domain.entity.Comment;
import com.thedebuggers.backend.domain.entity.Post;
import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.domain.repository.CommentRepository;
import com.thedebuggers.backend.dto.CommentReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final PostService postService;

    @Override
    public List<Comment> getCommentList(long postNo) {
        return commentRepository.findByPostNo(postNo);
    }

    @Override
    public void registComment(long postNo, User user, CommentReqDto commentDto) throws Exception {
        Post post = postService.getPost(postNo);

        if (post == null) throw new Exception();

        Comment comment = Comment.builder()
                .post(post)
                .user(user)
                .content(commentDto.getContent())
                .build();

        commentRepository.save(comment);
    }
}
