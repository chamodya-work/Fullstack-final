package com.movie.fullstack.repository;

import com.movie.fullstack.model.Documentary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentaryRepository extends MongoRepository<Documentary, String> {
}
