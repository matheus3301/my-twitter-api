package com.matheus.mytwitter.Services;

import com.matheus.mytwitter.Models.AppUser;
import com.matheus.mytwitter.Models.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TweetService {
    Tweet get(Long id);
    Tweet create(String username, String message);
    Page<Tweet> getTimelineFromUsername(String username, int page, AppUser authenticatedUser);
    Tweet likeTweet(Long id, AppUser authenticatedUser);
    Tweet unlikeTweet(Long id, AppUser authenticatedUser);
    Boolean hasLikedTweet(Long id, AppUser authenticatedUser);
}
