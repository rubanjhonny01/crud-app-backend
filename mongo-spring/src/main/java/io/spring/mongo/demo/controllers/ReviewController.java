package io.spring.mongo.demo.controllers;

import io.spring.mongo.demo.model.ReviewModel;
import io.spring.mongo.demo.mongoDocuments.Review;
import io.spring.mongo.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService service;

    @PostMapping()
    public ResponseEntity<Review> createReview(@RequestBody ReviewModel payload) throws InterruptedException {

        return new ResponseEntity<Review>(service.createReview(payload.getBody(), payload.getStar(), payload.getImdbId()), HttpStatus.OK);
    }
}
