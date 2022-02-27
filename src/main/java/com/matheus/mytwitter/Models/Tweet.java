package com.matheus.mytwitter.Models;

import com.matheus.mytwitter.DTOS.Models.TweetDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

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

    public static TweetDTO toDTO(Tweet tweet){
        TweetDTO tweetDTO = new TweetDTO();

        tweetDTO.setMessage(tweet.getMessage());
        tweetDTO.setAuthorUsername(tweet.getAuthor().getUsername());
        tweetDTO.setAuthorName(tweet.getAuthor().getName());
        tweetDTO.setCreatedAt(tweet.getCreatedAt().toLocalDateTime());

        return tweetDTO;
    }
}
