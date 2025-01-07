package com.movie.fullstack.repository;

import com.movie.fullstack.model.GUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GUserRepository extends MongoRepository<GUser, String> {
    GUser findByEmail(String email);
}
