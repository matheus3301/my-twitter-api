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
public class CreateTweetRequestDTO {
    @NotBlank(message = "The tweet cannot be void")
    @Size(max = 255, message = "The tweet must have just 255 characters")
    private String message;
}
