package com.matheus.mytwitter.Controllers;

import com.matheus.mytwitter.DTOS.Models.TweetDTO;
import com.matheus.mytwitter.DTOS.Requests.CreateTweetRequestDTO;
import com.matheus.mytwitter.Models.Tweet;
import com.matheus.mytwitter.Services.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/tweet")
public class TweetController {
    private final TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping
    public ResponseEntity<TweetDTO> create(@Valid @RequestBody CreateTweetRequestDTO createTweetRequestDTO){
        String username = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        TweetDTO tweetDTO = Tweet.toDTO(
                tweetService.create(username, createTweetRequestDTO.getMessage())
        );

        return new ResponseEntity<>(tweetDTO, HttpStatus.CREATED);
    }
}
