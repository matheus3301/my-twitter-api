package com.matheus.mytwitter.Repositories;

import com.matheus.mytwitter.Models.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

public interface FollowRepository extends PagingAndSortingRepository<Follow, Long> {
    Optional<Follow> findByFollowed_UsernameAndFollower_Username(String followedUsername, String followerUsername);
    Page<Follow> findAllByFollowed_Username(String username, Pageable pageable);
    Page<Follow> findAllByFollower_Username(String username, Pageable pageable);
    Long countAllByFollowed_Username(String username);
    Long countAllByFollower_Username(String username);
}
