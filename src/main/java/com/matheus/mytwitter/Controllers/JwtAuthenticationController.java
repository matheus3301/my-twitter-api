package com.matheus.mytwitter.Controllers;

import com.matheus.mytwitter.Exceptions.EntityNotFoundException;
import com.matheus.mytwitter.Utils.JwtTokenUtil;
import com.matheus.mytwitter.DTOS.Requests.SignInRequestDTO;
import com.matheus.mytwitter.DTOS.Responses.SignInResponseDTO;
import com.matheus.mytwitter.Exceptions.InvalidPasswordException;
import com.matheus.mytwitter.Services.AppUserService;
import com.matheus.mytwitter.Services.Implementations.JwtUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class JwtAuthenticationController {
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsServiceImpl jwtUserDetailsService;
    private final AppUserService appUserService;

    @Autowired
    public JwtAuthenticationController(JwtTokenUtil jwtTokenUtil, JwtUserDetailsServiceImpl jwtUserDetailsService, AppUserService appUserService) {
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

    private void authenticate(String username, String password) throws EntityNotFoundException, InvalidPasswordException {
        appUserService.signIn(username, password);
    }
}
