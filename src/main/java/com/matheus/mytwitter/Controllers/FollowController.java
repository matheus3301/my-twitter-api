package com.matheus.mytwitter.Controllers;

import com.matheus.mytwitter.Models.AppUser;
import com.matheus.mytwitter.Services.AppUserService;
import com.matheus.mytwitter.Utils.ContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/follow")
public class FollowController {

    private final AppUserService appUserService;

    @Autowired
    public FollowController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @PostMapping("/{username}")
    public ResponseEntity<Void> startFollowing(@PathVariable String username){
        String authenticatedUsername = ContextUtils.getAuthenticatedUsername();

        AppUser appUser = appUserService.get(authenticatedUsername);

        appUserService.startFollowing(username, appUser);

        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> stopFollowing(@PathVariable String username){
        String authenticatedUsername = ContextUtils.getAuthenticatedUsername();

        AppUser appUser = appUserService.get(authenticatedUsername);

        appUserService.stopFollowing(username, appUser);

        return ResponseEntity.noContent().build();
    }

}
