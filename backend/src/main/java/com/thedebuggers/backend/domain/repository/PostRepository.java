package com.thedebuggers.backend.domain.repository;

import com.thedebuggers.backend.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByTypeAndIsOpenTrue(int type);
    List<Post> findAllByCommunityNoAndType(long communityNo, int type);

    @Modifying
    @Query("update Post p set p.likeCount = p.likeCount + :count where p.no = :postNo")
    void updateLike(long postNo, int count);

    List<Post> findAllByCommunityNoAndUserNo(long communityNo, long userNo);

    List<Post> findAllByUserNoAndIsOpenTrue(long userNo);
}
