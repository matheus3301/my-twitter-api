package com.matheus.mytwitter.Services.Implementations;

import com.matheus.mytwitter.Exceptions.InvalidPasswordException;
import com.matheus.mytwitter.Exceptions.PasswordDoesNotMatchException;
import com.matheus.mytwitter.Models.AppUser;
import com.matheus.mytwitter.Repositories.UserRepository;
import com.matheus.mytwitter.Services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.NoSuchElementException;

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

        return userRepository.save(appUser);
    }

    @Override
    public AppUser update(Long id, AppUser appUser) {


        return null;
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
                new ResponseStatusException(HttpStatus.BAD_REQUEST, "This username does not exist")
        );
    }

    @Override
    public AppUser get(String username) {
        username = username.trim();
        AppUser appUser = userRepository.findByUsername(username);

        if(appUser == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This username does not exist");

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
