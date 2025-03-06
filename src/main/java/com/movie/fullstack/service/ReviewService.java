package com.movie.fullstack.service;

import com.movie.fullstack.model.DatabaseSequence;
import com.movie.fullstack.model.Review;
import com.movie.fullstack.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Review review) {
        review.setReview_id(generateSequence(Review.SEQUENCE_NAME));
        review.setTimeStamp(LocalDate.now());
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsForContent(String contentId) {
        return reviewRepository.findByContentId(contentId);
    }

    @Autowired
    private MongoOperations mongoOperations;

    public String generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return "R" + (!Objects.isNull(counter) ? counter.getSeq() : 1);
    }

    public boolean deleteReview(String reviewId) {
        // Check if review exists before attempting to delete
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            // If review exists, delete it from the repository
            reviewRepository.delete(review);
            return true;
        }
        return false;  // Review not found
    }

}

