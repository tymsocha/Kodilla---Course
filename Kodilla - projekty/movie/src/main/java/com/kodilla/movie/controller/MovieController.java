package com.kodilla.movie.controller;

import com.kodilla.movie.domain.Movie;
import com.kodilla.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Transactional
@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> findAllMovies() {
        return movieService.findAllMovies();
    }

    @GetMapping("/{movieId}")
    public Movie findMovie(@PathVariable Long movieId) throws MovieNotFound {
        return movieService.findMovieById(movieId).orElseThrow(MovieNotFound::new);
    }

    @PostMapping
    public void createMovie(@RequestBody Movie movie) {
        movieService.createMovie(movie);
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable Long movieId) throws MovieNotFound{
        movieService.deleteMovie(movieId);
    }

    @PutMapping("/{movieId}")
    public Movie updateMovie(@RequestBody Movie movie, @PathVariable Long movieId) throws MovieNotFound {
        return movieService.updateMovie(movieId, movie);
    }

    /*@PatchMapping("/{movieId}")
    public Movie updateMovie(
            @RequestBody Map<String, String> updates,
            @PathVariable Long movieId) {
        return movieService.updateMovie(updates, movieId);
    }*/
}
