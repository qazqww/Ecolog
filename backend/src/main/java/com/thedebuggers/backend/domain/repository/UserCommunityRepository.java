package com.thedebuggers.backend.domain.repository;

import com.thedebuggers.backend.domain.entity.User;
import com.thedebuggers.backend.domain.entity.UserCommunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCommunityRepository extends JpaRepository<UserCommunity, Long> {
    @Query("select uc.user from UserCommunity uc where uc.community.no = :communityNo")
    List<User> findAllUserByCommunityNo(long communityNo);


    List<UserCommunity> findAllByCommunityNo(long communityNo);
}