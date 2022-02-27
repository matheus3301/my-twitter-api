package com.matheus.mytwitter.Models;

import com.matheus.mytwitter.DTOS.Models.AppUserDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

//    @Column(nullable = false)
    private String passwordHash;

    public static AppUserDTO toDTO(AppUser appUser) {
        AppUserDTO appUserDTO = new AppUserDTO();

        appUserDTO.setUsername(appUser.getUsername());
        appUserDTO.setEmail(appUser.getEmail());
        appUserDTO.setName(appUser.getName());

        return appUserDTO;
    }
}
