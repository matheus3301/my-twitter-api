package com.matheus.mytwitter.Controllers;

import com.matheus.mytwitter.DTOS.Models.AppUserDTO;
import com.matheus.mytwitter.DTOS.Models.TweetDTO;
import com.matheus.mytwitter.DTOS.Requests.UpdateProfileRequestDTO;
import com.matheus.mytwitter.Models.AppUser;
import com.matheus.mytwitter.Models.Follow;
import com.matheus.mytwitter.Models.Tweet;
import com.matheus.mytwitter.Services.AppUserService;
import com.matheus.mytwitter.Services.TweetService;
import com.matheus.mytwitter.Utils.ContextUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final AppUserService appUserService;
    private final TweetService tweetService;

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(AppUserService appUserService, TweetService tweetService, ModelMapper modelMapper) {
        this.appUserService = appUserService;
        this.tweetService = tweetService;
        this.modelMapper = modelMapper;
    }

    @PutMapping("/me")
    public ResponseEntity<AppUserDTO> updateMyProfile(@Valid @RequestBody UpdateProfileRequestDTO updateProfileRequestDTO){
        AppUser appUser = new AppUser();
        String username = ContextUtils.getAuthenticatedUsername();


        appUser.setEmail(updateProfileRequestDTO.getEmail());
        appUser.setName(updateProfileRequestDTO.getName());
        appUser.setBiography(updateProfileRequestDTO.getBiography());
        appUser.setAvatarUrl(updateProfileRequestDTO.getAvatarUrl());
        appUser.setPrivate(updateProfileRequestDTO.isPrivate());

        AppUser updatedAppUser = appUserService.updateProfile(username, appUser);
        AppUserDTO updatedAppUserDTO = modelMapper.map(updatedAppUser, AppUserDTO.class);

        return ResponseEntity.ok(updatedAppUserDTO);
    }

    @GetMapping("/{username}")
    public ResponseEntity<AppUserDTO> getProfile(@PathVariable String username){
        AppUser appUser = appUserService.get(username);
        AppUserDTO appUserDTO = modelMapper.map(appUser, AppUserDTO.class);

        return ResponseEntity.ok(appUserDTO);

    }

    @GetMapping("/{username}/timeline")
    public ResponseEntity<Page<TweetDTO>> getTimeline(@RequestParam(name = "page", required = false, defaultValue = "0") int page, @PathVariable String username){
        String authenticatedUsername = ContextUtils.getAuthenticatedUsername();
        AppUser authenticatedUser = appUserService.get(authenticatedUsername);

        Page<Tweet> result = tweetService.getTimelineFromUsername(username, page, authenticatedUser);
        Page<TweetDTO> resultDTO = result.map(Tweet::toDTO);
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }

    @GetMapping("/{username}/followers")
    public ResponseEntity<Page<AppUserDTO>> getFollowers(@PathVariable String username, @RequestParam(required = false, defaultValue = "0") Integer page){
        Page<Follow> appUserPage = appUserService.getFollowers(username, page);
        Page<AppUserDTO> appUserDTOPage = appUserPage.map(follow -> modelMapper.map(follow.getFollower(),AppUserDTO.class));

        return ResponseEntity.ok(appUserDTOPage);
    }

    @GetMapping("/{username}/following")
    public ResponseEntity<Page<AppUserDTO>> getFollowing(@PathVariable String username, @RequestParam(required = false, defaultValue = "0") Integer page){
        Page<Follow> appUserPage = appUserService.getFollowing(username, page);
        Page<AppUserDTO> appUserDTOPage = appUserPage.map(follow -> modelMapper.map(follow.getFollowed(),AppUserDTO.class));

        return ResponseEntity.ok(appUserDTOPage);
    }


    @GetMapping("/me")
    public ResponseEntity<AppUserDTO> getMyProfile(){
        String username = ContextUtils.getAuthenticatedUsername();

        AppUser appUser = appUserService.get(username);
        AppUserDTO appUserDTO = modelMapper.map(appUser, AppUserDTO.class);

        return ResponseEntity.ok(appUserDTO);

    }

    @GetMapping("/me/timeline")
    public ResponseEntity<Page<TweetDTO>> getMyTimeline(@RequestParam(name = "page", required = false, defaultValue = "0") int page){
        String authenticatedUsername = ContextUtils.getAuthenticatedUsername();
        AppUser authenticatedUser = appUserService.get(authenticatedUsername);

        Page<Tweet> result = tweetService.getTimelineFromUsername(authenticatedUsername, page, authenticatedUser);
        Page<TweetDTO> resultDTO = result.map(Tweet::toDTO);
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }

    @GetMapping("/me/followers")
    public ResponseEntity<Page<AppUserDTO>> getMyFollowers(@RequestParam(required = false, defaultValue = "0") Integer page){
        String username = ContextUtils.getAuthenticatedUsername();

        Page<Follow> appUserPage = appUserService.getFollowers(username, page);
        Page<AppUserDTO> appUserDTOPage = appUserPage.map(follow -> modelMapper.map(follow.getFollower(),AppUserDTO.class));

        return ResponseEntity.ok(appUserDTOPage);
    }

    @GetMapping("/me/following")
    public ResponseEntity<Page<AppUserDTO>> getMyFollowing(@RequestParam(required = false, defaultValue = "0") Integer page){
        String username = ContextUtils.getAuthenticatedUsername();

        Page<Follow> appUserPage = appUserService.getFollowing(username, page);
        Page<AppUserDTO> appUserDTOPage = appUserPage.map(follow -> modelMapper.map(follow.getFollowed(),AppUserDTO.class));

        return ResponseEntity.ok(appUserDTOPage);
    }


}
