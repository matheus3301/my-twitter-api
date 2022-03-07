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
    @URL(message = "Profile photo link not valid")
    private String avatarUrl;

    private String biography;

    @NotBlank(message = "Email is a required field")
    @Email(message = "Email not valid")
    private String email;

    @NotBlank(message = "Name is a required field")
    private String name;

    @NotNull(message = "Profile privacy is a required field")
    private boolean isPrivate;
}
