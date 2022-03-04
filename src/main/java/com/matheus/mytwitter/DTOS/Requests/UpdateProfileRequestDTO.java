package com.matheus.mytwitter.DTOS.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequestDTO {
    @URL
    private String avatarUrl;

    private String biography;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;

    @NotNull
    private boolean isPrivate;
}
