package com.matheus.mytwitter.Controllers;

import com.matheus.mytwitter.DTOS.Models.AppUserDTO;
import com.matheus.mytwitter.DTOS.Requests.SignUpRequestDTO;
import com.matheus.mytwitter.Exceptions.EntityNotFoundException;
import com.matheus.mytwitter.Models.AppUser;
import com.matheus.mytwitter.Utils.JwtTokenUtil;
import com.matheus.mytwitter.DTOS.Requests.SignInRequestDTO;
import com.matheus.mytwitter.DTOS.Responses.SignInResponseDTO;
import com.matheus.mytwitter.Exceptions.InvalidPasswordException;
import com.matheus.mytwitter.Services.AppUserService;
import com.matheus.mytwitter.Services.Implementations.JwtUserDetailsServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsServiceImpl jwtUserDetailsService;
    private final AppUserService appUserService;

    private final ModelMapper modelMapper;

    @Autowired
    public AuthenticationController(JwtTokenUtil jwtTokenUtil, JwtUserDetailsServiceImpl jwtUserDetailsService, AppUserService appUserService, ModelMapper modelMapper) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.appUserService = appUserService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/signup")
    public ResponseEntity<AppUserDTO> signUp(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO){
        AppUser appUser = appUserService.signUp(
                signUpRequestDTO.getUsername(),
                signUpRequestDTO.getName(),
                signUpRequestDTO.getEmail(),
                signUpRequestDTO.getPassword(),
                signUpRequestDTO.getConfirmPassword()
        );

        AppUserDTO appUserDTO = modelMapper.map(appUser, AppUserDTO.class);

        return new ResponseEntity<>(appUserDTO, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDTO> createAuthenticationToken(@RequestBody SignInRequestDTO signInRequestDTO){
        this.authenticate(
                signInRequestDTO.getUsername(),
                signInRequestDTO.getPassword()
        );
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(
                signInRequestDTO.getUsername()
        );
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new SignInResponseDTO(token));
    }

    private void authenticate(String username, String password) throws EntityNotFoundException, InvalidPasswordException {
        appUserService.signIn(username, password);
    }
}
