package com.matheus.mytwitter.DTOS.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDTO {
    @NotBlank(message = "Name is a required field")
    private String name;

    @NotBlank(message = "Email is a required field")
    @Email(message = "Email not valid")
    private String email;

    @NotBlank(message = "Password is a required field")
    @Size(min = 8, message = "The password must have at least 8 characters")
    private String password;

    @NotBlank(message = "You must confirm your password")
    @Size(min = 8, message = "The password confirmation must have at least 8 characters")
    private String confirmPassword;

    @NotBlank(message = "Username is a required field")
    private String username;
}
