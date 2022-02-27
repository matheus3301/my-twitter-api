package com.matheus.mytwitter.Repositories;

import com.matheus.mytwitter.Models.AppUser;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
