package com.matheus.mytwitter.Controllers;

import com.matheus.mytwitter.Configurations.JwtTokenUtil;
import com.matheus.mytwitter.DTOS.Requests.SignInRequestDTO;
import com.matheus.mytwitter.DTOS.Responses.SignInResponseDTO;
import com.matheus.mytwitter.Exceptions.InvalidPasswordException;
import com.matheus.mytwitter.Services.AppUserService;
import com.matheus.mytwitter.Services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class JwtAuthenticationController {
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService jwtUserDetailsService;
    private final AppUserService appUserService;

    @Autowired
    public JwtAuthenticationController(JwtTokenUtil jwtTokenUtil, JwtUserDetailsService jwtUserDetailsService, AppUserService appUserService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.appUserService = appUserService;
    }

    @PostMapping("/authenticate")
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

    private void authenticate(String username, String password) throws DisabledException, BadCredentialsException {
        try {
            appUserService.signIn(username, password);
        } catch (InvalidPasswordException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Password");
        }
    }
}
