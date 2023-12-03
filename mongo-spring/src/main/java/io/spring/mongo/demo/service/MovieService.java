package io.spring.mongo.demo.service;

import io.spring.mongo.demo.mongoDocuments.Movie;
import io.spring.mongo.demo.mongoDocuments.Review;
import io.spring.mongo.demo.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    public String calculateRating(String imdbId){
        Optional<Movie> movie = repository.findMovieByImdbId(imdbId);
        double sum = 0;
        double averageRating = 0;
        List<Review> reviews = movie.get().getReviews();
        for (Review review :reviews) {
            sum +=  Double.parseDouble(review.getRating());
        }
        if(!reviews.isEmpty()){
            averageRating = sum / reviews.size();
        }

        return String.format("%.1f", averageRating);
    }

    public List<Movie> findAllMovies() {
        return repository.findAll();
    }
    public Optional<Movie> findMovieByImdbId(String imdbId) {
        return repository.findMovieByImdbId(imdbId);
    }
}