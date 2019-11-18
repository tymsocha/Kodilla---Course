package com.kodilla.rating.service;

import com.kodilla.rating.controller.RatingNotFound;
import com.kodilla.rating.domain.Rating;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RatingService {
    private Set<Rating> ratings;

    private RatingService() {
        this.ratings = exampleData();
    }

    private Set<Rating> exampleData() {
        Set<Rating> ratings = new HashSet<>();
        ratings.add(new Rating(1L,1L,7));
        ratings.add(new Rating(2L,2L,6));
        ratings.add(new Rating(3L,3L,5));
        ratings.add(new Rating(4L,4L,4));
        ratings.add(new Rating(5L,5L,3));
        ratings.add(new Rating(6L,6L,2));
        ratings.add(new Rating(7L,7L,1));
        return ratings;
    }
    public List<Rating> findAllRatings() {
        return ratings.stream().collect(Collectors.toList());
    }

    public Rating findRatingById(Long id) throws RatingNotFound {
        return ratings.stream().filter(rating -> rating.getId().equals(id)).findFirst()
                .orElseThrow(RatingNotFound::new);
    }
    public List<Rating> findRatingsByMovieId(Long movieId) {
        return ratings.stream().filter(rating -> rating.getMovieId().equals(movieId))
                .collect(Collectors.toList());
    }

    public Rating createRating(Rating rating) {
        ratings.add(rating);
        return rating;
    }

    public void deleteRating(Long ratingId) throws RatingNotFound{
        ratings.remove(findRatingById(ratingId));
    }

    public Rating updateRating(Rating rating, Long ratingId) throws RatingNotFound {
        Rating rating1 = findRatingById(ratingId);
        rating1.setId(rating.getId());
        rating1.setMovieId(rating.getMovieId());
        rating1.setStars(rating.getStars());
        return rating1;
    }
}
