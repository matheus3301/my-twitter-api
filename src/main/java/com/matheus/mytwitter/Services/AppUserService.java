package com.matheus.mytwitter.Services;

import com.matheus.mytwitter.Models.AppUser;
import com.matheus.mytwitter.Models.Follow;
import org.postgresql.util.PSQLException;
import org.springframework.data.domain.Page;

import java.util.Collection;

public interface AppUserService {
    AppUser signUp(
            String username,
            String name,
            String email,
            String password,
            String confirmPassword
    );
    AppUser updateProfile(String username, AppUser appUser);
    AppUser get(String username);
    AppUser get(Long id);
    void delete(String username);
    AppUser signIn(String username, String password);
    Follow startFollowing(String toFollowUsername, AppUser appUser);
    void stopFollowing(String toUnfollowUsername, AppUser appUser);
    Page<Follow> getFollowers(String username, Integer page);
    Page<Follow> getFollowing(String username, Integer page);
    Long getFollowersCount(String username);
    Long getFollowingCount(String username);
    Boolean isFollowing(String followed, String follower);
}
