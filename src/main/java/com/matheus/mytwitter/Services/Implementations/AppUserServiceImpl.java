package com.matheus.mytwitter.Services.Implementations;

import com.matheus.mytwitter.Constants.Constants;
import com.matheus.mytwitter.Exceptions.*;
import com.matheus.mytwitter.Models.AppUser;
import com.matheus.mytwitter.Models.Follow;
import com.matheus.mytwitter.Repositories.FollowRepository;
import com.matheus.mytwitter.Repositories.UserRepository;
import com.matheus.mytwitter.Services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.followRepository = followRepository;
    }


    @Override
    public AppUser signUp(String username, String name, String email, String password, String confirmPassword) {
        if(!password.equals(confirmPassword))
            throw new PasswordDoesNotMatchException();

        name = name.trim();
        email = email.trim();

        username = username.trim();
        username = username.toLowerCase();

        AppUser appUser = new AppUser();
        appUser.setName(name);
        appUser.setEmail(email);
        appUser.setUsername(username);

        appUser.setPasswordHash(
                this.passwordEncoder.encode(password)
        );

        AppUser createdAppUser = userRepository.save(appUser);

        return createdAppUser;
    }

    @Override
    public AppUser updateProfile(String username, AppUser appUser) {
        AppUser original = userRepository.findByUsername(username);

        original.setEmail(appUser.getEmail());
        original.setName(appUser.getName());
        original.setAvatarUrl(appUser.getAvatarUrl());
        original.setBiography(appUser.getBiography());
        original.setPrivate(appUser.isPrivate());

        return userRepository.save(original);
    }

    @Override
    public void delete(String username) {

    }

    @Override
    public AppUser get(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
         new EntityNotFoundException("User")
        );
    }

    @Override
    public AppUser get(String username) {
        username = username.trim();
        AppUser appUser = userRepository.findByUsername(username);

        if(appUser == null)
            throw new EntityNotFoundException("User");

        return appUser;
    }

    @Override
    public AppUser signIn(String username, String password) {
        username = username.trim();
        username = username.toLowerCase();

        AppUser appUser = this.get(username);

        if (this.passwordEncoder.matches(password, appUser.getPasswordHash())){
            return appUser;
        }

        throw new InvalidPasswordException();
    }

//    @Override
//    public AppUser getProfile(String username, AppUser appUser) {
//        AppUser profile = this.get(username);
//        if(!profile.isPrivate() || followService.isFollowing(username, appUser.getUsername())){
//            return profile;
//        }
//
//        throw new NotFollowingException();
//    }

    @Override
    public Follow startFollowing(String toFollowUsername, AppUser appUser) {
        if(appUser.getUsername().equals(toFollowUsername))
            throw new FollowSelfLoopException();

        if(this.isFollowing(toFollowUsername, appUser.getUsername()))
            throw new AlreadyFollowingException();


        Follow follow = new Follow();

        AppUser toFollow = this.get(toFollowUsername);

        follow.setFollowed(toFollow);
        follow.setFollower(appUser);

        return followRepository.save(follow);

    }

    @Override
    public void stopFollowing(String toUnfollowUsername, AppUser appUser) {
        if(!this.isFollowing(toUnfollowUsername, appUser.getUsername()))
            throw new NotFollowingException();


        Follow follow = followRepository.
                findByFollowed_UsernameAndFollower_Username(toUnfollowUsername, appUser.getUsername()).get();

        followRepository.delete(follow);
    }

    @Override
    public Page<Follow> getFollowers(String username, Integer page) {
        this.get(username);

        PageRequest pageRequest = PageRequest.of(page, Constants.DEFAULT_PAGE_SIZE);

        return followRepository.findAllByFollowed_Username(username, pageRequest);
    }

    @Override
    public Page<Follow> getFollowing(String username, Integer page) {
        this.get(username);

        PageRequest pageRequest = PageRequest.of(page, Constants.DEFAULT_PAGE_SIZE);

        return followRepository.findAllByFollower_Username(username, pageRequest);
    }

    @Override
    public Long getFollowersCount(String username) {
        return followRepository.countAllByFollowed_Username(username);
    }

    @Override
    public Long getFollowingCount(String username) {
        this.get(username);
        return followRepository.countAllByFollower_Username(username);
    }

    @Override
    public Boolean isFollowing(String followed, String follower){
        this.get(followed);
        this.get(follower);

        return followRepository.findByFollowed_UsernameAndFollower_Username(followed, follower).isPresent();
    }

}
