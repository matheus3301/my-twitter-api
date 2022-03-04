package com.matheus.mytwitter.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class Follow {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private AppUser follower;
    @ManyToOne
    private AppUser followed;
}
