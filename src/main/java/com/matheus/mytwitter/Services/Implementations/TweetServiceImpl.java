package com.matheus.mytwitter.Services.Implementations;

import com.matheus.mytwitter.Constants.Constants;
import com.matheus.mytwitter.Exceptions.NotFollowingException;
import com.matheus.mytwitter.Models.AppUser;
import com.matheus.mytwitter.Models.Tweet;
import com.matheus.mytwitter.Repositories.TweetRepository;
import com.matheus.mytwitter.Services.AppUserService;
import com.matheus.mytwitter.Services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class TweetServiceImpl implements TweetService {
    private final TweetRepository tweetRepository;

    private final AppUserService appUserService;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository, AppUserService appUserService) {
        this.tweetRepository = tweetRepository;
        this.appUserService = appUserService;
    }

    @Override
    public Tweet create(String username, String message) {
        AppUser appUser = appUserService.get(username);
        Tweet tweet = new Tweet();

        tweet.setAuthor(appUser);
        tweet.setMessage(message);

        return tweetRepository.save(tweet);
    }

    @Override
    public Page<Tweet> getTimelineFromUsername(String username, int page, AppUser authenticatedUser) {
        AppUser profile = appUserService.get(username);
        if(profile.isPrivate() && !appUserService.isFollowing(username, authenticatedUser.getUsername()) && !username.equals(authenticatedUser.getUsername()))
            throw new NotFollowingException();

        PageRequest pageRequest = PageRequest.of(page, Constants.DEFAULT_PAGE_SIZE).withSort(Sort.Direction.DESC, "createdAt");

        return tweetRepository.findAllByAuthor_Username(username, pageRequest);
    }

}
