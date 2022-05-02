package com.thedebuggers.backend.domain.repository;

import com.thedebuggers.backend.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p.no, p.title, p.user, p.createdAt from Post p where p.isOpen = true")
    List<Post> findOpenPosts();

    @Query("select p.no, p.title, p.user, p.createdAt from Post p where p.community.no = :communityNo")
    List<Post> findPostsByCommunity(long communityNo);

    Optional<Post> findByNo(long postNo);

    @Modifying
    @Query("update Post p set p.title = :#{#post.title}, p.content = :#{#post.content}," +
            "p.image = :#{#post.image}, p.isOpen = :#{#post.open} where p.no = :postNo")
    void modifyPost(long postNo, Post post);

    @Modifying
    @Query("update Post p set p.likeCount = p.likeCount + 1 where p.no = :postNo")
    void updateLikePlus(long postNo);

    @Modifying
    @Query("update Post p set p.likeCount = p.likeCount - 1 where p.no = :postNo")
    void updateLikeMinus(long postNo);
}
