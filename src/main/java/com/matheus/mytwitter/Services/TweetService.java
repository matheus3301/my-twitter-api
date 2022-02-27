package com.matheus.mytwitter.Services;

import com.matheus.mytwitter.Models.AppUser;
import com.matheus.mytwitter.Models.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TweetService {
    Tweet create(String username, String message);
    Page<Tweet> listAllFromUsername(String username, Pageable pageable);
}
