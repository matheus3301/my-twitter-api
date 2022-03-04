package com.matheus.mytwitter.Models;

import com.matheus.mytwitter.DTOS.Models.TweetDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Tweet {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private AppUser author;

    @Column(nullable = false, length = 255)
    private String message;

    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToMany
    Set<AppUser> likedBy;
}
