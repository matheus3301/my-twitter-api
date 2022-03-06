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
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 8)
    private String password;
}
