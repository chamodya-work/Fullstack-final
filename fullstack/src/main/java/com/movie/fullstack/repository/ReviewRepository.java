package com.movie.fullstack.repository;

import com.movie.fullstack.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByContentId(String contentId);
}

