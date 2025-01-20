package com.movie.fullstack.controller;

import com.movie.fullstack.model.Review;
import com.movie.fullstack.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/addReview")
    public Review submitReview(@RequestBody Review review){
        return reviewService.addReview(review);
    }

    @GetMapping("/getAllReviews")
    public List<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }

    @GetMapping("/getReviewByContent/{contentId}")
    public List<Review> getReviewsForContent(@PathVariable String contentId){
        return reviewService.getReviewsForContent(contentId);
    }
}

