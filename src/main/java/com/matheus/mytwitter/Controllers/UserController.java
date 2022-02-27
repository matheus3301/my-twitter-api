package com.matheus.mytwitter.Controllers;

import com.matheus.mytwitter.DTOS.Models.AppUserDTO;
import com.matheus.mytwitter.DTOS.Requests.SignUpRequestDTO;
import com.matheus.mytwitter.Models.AppUser;
import com.matheus.mytwitter.Services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    private final AppUserService appUserService;

    @Autowired
    public UserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @PostMapping("/signup")
    public ResponseEntity<AppUserDTO> signUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO){
        AppUserDTO appUserDTO = AppUser.toDTO(
                appUserService.signUp(
                        signUpRequestDTO.getUsername(),
                        signUpRequestDTO.getName(),
                        signUpRequestDTO.getEmail(),
                        signUpRequestDTO.getPassword(),
                        signUpRequestDTO.getConfirmPassword()
                )
        );

        return new ResponseEntity<>(appUserDTO, HttpStatus.CREATED);
    }
}
