package com.matheus.mytwitter.Services;

import com.matheus.mytwitter.Models.AppUser;

import java.util.Collection;

public interface AppUserService {
    AppUser signUp(
            String username,
            String name,
            String email,
            String password,
            String confirmPassword
    );
    AppUser update(Long id, AppUser appUser);
    Collection<AppUser> list();
    void delete(Long id);
    AppUser get(Long id);
    AppUser get(String username);
    AppUser signIn(String username, String password);
}
