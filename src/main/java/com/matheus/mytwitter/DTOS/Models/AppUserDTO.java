package com.matheus.mytwitter.DTOS.Models;

import com.matheus.mytwitter.Models.AppUser;
import com.matheus.mytwitter.Repositories.UserRepository;
import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @URL
    private String avatarUrl;

    private String biography;

    private Long followers = 0L;

    private Long following = 0L;

    private boolean isPrivate;
}
