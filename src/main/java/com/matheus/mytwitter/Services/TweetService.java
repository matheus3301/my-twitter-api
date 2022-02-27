package com.matheus.mytwitter.Services;

import com.matheus.mytwitter.Models.AppUser;
import com.matheus.mytwitter.Models.Tweet;

public interface TweetService {
    Tweet create(String username, String message);
}
