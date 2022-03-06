package com.matheus.mytwitter.Controllers;

import com.matheus.mytwitter.DTOS.Models.AppUserDTO;
import com.matheus.mytwitter.DTOS.Models.TweetDTO;
import com.matheus.mytwitter.DTOS.Requests.CreateTweetRequestDTO;
import com.matheus.mytwitter.Models.AppUser;
import com.matheus.mytwitter.Models.Tweet;
import com.matheus.mytwitter.Services.AppUserService;
import com.matheus.mytwitter.Services.TweetService;
import com.matheus.mytwitter.Utils.ContextUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin
@RequestMapping("/api/tweets")
public class TweetController {
    private final TweetService tweetService;
    private final AppUserService appUserService;

    private final ModelMapper modelMapper;

    @Autowired
    public TweetController(TweetService tweetService, AppUserService appUserService, ModelMapper modelMapper) {
        this.tweetService = tweetService;
        this.appUserService = appUserService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<TweetDTO> create(@Valid @RequestBody CreateTweetRequestDTO createTweetRequestDTO){
        String username = ContextUtils.getAuthenticatedUsername();
        TweetDTO tweetDTO = modelMapper.map(
                tweetService.create(username, createTweetRequestDTO.getMessage()
                ), TweetDTO.class);

        return ResponseEntity.created(null).body(tweetDTO);
    }

    @PostMapping ("/{id}/like")
    public ResponseEntity<TweetDTO> likeTweet(@PathVariable Long id){
        String username = ContextUtils.getAuthenticatedUsername();
        AppUser appUser = appUserService.get(username);
        Tweet tweet = tweetService.likeTweet(id, appUser);

        return ResponseEntity.created(null).body(modelMapper.map(tweet, TweetDTO.class));
    }

    @DeleteMapping("/{id}/like")
    public ResponseEntity<TweetDTO> unlikeTweet(@PathVariable Long id){
        String username = ContextUtils.getAuthenticatedUsername();
        AppUser appUser = appUserService.get(username);
        Tweet tweet = tweetService.unlikeTweet(id, appUser);

        return ResponseEntity.created(null).body(modelMapper.map(tweet, TweetDTO.class));
    }

    @GetMapping("/{id}/likes")
    public ResponseEntity<Page<AppUserDTO>> getLikes(@PathVariable String id){

        return null;
    }
}
