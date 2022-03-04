package com.matheus.mytwitter.Configurations;

import com.matheus.mytwitter.DTOS.Models.AppUserDTO;
import com.matheus.mytwitter.Models.AppUser;
import com.matheus.mytwitter.Services.AppUserService;
import com.matheus.mytwitter.Services.TweetService;
import org.modelmapper.Conditions;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConfiguration {

    private final TweetService tweetService;
    private final AppUserService appUserService;

    @Autowired
    public ModelMapperConfiguration(TweetService tweetService, AppUserService appUserService) {
        this.tweetService = tweetService;
        this.appUserService = appUserService;
    }

    @Bean
    ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        Converter<String, Long> getUserFollowers = mappingContext -> appUserService.getFollowersCount(mappingContext.getSource());;
        Converter<String, Long> getUserFollowing = mappingContext -> appUserService.getFollowingCount(mappingContext.getSource());


        modelMapper.createTypeMap(AppUser.class, AppUserDTO.class)
                .addMappings(
                        mapper -> mapper.using(getUserFollowers).map(AppUser::getUsername, (dest, value) -> dest.setFollowers((Long) value))
                )
                .addMappings(
                        mapper -> mapper.using(getUserFollowing).map(AppUser::getUsername, (dest, value) -> dest.setFollowing((Long) value))
                );


        return modelMapper;
    }
}