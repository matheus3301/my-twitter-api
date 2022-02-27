package com.matheus.mytwitter.Repositories;

import com.matheus.mytwitter.Models.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface TweetRepository extends PagingAndSortingRepository<Tweet, Long> {
    Page<Tweet> findAllByAuthor_Username(String username, Pageable pageable);
}
