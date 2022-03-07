package com.matheus.mytwitter.DTOS.Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequestDTO {
    @NotBlank(message = "Username is a required field")
    private String username;
    @NotBlank(message = "Password is a required field")
    @Size(min = 8, message = "The password must have at least 8 characters")
    private String password;
}
