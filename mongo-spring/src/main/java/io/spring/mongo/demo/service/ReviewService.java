package io.spring.mongo.demo.service;


import io.spring.mongo.demo.mongoDocuments.Movie;
import io.spring.mongo.demo.mongoDocuments.Review;
import io.spring.mongo.demo.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MovieService movieService;

    @Transactional
    public Review createReview(String reviewBody, String movieRating, String imdbId) throws InterruptedException {
        Review review = repository.insert(new Review(reviewBody, movieRating, LocalDateTime.now(), LocalDateTime.now()));
        Thread.sleep(2000);
        String averageMovieRating = movieService.calculateRating(imdbId);
        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviews").value(review).set("rating",averageMovieRating))
                .first();
        return review;
    }
}
