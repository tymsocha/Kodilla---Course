package com.kodilla.rating.controller;

import com.kodilla.rating.service.RatingService;
import com.kodilla.rating.domain.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @GetMapping
    public List<Rating> findRatingsByMovieId(@RequestParam(required = false, defaultValue = "0") Long movieId) {
        if (movieId.equals(0L)) {
            return ratingService.findAllRatings();
        }
        return ratingService.findRatingsByMovieId(movieId);
    }

    @PostMapping
    public Rating createRating(@RequestBody Rating rating) {
        return ratingService.createRating(rating);
    }

    @DeleteMapping("/{ratingId}")
    public void deleteRating(@PathVariable Long ratingId) throws RatingNotFound {
        ratingService.deleteRating(ratingId);
    }

    @PutMapping("/{ratingId}")
    public Rating updateRating(@RequestBody Rating rating, @PathVariable Long ratingId) throws RatingNotFound {
        return ratingService.updateRating(rating, ratingId);
    }

    /*@PatchMapping("/{ratingId}")
    public Rating updateRating(
            @RequestBody Map<String, String> updates,
            @PathVariable Long ratingId) {
        return ratingService.updateRating(updates, ratingId);
    }*/
}
