package com.matheus.mytwitter.Services.Implementations;

import com.matheus.mytwitter.Exceptions.EntityNotFoundException;
import com.matheus.mytwitter.Exceptions.InvalidPasswordException;
import com.matheus.mytwitter.Exceptions.PasswordDoesNotMatchException;
import com.matheus.mytwitter.Models.AppUser;
import com.matheus.mytwitter.Repositories.UserRepository;
import com.matheus.mytwitter.Services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AppAppUserServiceImpl implements AppUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public AppAppUserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

        return userRepository.save(original);
    }

    @Override
    public Collection<AppUser> list() {
        return null;
    }

    @Override
    public void delete(Long id) {

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


}
