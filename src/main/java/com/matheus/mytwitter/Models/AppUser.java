package com.matheus.mytwitter.Models;

import com.matheus.mytwitter.DTOS.Models.AppUserDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

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

    @Column(nullable = false)
    private String passwordHash;

    private String avatarUrl;

    private String biography;

    @Column(columnDefinition = "boolean default false")
    private boolean isPrivate = false;

}
