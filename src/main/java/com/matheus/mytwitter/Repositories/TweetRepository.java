package com.matheus.mytwitter.Repositories;

import com.matheus.mytwitter.Models.Tweet;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

public interface TweetRepository extends PagingAndSortingRepository<Tweet, Long> {
    Collection<Tweet> findAllByAuthor_Username(String username);
}
