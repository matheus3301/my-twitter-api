package com.matheus.mytwitter.Services.Implementations;

import com.matheus.mytwitter.Models.AppUser;
import com.matheus.mytwitter.Services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final AppUserService appUserService;

    @Autowired
    public JwtUserDetailsServiceImpl(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserService.get(username);
        return new User(username, appUser.getPasswordHash(), new ArrayList<>());
    }
}
